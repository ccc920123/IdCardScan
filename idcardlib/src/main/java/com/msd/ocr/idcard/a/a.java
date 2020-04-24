package com.msd.ocr.idcard.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.cdjysd.idcardlib.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class a {
  private Object a;
  private String[] b;
  private String c;
  private int d;
  @StringRes
  private int e = R.string.cancel;
  @StringRes
  private int f = R.string.setting;

  private a(Object var1) {
    this.a = var1;
  }

  public static com.msd.ocr.idcard.a.a a(Activity var0) {
    return new com.msd.ocr.idcard.a.a(var0);
  }

  public com.msd.ocr.idcard.a.a a(String... var1) {
    this.b = var1;
    return this;
  }

  public com.msd.ocr.idcard.a.a a(String var1) {
    this.c = var1;
    return this;
  }

  public com.msd.ocr.idcard.a.a a(int var1) {
    this.d = var1;
    return this;
  }

  public void a() {
    a(this.a, this.c, this.e, this.f, this.d, this.b);
  }

  public static boolean a(Context var0, String... var1) {
    if (!com.msd.ocr.idcard.a.b.a()) {
      return true;
    } else {
      String[] var2 = var1;
      int var3 = var1.length;

      for(int var4 = 0; var4 < var3; ++var4) {
        String var5 = var2[var4];
        boolean var6 = ContextCompat.checkSelfPermission(var0, var5) == 0;
        if (!var6) {
          return false;
        }
      }

      return true;
    }
  }

  public static void a(final Object var0, String var1, @StringRes int var2, @StringRes int var3, final int var4, String... var5) {
    a(var0);
    com.msd.ocr.idcard.a.a.PermissionsResultCallback var6 = (com.msd.ocr.idcard.a.a.PermissionsResultCallback)var0;
    if (!com.msd.ocr.idcard.a.b.a()) {
      var6.requestSuccess(var4, Arrays.asList(var5));
    } else {
      List var7 = com.msd.ocr.idcard.a.b.a(com.msd.ocr.idcard.a.b.a(var0), var5);
      boolean var8 = false;

      String var10;
      for(Iterator var9 = var7.iterator(); var9.hasNext(); var8 = var8 || com.msd.ocr.idcard.a.b.a(var0, var10)) {
        var10 = (String)var9.next();
      }

      if (com.msd.ocr.idcard.a.b.a(var7)) {
        var6.requestSuccess(var4, Arrays.asList(var5));
      } else {
        final String[] var12 = (String[])var7.toArray(new String[var7.size()]);
        if (var8) {
          Activity var13 = com.msd.ocr.idcard.a.b.a(var0);
          if (null == var13) {
            return;
          }

          AlertDialog var11 = (new AlertDialog.Builder(var13)).setMessage(var1).setPositiveButton(var2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface var1, int var2) {
              com.msd.ocr.idcard.a.a.b(var0, var12, var4);
            }
          }).setCancelable(false).create();
          var11.setCanceledOnTouchOutside(false);
          var11.show();
        } else {
          b(var0, var12, var4);
        }
      }

    }
  }

  @TargetApi(23)
  private static void b(Object var0, String[] var1, int var2) {
    a(var0);
    if (var0 instanceof Activity) {
      ActivityCompat.requestPermissions((Activity)var0, var1, var2);
    } else if (var0 instanceof Fragment) {
      ((Fragment)var0).requestPermissions(var1, var2);
    }

  }

  public static void a(Object var0, int var1, String[] var2, int[] var3) {
    a(var0);
    com.msd.ocr.idcard.a.a.PermissionsResultCallback var4 = (com.msd.ocr.idcard.a.a.PermissionsResultCallback)var0;
    ArrayList var5 = new ArrayList();

    for(int var6 = 0; var6 < var3.length; ++var6) {
      if (var3[var6] != 0) {
        var5.add(var2[var6]);
      }
    }

    if (com.msd.ocr.idcard.a.b.a(var5)) {
      var4.requestSuccess(var1, Arrays.asList(var2));
    } else {
      var4.requestFailed(var1, var5);
    }

  }

  public static boolean a(final Object var0, String var1, @StringRes int var2, @StringRes int var3, @Nullable DialogInterface.OnClickListener var4, List<String> var5) {
    Iterator var7 = var5.iterator();

    boolean var6;
    do {
      if (!var7.hasNext()) {
        return false;
      }

      String var8 = (String)var7.next();
      var6 = com.msd.ocr.idcard.a.b.a(var0, var8);
    } while(var6);

    final Activity var9 = com.msd.ocr.idcard.a.b.a(var0);
    if (null == var9) {
      return true;
    } else {
      AlertDialog var10 = (new AlertDialog.Builder(var9)).setMessage(var1).setPositiveButton(var2, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface var1, int var2) {
          Intent itt = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
          Uri var4 = Uri.fromParts("package", var9.getPackageName(), (String)null);
          itt.setData(var4);
          com.msd.ocr.idcard.a.a.b(var0, itt);
        }
      }).setNegativeButton(var3, var4).setCancelable(false).create();
      var10.setCanceledOnTouchOutside(false);
      var10.show();
      return true;
    }
  }

  @TargetApi(11)
  private static void b(Object var0, Intent var1) {
    if (var0 instanceof Activity) {
      ((Activity)var0).startActivityForResult(var1, 16061);
    } else if (var0 instanceof Fragment) {
      ((Fragment)var0).startActivityForResult(var1, 16061);
    } else if (var0 instanceof android.app.Fragment) {
      ((android.app.Fragment)var0).startActivityForResult(var1, 16061);
    }

  }

  private static void a(Object var0) {
    if (!(var0 instanceof Fragment) && !(var0 instanceof Activity) && !(var0 instanceof android.app.Fragment)) {
      throw new IllegalArgumentException("Caller must be an Activity or a Fragment.");
    } else if (!(var0 instanceof com.msd.ocr.idcard.a.a.PermissionsResultCallback)) {
      throw new IllegalArgumentException("Caller must implement PermissionCallback.");
    }
  }

  public interface PermissionsResultCallback extends OnRequestPermissionsResultCallback {
    //全部授权
    void requestSuccess(int var1, List<String> var2);
   //还有为授权的权限
    void requestFailed(int var1, List<String> var2);
  }
}
