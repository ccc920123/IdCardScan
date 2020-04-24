package com.c.a.a.a;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import com.c.a.a.b.a;
import java.io.IOException;
import java.io.InputStream;

public class b {
  private String a = "ocr_manager";
  private a b;
  private Handler c;
  private byte[] d;

  public b(Handler var1, Context var2) {
    this.c = var1;
    this.b = new a(this.c);
    this.d = new byte[256];
    AssetManager var3 = var2.getAssets();

    try {
      InputStream var4 = var3.open("license.info");
      var4.read(this.d);
      var4.close();
    } catch (IOException var5) {
    }

  }

  public void a(byte[] var1, int var2, int var3, Rect var4) {
    Log.d("tag", "------start----");
    int var5 = this.b.a(var1, var2, var3, var4, this.d);
    if (var5 == 100) {
      this.c.sendEmptyMessage(203);
    } else if (var5 == 200) {
      this.c.sendEmptyMessage(204);
    } else if (var5 < 0) {
      this.c.sendEmptyMessage(205);
    } else {
      this.c.sendMessage(this.c.obtainMessage(207, var5));
    }

    Log.d("tag", "------end---->" + var5);
  }

  public com.c.a.a.c.a a(String var1, String var2) {
    com.c.a.a.c.a var3 = new com.c.a.a.c.a();
    Log.d("tag", "-------result-4----->>");
    byte[] var4 = new byte[1024];
    this.b.a(var4);
    Log.d("tag", "-------result-4-1---->>" + var4.toString());
    int[] var5 = new int[4];
    long var6 = this.b.a(var5);
    this.b.a(var6, var1);
    Log.d("tag", var5[0] + "<----->>" + var5[1]);
    if (var5[0] != 0) {
      long var8 = this.b.a(var6, var5);
      this.b.a(var8, var2);
      var3.b(var2);
    } else {
      var3.b("");
    }

    var3.a(var1);
    var3.a(var4);
    return var3;
  }

  public void a() {
    this.b.a(1);
  }
}
