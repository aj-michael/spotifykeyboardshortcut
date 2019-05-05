package net.ajmichael.spotify;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

final class SpotifyCommander {

  private static final OkHttpClient client = new OkHttpClient();
  private final String accessToken;

  SpotifyCommander(String accessToken) {
    this.accessToken = accessToken;
  }

  void play() throws IOException {
    Request request = new Request.Builder().addHeader("Authorization", "Bearer " + accessToken)
        .url(SpotifyAPI.PLAY_URL)
        .put(RequestBody.create(null, "")).build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response + ": " + response.body().string());
      }
    }
  }

  void pause() throws IOException {
    Request request = new Request.Builder().addHeader("Authorization", "Bearer " + accessToken)
        .url(SpotifyAPI.PAUSE_URL)
        .put(RequestBody.create(null, "")).build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response + ": " + response.body().string());
      }
    }
  }
}
