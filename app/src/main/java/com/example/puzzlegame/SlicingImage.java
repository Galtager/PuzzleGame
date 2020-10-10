package com.example.puzzlegame;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class SlicingImage {
    static ArrayList<Bitmap> imageChunksStorageList = new ArrayList<>();
    public static Bitmap hint;
    public SlicingImage(){}


    public void splitImage(Drawable image, int rows, int columns, int imageSize, int pieaceSize) {
        imageChunksStorageList.clear();
        int chunkHeight, chunkWidth;
        Bitmap slicedImage;
        BitmapDrawable drawable = (BitmapDrawable) image;
        Bitmap bitmap = drawable.getBitmap();
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageSize, imageSize, true);
        hint=scaledBitmap;
        chunkHeight = pieaceSize;
        chunkWidth = pieaceSize;

        int yCoord = 0;
        int xCoord = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                slicedImage = Bitmap.createBitmap(scaledBitmap, xCoord, yCoord, chunkWidth, chunkHeight);
                imageChunksStorageList.add(slicedImage);
                xCoord += chunkWidth;
            }
            yCoord +=+ chunkHeight;
            xCoord = 0;
        }
    }
}
