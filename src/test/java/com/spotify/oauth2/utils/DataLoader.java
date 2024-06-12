package com.spotify.oauth2.utils;

import java.util.Properties;

public class DataLoader {

    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader(){
        properties=PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance(){

        if(dataLoader==null){
            dataLoader=new DataLoader();
        }

        return dataLoader;
    }

    public String getPlaylist_Id(){
        String prop=properties.getProperty("GetPlaylist_Id");
        if(prop!=null)
            return prop;
        else throw new RuntimeException("Property GetPlaylist_Id is not specified in the data.properties file");
    }

    public String getUpdatePlaylist_Id(){
        String prop=properties.getProperty("UpdatePlaylist_Id");
        if(prop!=null)
            return prop;
        else throw new RuntimeException("Property UpdatePlaylist_Id is not specified in the data.properties file");
    }

}
