package com.mycompany.instagramclone.Utils;

/**
 * Created by Vidya Prabhu on 19-10-2017.
 */

public class StringManipulation {
    public static String expandUsername(String username){
                return username.replace(".", " ");
            }

            public static String condenseUsername(String username){
                return username.replace(" " , ".");
            }
}
