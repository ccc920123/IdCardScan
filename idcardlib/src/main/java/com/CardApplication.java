package com;

import android.app.ActivityManager;
import android.app.Application;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Environment;
import android.os.Process;
import java.util.Iterator;
import java.util.List;

public class CardApplication extends Application {
  public static String path = "";
@Override
  public void onCreate() {
    super.onCreate();
    String var1 = a(this, Process.myPid());
    if (var1 != null) {
      boolean var2 = var1.equals(this.getPackageName());
      if (var2) {
          path = a(this);
      }
    }

  }

  public static String a(Context var0, int var1) {
    ActivityManager var2 = (ActivityManager)var0.getSystemService(Context.ACTIVITY_SERVICE);
    List var3 = var2.getRunningAppProcesses();
    if (var3 == null) {
      return null;
    } else {
      Iterator var4 = var3.iterator();

      while(var4.hasNext()) {
        RunningAppProcessInfo var5 = (RunningAppProcessInfo)var4.next();
        if (var5.pid == var1) {
          return var5.processName;
        }
      }

      return null;
    }
  }

  public static String a(Context var0) {
    String var1 = null;
    if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable()) {
      var1 = var0.getCacheDir().getPath();
    } else {
      var1 = var0.getExternalCacheDir().getPath();
    }

    return var1;
  }
}
