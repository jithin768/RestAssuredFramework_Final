package com.spotify.oauth2.applicationApi;

import com.spotify.oauth2.API.TokenManager;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.spotify.oauth2.API.Route.PLAYLISTS;
import static com.spotify.oauth2.API.Route.USERS;
import static com.spotify.oauth2.API.TokenManager.getToken;


public class PlaylistApi {

    //static String Access_Token="BQAas9hgMbLS_Qfk58Ietcl1YlfkfnKriBbPwzZjV-wUhN4HJAhuNNFyjsFOBD1DRwLybIgqQfaUMK4ZM6YBQfWdSNQAoIjtv7eOHFNUxtdp3wF3sTdtpPsYWdS1i6js4O5w5a_GdoCdH6g5hY40n7rLVlPJrnjQS0ft2aByhZSHCpVogJKVVfjpiayG8eqzSVPIQP1JDYPRc10LrTjEDhzUwccB2fpRpajKdnTQvtbDaZi1EKBm5o0dq6ZyuKBA-sOFCYFxGyyRs-3p";

    public static Response Post(Playlist requestPlaylist){

        return RestResource.Post(USERS +"/"+ ConfigLoader.getInstance().getUser() + PLAYLISTS,getToken(),requestPlaylist);

    }

    public static Response Post(String token, Playlist requestPlaylist){

        return RestResource.Post(USERS +"/"+ ConfigLoader.getInstance().getUser()+ PLAYLISTS,token,requestPlaylist);

    }

    public static Response Get(String playlistId){

        return RestResource.Get(PLAYLISTS+ "/"+ playlistId,getToken());

    }

    public static Response update(Playlist requestPlaylist, String playlistId){

        return RestResource.update(PLAYLISTS+ "/"+ playlistId,getToken(),requestPlaylist);

    }






}
