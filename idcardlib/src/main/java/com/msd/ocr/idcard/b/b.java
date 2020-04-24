package com.msd.ocr.idcard.b;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.DisplayMetrics;

public class b {
  public static Bitmap a(Context var0, String var1) {
    DisplayMetrics var2 = var0.getResources().getDisplayMetrics();
    int var3 = var2.widthPixels > var2.heightPixels ? var2.widthPixels : var2.heightPixels;
    Options var4 = new Options();
    var4.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(var1, var4);
    int var5 = var4.outWidth;
    int var6 = var4.outHeight;
    int var7 = 1;
    if (var5 > var3 || var6 > var3) {
      int var8 = var5 / var3;
      int var9 = var6 / var3;
      if (var8 > var9 & var9 >= 1) {
        var7 = var8;
      }

      if (var9 > var8 & var8 >= 1) {
        var7 = var9;
      }
    }

    var4.inJustDecodeBounds = false;
    var4.inSampleSize = var7;
    return BitmapFactory.decodeFile(var1, var4);
  }
}
