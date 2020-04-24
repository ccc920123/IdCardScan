package com.a.a.a;

import android.util.Log;
import java.io.UnsupportedEncodingException;

public class c {

  public static String a(byte[] var0) {
    String var1 = null;

    try {
      byte[] var2 = b(var0);
      if (var2 != null) {
        var1 = new String(var2, "GBK");
      } else {
        var1 = "";
      }
    } catch (UnsupportedEncodingException var3) {
      Log.e("convert", var3.toString());
    }

    return var1.trim();
  }

  public static byte[] b(byte[] var0) {
    int var1 = c(var0);
    if (var1 < 1) {
      return null;
    } else {
      byte[] var2 = new byte[var1];
      int var3 = 0;

      for(int var4 = 0; var3 < var1; ++var3) {
        if (var0[var3] != 13) {
          var2[var4++] = var0[var3];
        }
      }

      return var2;
    }
  }

  public static int c(byte[] var0) {
    int var1 = -1;
    if (var0 != null) {
      if (var0.length == 0) {
        var1 = 0;
      } else {
        for(int var2 = 0; var2 < var0.length; ++var2) {
          if (var0[var2] == 0) {
            var1 = var2;
            break;
          }
        }
      }
    }

    return var1;
  }

  public static byte[] a(String var0) {
    Object var1 = null;

    byte[] var2;
    try {
      int var3 = var0.length();
      byte[] var4 = var0.getBytes("US-ASCII");
      var2 = new byte[var3 + 1];

      for(int var5 = 0; var5 < var3; ++var5) {
        var2[var5] = var4[var5];
      }

      var2[var3] = 0;
    } catch (UnsupportedEncodingException var6) {
      Log.e("convert", var6.toString());
      var2 = null;
    }

    return var2;
  }
}
