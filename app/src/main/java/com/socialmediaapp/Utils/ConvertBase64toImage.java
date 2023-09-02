package com.socialmediaapp.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class ConvertBase64toImage {
     public static Bitmap convert(String base64){
        String base64Image = base64.split(",")[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
