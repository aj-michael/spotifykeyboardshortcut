package net.ajmichael.spotify;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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
    AccessToken token = new AccessToken(null, SpotifyAPI.REFRESH_TOKEN);
    ScheduledExecutorService refreshService = Executors.newSingleThreadScheduledExecutor();
    refreshService.scheduleAtFixedRate(() -> {
      try {
        token.refresh();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }, 0, 30, TimeUnit.MINUTES);
    SpotifyStateToggler toggler = new SpotifyStateToggler(token);
    GlobalScreen.addNativeKeyListener(
        new KeyPressedListener(NativeKeyEvent.VC_F10, toggler::toggleState));
  }
}