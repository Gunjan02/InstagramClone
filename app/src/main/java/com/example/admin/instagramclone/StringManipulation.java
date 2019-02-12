package com.example.admin.instagramclone;

public class StringManipulation {
    public static String expandUsername(String username){
        return username.replace(".","");

    }

    public static String compressUsername(String username){
        return username.replace("",".");
    }
}
