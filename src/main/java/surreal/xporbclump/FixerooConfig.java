package surreal.xporbclump;

import com.cleanroommc.configanytime.ConfigAnytime;
import net.minecraftforge.common.config.Config;
import surreal.xporbclump.core.FixerooPlugin;

@Config(modid = Fixeroo.MODID)
public class FixerooConfig {

    public static final XPOrbClump xpOrbClump = new XPOrbClump();

    public static class XPOrbClump {
        @Config.Comment("Enable xp orb clumping")
        public boolean enable = true;

        @Config.Comment("Remove xp collecting cooldown")
        public boolean removeCooldown = true;

        @Config.Comment("Size of checking area")
        public double areaSize = 4D;

        @Config.Comment("How many xp orbs can be in that area")
        public int maxOrbCount = 1;

        @Config.Comment("Changes orbs size based on the amount of experience it holds")
        public boolean changeOrbSize = false;
    }

    static {
        if (FixerooPlugin.configAnytime) {
            ConfigAnytime.register(FixerooConfig.class);
        }
    }
}
