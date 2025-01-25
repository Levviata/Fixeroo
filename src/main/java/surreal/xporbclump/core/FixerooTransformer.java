package surreal.xporbclump.core;

import net.minecraft.launchwrapper.IClassTransformer;
import surreal.xporbclump.core.transformers.XPOrbTransformer;


@SuppressWarnings("unused")
public class FixerooTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (!FixerooPlugin.configAnytime) return basicClass;
        switch (transformedName) {
            // Make XP Orbs get together to reduce FPS.
            // Potentially fixes a bug where game crashes when you get output from a furnace that holds too many experience.
            case "net.minecraft.entity.item.EntityXPOrb": return XPOrbTransformer.transformEntityXPOrb(transformedName, basicClass);

            // Change XP Orbs' size based on their level.
            case "net.minecraft.client.renderer.entity.RenderXPOrb": return XPOrbTransformer.transformRenderXPOrb(transformedName, basicClass);
        }
        return basicClass;
    }
}
