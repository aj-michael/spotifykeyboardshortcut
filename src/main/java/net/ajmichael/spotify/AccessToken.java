package net.ajmichael.spotify;

import java.io.IOException;
import java.util.Base64;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

final class AccessToken {

  private static final OkHttpClient client = new OkHttpClient();

  volatile String accessToken;
  private final String refreshToken;

  AccessToken(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    System.out.println("Created access token: " + this);
  }

  @Override
  public String toString() {
    return "{ access_token = " + accessToken + " , refresh_token = " + refreshToken + " }";
  }

  static AccessToken getAccessToken(String authorizationCode) throws IOException {
    RequestBody requestBody = new FormBody.Builder()
        .add("grant_type", "authorization_code")
        .add("code", authorizationCode)
        .add("redirect_uri", SpotifyAPI.AUTHORIZE_REDIRECT_URI)
        .build();
    Request request = new Request.Builder().url(SpotifyAPI.ACCESS_TOKEN_URL)
        .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(
            (SpotifyAPI.CLIENT_ID + ":" + SpotifyAPI.CLIENT_SECRET)
                .getBytes()))
        .post(requestBody)
        .build();
    Response response = client.newCall(request).execute();
    if (!response.isSuccessful()) {
      throw new IOException("Unexpected code " + response + ": " + response.body().string());
    }
    JSONObject data = new JSONObject(response.body().string());
    return new AccessToken(data.getString("access_token"), data.getString("refresh_token"));
  }

  final void refresh() throws IOException {
    RequestBody requestBody = new FormBody.Builder()
        .add("grant_type", "refresh_token")
        .add("refresh_token", refreshToken)
        .add("redirect_uri", SpotifyAPI.AUTHORIZE_REDIRECT_URI)
        .build();
    Request request = new Request.Builder().url(SpotifyAPI.ACCESS_TOKEN_URL)
        .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(
            (SpotifyAPI.CLIENT_ID + ":" + SpotifyAPI.CLIENT_SECRET)
                .getBytes()))
        .post(requestBody)
        .build();
    Response response = client.newCall(request).execute();
    if (!response.isSuccessful()) {
      throw new IOException("Unexpected code " + response + ": " + response.body().string());
    }
    JSONObject data = new JSONObject(response.body().string());
    this.accessToken = data.getString("access_token");
    System.out.println("Refreshed access token: " + this);
  }

  public static void main(String[] args) throws Exception {
    AccessToken accessToken = getAccessToken(SpotifyAPI.AUTHORIZATION_CODE);
    System.out.println(accessToken);
    accessToken.refresh();
    System.out.println(accessToken);
    accessToken.refresh();
    System.out.println(accessToken);
  }
}
