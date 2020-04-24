package com.msd.ocr.idcard.id;

import android.graphics.Point;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class a {
  private static final Pattern a = Pattern.compile(";");

  public static Point a(Parameters var0, Point var1) {
    List var2 = var0.getSupportedPreviewSizes();
    if (var2 == null) {
      Log.w("CameraConfiguration", "Device returned no supported preview sizes; using default");
      Size var21 = var0.getPreviewSize();
      if (var21 == null) {
        throw new IllegalStateException("Parameters contained no preview size!");
      } else {
        return new Point(var21.width, var21.height);
      }
    } else {
      if (Log.isLoggable("CameraConfiguration", Log.INFO)) {
        StringBuilder var3 = new StringBuilder();
        Iterator var4 = var2.iterator();

        while(var4.hasNext()) {
          Size var5 = (Size)var4.next();
          var3.append(var5.width).append('x').append(var5.height).append(' ');
        }

        Log.i("CameraConfiguration", "Supported preview sizes: " + var3);
      }

      double var20 = (double)var1.x / (double)var1.y;
      int var22 = 0;
      Size var6 = null;
      Iterator var7 = var2.iterator();

      Size var8;
      while(var7.hasNext()) {
        var8 = (Size)var7.next();
        int var9 = var8.width;
        int var10 = var8.height;
        int var11 = var9 * var10;
        if (var11 >= 153600) {
          boolean var12 = var9 < var10;
          int var13 = var12 ? var10 : var9;
          int var14 = var12 ? var9 : var10;
          double var15 = (double)var13 / (double)var14;
          double var17 = Math.abs(var15 - var20);
          if (var17 <= 0.15D) {
            if (var13 == var1.x && var14 == var1.y) {
              Point var19 = new Point(var9, var10);
              Log.i("CameraConfiguration", "Found preview size exactly matching screen size: " + var19);
              return var19;
            }

            if (var11 > var22) {
              var22 = var11;
              var6 = var8;
            }
          }
        }
      }

      if (var6 != null) {
        Point var23 = new Point(var6.width, var6.height);
        Log.i("CameraConfiguration", "Using largest suitable preview size: " + var23);
        return var23;
      } else {
        var8 = var0.getPreviewSize();
        if (var8 == null) {
          throw new IllegalStateException("Parameters contained no preview size!");
        } else {
          Point var24 = new Point(var8.width, var8.height);
          Log.i("CameraConfiguration", "No suitable preview sizes, using default: " + var24);
          return var24;
        }
      }
    }
  }
}
