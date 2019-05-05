package net.ajmichael.spotify;

import java.io.IOException;
import java.util.Base64;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

final class GetAccessToken {

  public static void main(String[] args) throws Exception {
    OkHttpClient client = new OkHttpClient();
    RequestBody requestBody = new FormBody.Builder()
        .add("grant_type", "authorization_code")
        .add("code", SpotifyAPI.AUTHORIZATION_CODE)
        .add("redirect_uri", SpotifyAPI.AUTHORIZE_REDIRECT_URI)
        .build();
    Request request = new Request.Builder().url(SpotifyAPI.ACCESS_TOKEN_URL)
        .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(
            (SpotifyAPI.CLIENT_ID + ":" + SpotifyAPI.CLIENT_SECRET)
                .getBytes()))
        .post(requestBody)
        .build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Unexpected code " + response);
      }
      String accessToken = new JSONObject(response.body().string()).getString("access_token");
      System.out.println(accessToken);
    }
  }
}
