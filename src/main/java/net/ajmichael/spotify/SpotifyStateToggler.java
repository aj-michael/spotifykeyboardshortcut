package net.ajmichael.spotify;

import java.io.IOException;

class SpotifyStateToggler {

  enum State {ON, OFF}

  ;

  private State state;
  private final SpotifyCommander commander;


  SpotifyStateToggler(AccessToken accessToken) throws IOException {
    this.state = State.OFF;
    this.commander = new SpotifyCommander(new SpotifyAPIRequester(accessToken));
  }

  final void toggleState() {
    try {
      if (state == State.ON) {
        System.out.println("Attempting to pause");
        commander.pause();
        this.state = State.OFF;
      } else {
        System.out.println("Attempting to play");
        commander.play();
        this.state = State.ON;
      }
    } catch (IOException e) {
      System.out.println("Error while changing state: " + e.getMessage());
    }
  }
}
