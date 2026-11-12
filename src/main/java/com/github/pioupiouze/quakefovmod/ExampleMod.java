package com.github.pioupiouze.quakefovmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = "quakefovmod", useMetadata=true)
public class ExampleMod {

    private static final KeyBinding TOGGLE_KEY =
            new KeyBinding("Activer le FOV Quake Pro", Keyboard.KEY_V, "Quake FOV");

    private float savedFov = 70f;
    private boolean active = false;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientRegistry.registerKeyBinding(TOGGLE_KEY);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (!TOGGLE_KEY.isPressed()) return;

        Minecraft mc = Minecraft.getMinecraft();
        if (active) {
            mc.gameSettings.fovSetting = savedFov;
        } else {
            savedFov = mc.gameSettings.fovSetting;
            mc.gameSettings.fovSetting = 110f;
        }
        active = !active;
    }
}
