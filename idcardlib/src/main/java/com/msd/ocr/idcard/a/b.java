package com.msd.ocr.idcard.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

public final class b {
  public static boolean a() {
    return VERSION.SDK_INT >= 23;
  }

  @TargetApi(23)
  public static List<String> a(Activity var0, String... var1) {
    ArrayList var2 = new ArrayList();
    String[] var3 = var1;
    int var4 = var1.length;

    for(int var5 = 0; var5 < var4; ++var5) {
      String var6 = var3[var5];
      if (var0.checkSelfPermission(var6) != 0) {
        var2.add(var6);
      }
    }

    return var2;
  }

  @TargetApi(23)
  public static boolean a(Object var0, String var1) {
    if (var0 instanceof Activity) {
      return ActivityCompat.shouldShowRequestPermissionRationale((Activity)var0, var1);
    } else if (var0 instanceof Fragment) {
      return ((Fragment)var0).shouldShowRequestPermissionRationale(var1);
    } else {
      return var0 instanceof android.app.Fragment ? ((android.app.Fragment)var0).shouldShowRequestPermissionRationale(var1) : false;
    }
  }

  @TargetApi(11)
  public static Activity a(Object var0) {
    if (var0 instanceof Activity) {
      return (Activity)var0;
    } else if (var0 instanceof Fragment) {
      return ((Fragment)var0).getActivity();
    } else {
      return var0 instanceof android.app.Fragment ? ((android.app.Fragment)var0).getActivity() : null;
    }
  }

  public static boolean a(List var0) {
    return var0 == null || var0.isEmpty();
  }
}
