package net.ajmichael.spotify;

import java.net.URLEncoder;

final class GetAuthorizationURL {

  public static void main(String[] args) throws Exception {
    String baseUrl = "https://accounts.spotify.com/authorize";
    String authorizeUrl = baseUrl + "?client_id=" + SpotifyAPI.CLIENT_ID
        + "&response_type=code&scope=user-modify-playback-state&redirect_uri=" +
        URLEncoder.encode(SpotifyAPI.AUTHORIZE_REDIRECT_URI, "UTF-8");
    System.out.println(authorizeUrl);
  }
}
