package com.spotify.oauth2.tests;

import com.spotify.oauth2.API.StatusCode;
import com.spotify.oauth2.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.FakerUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.spotify.oauth2.API.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.API.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlaylistTests extends BaseTest {


    @Test
    public void shouldAddAPlaylist(){

        Playlist requestPlaylist=new Playlist()
                .setName(FakerUtils.generateName())
                .setDescription("Jithin POJO Description")
                .setPublic(false);

        Response response=PlaylistApi.Post(requestPlaylist);
        assertThat(response.statusCode(),equalTo(StatusCode.CODE_201.getCode()));

        Playlist responsePlaylist=response.as(Playlist.class);


                assertThat(requestPlaylist.getName(),equalTo(responsePlaylist.getName()));
                assertThat(requestPlaylist.getDescription(),equalTo(responsePlaylist.getDescription()));
                assertThat(requestPlaylist.getPublic(),equalTo(responsePlaylist.getPublic()));
    }
    @Test
    public void shouldGetAPlaylist(){

        Playlist requestPlaylist=new Playlist()
                .setName("Updated Jithin New Playlist1")
                .setDescription("Updated Jithin New playlist description1")
                .setPublic(true);

        Response response=PlaylistApi.Get(DataLoader.getInstance().getPlaylist_Id());
        assertThat(response.statusCode(),equalTo(StatusCode.CODE_200.getCode()));

        Playlist responsePlaylist=response.as(Playlist.class);

        assertThat(requestPlaylist.getName(),equalTo(responsePlaylist.getName()));
        assertThat(requestPlaylist.getDescription(),equalTo(responsePlaylist.getDescription()));
        assertThat(requestPlaylist.getPublic(),equalTo(responsePlaylist.getPublic()));

    }

    @Test
    public void shouldUpdateAPlaylist(){

        Playlist requestPlaylist=new Playlist()
                .setName("Updated Jithin New Playlist1")
                .setDescription("Updated Jithin New playlist description1")
                .setPublic(true);

        Response response=PlaylistApi.update(requestPlaylist,DataLoader.getInstance().getUpdatePlaylist_Id());
        assertThat(response.statusCode(),equalTo(StatusCode.CODE_200.getCode()));


    }

    @Test
    public void shouldNotAbleToAddAPlaylistWithoutName(){

        Playlist requestPlaylist=new Playlist()
                    .setName("")
                    .setDescription("Jithin New playlist description1")
                    .setPublic(false);

        Response response=PlaylistApi.Post(requestPlaylist);
        assertThat(response.statusCode(),equalTo(StatusCode.CODE_400.getCode()));

        Error error=response.as(Error.class);



       assertThat(error.getError().getStatus(),equalTo(StatusCode.CODE_400.getCode()));
       assertThat(error.getError().getMessage(),equalTo(StatusCode.CODE_400.getMsg()));

    }

   @Test
    public void shouldNotAbleToAddAPlaylistWithInvalidAccessToken(){
        Playlist requestPlaylist=new Playlist()
                    .setName("Updated Jithin New Playlist1")
                    .setDescription("Updated Jithin New playlist description1")
                    .setPublic(false);
       String token="1234";

        Response response=PlaylistApi.Post(token, requestPlaylist);
        assertThat(response.statusCode(),equalTo(StatusCode.CODE_401.getCode()));

       Error error=response.as(Error.class);

        assertThat(error.getError().getStatus(),equalTo(StatusCode.CODE_401.getCode()));
        assertThat(error.getError().getMessage(),equalTo(StatusCode.CODE_401.getMsg()));


    }

}
