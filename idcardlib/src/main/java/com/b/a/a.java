package com.b.a;

import java.io.UnsupportedEncodingException;

public class a {

  public static String a(byte[] var0) {
    String var1 = null;

    try {
      byte[] var2 = c(var0);
      if (var2 != null) {
        var1 = new String(var2, "GBK");
      } else {
        var1 = "";
      }
    } catch (UnsupportedEncodingException var3) {
    }

    return var1.trim();
  }

  public static String b(byte[] var0) {
    String var1 = null;

    try {
      byte[] var2 = c(var0);
      if (var2 != null) {
        var1 = new String(var2, "ISO-8859-1");
      } else {
        var1 = "";
      }
    } catch (UnsupportedEncodingException var3) {
    }

    return var1.trim();
  }

  public static byte[] c(byte[] var0) {
    int var1 = d(var0);
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

  public static int d(byte[] var0) {
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
    byte[] var1 = (byte[])null;

    try {
      int var2 = var0.length();
      byte[] var3 = var0.getBytes("US-ASCII");
      var1 = new byte[var2 + 1];

      for(int var4 = 0; var4 < var2; ++var4) {
        var1[var4] = var3[var4];
      }

      var1[var2] = 0;
    } catch (UnsupportedEncodingException var5) {
      var1 = (byte[])null;
    }

    return var1;
  }

  public static String e(byte[] var0) {
    String var1 = null;

    try {
      byte[] var2 = c(var0);
      if (var2 != null) {
        var1 = new String(var2, "big5");
      } else {
        var1 = "";
      }
    } catch (UnsupportedEncodingException var3) {
    }

    return var1.trim();
  }

  public static byte[] b(String var0) {
    byte[] var1 = (byte[])null;

    try {
      byte[] var2 = var0.getBytes("utf-8");
      int var3 = var2.length;
      var1 = new byte[var3 + 1];

      for(int var4 = 0; var4 < var3; ++var4) {
        var1[var4] = var2[var4];
      }

      var1[var3] = 0;
    } catch (UnsupportedEncodingException var5) {
      var1 = (byte[])null;
    }

    return var1;
  }
}
