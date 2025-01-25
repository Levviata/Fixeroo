package surreal.xporbclump.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import surreal.xporbclump.FixerooConfig;

import java.util.List;

@SuppressWarnings("unused")
public class FixerooHooks {

    public static void debug(Object obj) {
        System.out.println(obj);
    }

    // XP Orb Clump
    public static void EntityXPOrb$onUpdate(EntityXPOrb orb) {
        if (orb.xpValue == Integer.MAX_VALUE) return;
        World world = orb.world;
        double a = FixerooConfig.xpOrbClump.areaSize / 2;
        List<Entity> orbs = world.getEntitiesInAABBexcluding(
                orb,
                new AxisAlignedBB(orb.posX - a, orb.posY - a, orb.posZ - a, orb.posX + a, orb.posY + a, orb.posZ + a),
                e -> e instanceof EntityXPOrb
        );
        System.out.println(orbs.size());
        if (orbs.size() <= FixerooConfig.xpOrbClump.maxOrbCount) return;
        int count = orbs.size();
        for (Entity e : orbs) {
            if (count <= FixerooConfig.xpOrbClump.maxOrbCount) return;
            EntityXPOrb o = (EntityXPOrb) e;
            if (o.xpValue == Integer.MAX_VALUE) continue;
            if ((long) orb.xpValue + o.xpValue > Integer.MAX_VALUE) orb.xpValue = Integer.MAX_VALUE;
            else orb.xpValue += o.xpValue;
            o.setDead();
            count--;
        }
    }

    public static int EntityXPOrb$getXPValue(NBTTagCompound tag) {
        if (tag.hasKey("Value", Constants.NBT.TAG_SHORT)) return tag.getShort("Value");
        else return tag.getInteger("Value");
    }

    public static float RenderXPOrb$getSize(EntityXPOrb orb) {
        int xpValue = orb.xpValue;
        return Math.max(0.3F, MathHelper.sqrt(xpValue) / 100);
    }
}