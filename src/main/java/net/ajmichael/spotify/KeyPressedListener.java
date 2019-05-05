package net.ajmichael.spotify;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

final class KeyPressedListener implements NativeKeyListener {

  private final int keyCode;
  private final Runnable callback;

  KeyPressedListener(int keyCode, Runnable callback ) {
    this.keyCode = keyCode;
    this.callback = callback;
  }

  @Override
  public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
    if (nativeKeyEvent.getKeyCode() == keyCode) {
      callback.run();
    }
  }

  @Override
  public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {}

  @Override
  public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {}
}
