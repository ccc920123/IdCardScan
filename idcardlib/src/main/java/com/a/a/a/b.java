package com.a.a.a;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;

import a.a.a.LiceseByte;
import a.a.a.LiceseKeyCheck;

public class b extends Thread implements Runnable {
  private Handler handler;
  private Rect b;
  private a c;
  private boolean d;
  private byte[] e = null;
  private boolean f = true;
  private String g;
  private String h;

  public b(Handler var1, byte[] var2, a var3, Rect var4, boolean var5, boolean var6, String var7, String var8) {
    this.handler = var1;
    this.e = var2;
    this.c = var3;
    this.b = var4;
    this.d = var5;
    this.f = var6;
    if (var7.length() > 0) {
      this.g = var7;
    }

    if (var8.length() > 0) {
      this.h = var8;
    }

  }
  @Override
  public void run() {
    Message var1 = this.handler.obtainMessage();
    if (!LiceseKeyCheck.a()) {
      var1.what = LiceseByte.a(LiceseByte.a);
      this.handler.sendMessage(var1);
    } else {
      com.a.a.d.a var2 = this.c.a(this.e, 2, this.b, this.handler, this.d, this.f, this.g, this.h);
      if (this.d) {
        if (var2 == null) {
          this.handler.sendEmptyMessageDelayed(9, 100L);
        } else {
          var1.what = 2;
          var1.obj = var2;
          this.handler.sendMessage(var1);
        }
      } else if (var2 == null) {
        var1.what = 3;
        this.handler.sendMessage(var1);
      } else if (var2.a() != 6) {
        var1.what = 2;
        var1.obj = var2;
        this.handler.sendMessage(var1);
      }

    }
  }
}
