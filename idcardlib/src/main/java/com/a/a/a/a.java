package com.a.a.a;

import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;

public class a {
  private String a = "ocr_manager";
  private com.a.a.b.a b = new com.a.a.b.a();
  private  com.a.a.b.b  c = new com.a.a.b.b();
  private int d = 0;
  private int e = 0;
  private int f = 0;
  private long g = 0L;

  public a() {
    this.d = 0;
    this.e = 0;
    this.f = 0;
    this.g = 0L;
  }

  public void a(int var1, int var2) {
    this.d = var1;
    this.e = var2;
  }

  public boolean a(boolean var1) {
    return this.c.a(com.a.a.c.a.a() + "/ScanBcr_Mo.cfg", com.a.a.c.a.a(), 2, var1) && this.b.a(1, 90);
  }

  public com.a.a.d.a a(byte[] var1, int var2, Rect var3, Handler var4, boolean var5, boolean var6, String var7, String var8) {
    com.a.a.d.a var9 = null;
    if (!var5) {
      long var18 = System.currentTimeMillis();
      long var19 = this.c.a(var1, this.d, this.e, 420);
      if (this.c.a(var19, this.d, this.e, 3)) {
        this.c.a(var19);
        ++this.f;
        if (!this.c.a(var3, var4)) {
          this.g += System.currentTimeMillis() - var18;
          this.c.b();
          var9 = new com.a.a.d.a();
          var9.a(3);
          return var9;
        } else {
          this.g += System.currentTimeMillis() - var18;
          Log.d(this.a, "---------模糊判断开始--y---------");
          if (var6) {
            if (this.c.a(8)) {
              Log.d(this.a, "---------模糊判断失败-x----------");
              this.c.b();
              var9 = new com.a.a.d.a();
              var9.a(4);
              return var9;
            }

            Log.d(this.a, "---------模糊判断通过--y---------");
          }

          var4.sendEmptyMessage(4);
          this.c.b();
          var9 = new com.a.a.d.a();
          var9.a(6);
          return var9;
        }
      } else {
        this.c.a(var19);
        this.c.b();
        var9 = new com.a.a.d.a();
        var9.a(3);
        this.f = 0;
        this.g = 0L;
        return var9;
      }
    } else {
      if (this.b.a(var1)) {
        int var10 = this.b.a();
        int var11 = this.b.b();
        int var12 = this.b.c();
        long var13 = this.b.d();
        if (this.c.a(var13, var10, var11, var12)) {
          Log.d(this.a, "---------模糊判断开始--2---------");
          if (this.c.a(4)) {
            Log.d(this.a, "---------模糊判断失败---2--------");
            this.c.b();
            this.f = 0;
            this.g = 0L;
            return null;
          }

          Log.d(this.a, "---------模糊判断通过---2--------");
          if (this.c.a()) {
            long var15 = this.c.a(new Rect(0, 0, var10, var11));
            var9 = this.c.b(var2);
            var9.a(1);
            ArrayList var17 = new ArrayList();
            var17.add(String.valueOf(this.f));
            var17.add(String.valueOf(this.g));
            var9.a(var17);
            this.f = 0;
            this.g = 0L;
            if (var7 != null && var7.length() > 0) {
              var9.a(var7);
              this.c.a(var15, var9.b());
            }

            if (var8 != null && var8.length() > 0) {
              var9.b(var8);
              this.c.a(this.c.a(var15, this.c.d()), var9.c());
            }

            this.c.c();
          }

          this.c.b();
        }
      }

      return var9;
    }
  }
}
