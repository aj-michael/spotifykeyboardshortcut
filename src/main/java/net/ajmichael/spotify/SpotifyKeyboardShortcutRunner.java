package net.ajmichael.spotify;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;

final class SpotifyKeyboardShortcutRunner {

  private static void initializeJNativeHook() throws NativeHookException {
    Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
    GlobalScreen.registerNativeHook();
  }

  public static void main(String[] args) throws Exception {
    initializeJNativeHook();
    SpotifyStateToggler toggler = new SpotifyStateToggler();
    GlobalScreen.addNativeKeyListener(
        new KeyPressedListener(NativeKeyEvent.VC_F11, toggler::toggleState));
  }
}