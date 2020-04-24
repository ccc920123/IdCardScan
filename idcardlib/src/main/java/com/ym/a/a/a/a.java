package com.ym.a.a.a;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

public class a {
  private String a = "ocr_manager";
  private com.ym.a.a.b.a b;
  private Handler c;
  private byte[] d;

  public a(Handler var1, Context var2) {
    this.c = var1;
    this.b = new com.ym.a.a.b.a(this.c);
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

  public com.ym.a.a.c.a a(String var1) {
    com.ym.a.a.c.a var2 = new com.ym.a.a.c.a();
    Log.d("tag", "-------result-4----->>");
    byte[] var3 = new byte[1024];
    this.b.a(var3);
    Log.d("tag", "-------result-4-1---->>" + var3.toString());
    this.b.a(var1);
    var2.a(var1);
    var2.a(var3);
    return var2;
  }
}
