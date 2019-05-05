package net.ajmichael.spotify;

import java.io.IOException;

final class SpotifyCommander {

  private final SpotifyAPIRequester requester;

  SpotifyCommander(SpotifyAPIRequester requester) {
    this.requester = requester;
  }

  void play() throws IOException {
    requester.postRequest(SpotifyAPI.PLAY_URL);
  }

  void pause() throws IOException {
    requester.postRequest(SpotifyAPI.PAUSE_URL);
  }
}
