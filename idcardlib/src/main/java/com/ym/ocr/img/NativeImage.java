package com.ym.ocr.img;

import android.util.Log;

public class NativeImage {
  private static final String TAG = "NativeImage";
  private static final String LIB = "imageengine";
  private static final String LIB_R10 = "imageengine_r10";

  static {
    try {
      System.loadLibrary("imageengine");
    } catch (Exception var1) {
      Log.e("imageengine", "", var1);
    }

  }

  public NativeImage() {
  }


  public native int initImage(long var1, int var3, int var4);

  public native int loadmemjpg(long var1, byte[] var3, int var4);

  public native int loadImage(long var1, byte[] var3, int var4);

  public native int freeImage(long var1);

  public native long createEngine();

  public native int closeEngine(long var1);

  public native long getImageDataEx(long var1);

  public native int getImageWidth(long var1);

  public native int getImageHeight(long var1);

  public native int getImageComponent(long var1);
}
