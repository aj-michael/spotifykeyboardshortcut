This is a janky way to play and pause Spotify via a keyboard shortcut

To use it, you need to first register with Spotify to obtain a client id and
client secret. Add those to SpotifyAPI.java. Then you need to obtain an access
token. Their auth API is geared towards websites, and unfortunately this isn't
a website so the authentication steps are:

* Run GetAuthorizationURL.java and go to the URL that it prints. That will
  redirect you to a URL with a `code` URL parameter. Copy that into
  AUTHORIZATION_CODE in SpotifyAPI.java.
* Run GetAccessToken.java. It will print out an access token. Copy that into
  ACCESS_TOKEN in SpotifyAPI.java

Now you should be able to run SpotifyKeyboardShortcutRunner.java. The key that
it listens to is hardcoded in there to Fn11. That key should toggle your Spotify
play/pause. It assumes that Spotify is not playing when you first start it up.