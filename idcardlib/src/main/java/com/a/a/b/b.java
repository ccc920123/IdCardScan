package com.a.a.b;

import android.graphics.Rect;
import android.os.Handler;

import com.a.a.d.a;
import com.ym.idcard.reg.NativeOcr;
import java.io.UnsupportedEncodingException;

public class b {
  protected long[] a = null;
  protected long[] b = null;
  protected long[] c = null;
  protected long d = 0L;
  protected long e = 0L;
  protected long f = 0L;
  protected long g = 0L;
  protected NativeOcr h = null;

  public b() {
    this.a = new long[1];
    this.b = new long[1];
    this.c = new long[1];
    this.h = new NativeOcr();
  }

  @Override
  public void finalize() {
    this.a = null;
    this.b = null;
    this.c = null;
    this.h = null;
    this.d = 0L;
    this.e = 0L;
    this.f = 0L;
  }

  public boolean a(String var1, String var2, int var3, boolean var4) {
    boolean var5 = false;
    int var6 = this.h.startBCR(this.a, com.a.a.a.c.a(var2), com.a.a.a.c.a(var1), var3);
    if (var6 == 1) {
      this.d = this.a[0];
      if (!var4) {
        this.h.SetSwitch(this.d, 13, 1);
      }

      var5 = true;
    }

    return var5;
  }

  public boolean a(long var1, int var3, int var4, int var5) {
    boolean var6 = false;
    if (var1 != 0L) {
      this.e = this.h.loadImageMem(this.d, var1, var3, var4, var5);
      if (this.e != 0L) {
        this.b[0] = this.e;
        var6 = true;
      }
    }

    return var6;
  }

  public boolean a() {
    boolean var1 = false;
    int var2 = this.h.doImageBCR(this.d, this.e, this.c);
    if (var2 == 1) {
      this.f = this.c[0];
      this.g = this.f;
      var1 = true;
    }

    return var1;
  }

  public void b() {
    if (this.h != null) {
      this.h.freeImage(this.d, this.b);
      this.b[0] = 0L;
      this.e = 0L;
    }

  }

  public void a(long var1) {
    if (this.h != null) {
      this.h.FreeRgb(var1);
    }

  }

  public void c() {
    if (this.h != null) {
      this.h.freeBField(this.d, this.f, 0);
      this.c[0] = 0L;
      this.f = 0L;
      this.g = 0L;
    }

  }

  public boolean a(int var1) {
    boolean var2 = false;
    if (this.h != null) {
      int var3 = this.h.imageChecking(this.d, this.e, var1);
      if (var3 == 2) {
        var2 = true;
      }
    }

    return var2;
  }

  public boolean a(Rect var1, Handler var2) {
    boolean var3 = false;
    if (this.h != null) {
      int[] var4 = new int[]{var1.left, var1.top, var1.right, var1.bottom};
      byte var5 = this.h.CheckCardEdgeLine(this.d, this.e, var4, 40, 30, 0, 0);
      var2.sendMessage(var2.obtainMessage(7, var5));
      if (var5 == 15) {
        var3 = true;
      }
    }

    return var3;
  }

  public long a(Rect var1) {
    if (var1 == null) {
      return this.h.DupImage(this.e, new int[4]);
    } else {
      long var2 = -1L;
      if (this.h != null) {
        int[] var4 = new int[]{var1.left, var1.top, var1.right, var1.bottom};
        var2 = this.h.DupImage(this.e, var4);
      }

      return var2;
    }
  }

  public int[] d() {
    int[] var1 = new int[4];
    if (this.h != null) {
      this.h.getheadImgRect(this.f, var1);
    }

    return var1;
  }

  public long a(long var1, int[] var3) {
    long var4 = -1L;
    if (this.h != null) {
      var4 = this.h.DupImage(var1, var3);
    }

    return var4;
  }

  public void a(long var1, String var3) {
    if (this.h != null) {
      StringBuffer var4 = new StringBuffer(var3);
      var4.append('0');
      byte[] var5 = null;

      try {
        var5 = var4.toString().getBytes("gbk");
      } catch (UnsupportedEncodingException var7) {
        var7.printStackTrace();
      }

      var5[var5.length - 1] = 0;
      this.h.SaveImage(var1, var5);
    }

  }

  private int e() {
    long var1 = this.g;
    return this.h.getFieldId(var1);
  }

  private String c(int var1) {
    long var2 = this.g;
    short var4 = 256;
    byte[] var5 = new byte[var4];
    this.h.getFieldText(var2, var5, var4);
    return com.a.a.a.c.a(var5);
  }

  private void f() {
    if (!this.g()) {
      this.g = this.h.getNextField(this.g);
    }

  }

  private boolean g() {
    return this.g == 0L;
  }

  public a b(int var1) {
    a var2;
    for(var2 = new a(); !this.g(); this.f()) {
      switch(this.e()) {
        case 1:
          var2.d(this.c(var1).replace("姓名", ""));
          break;
        case 3:
          var2.c(this.c(var1).replace("(wrong number)", ""));
          break;
        case 4:
          var2.e(this.c(var1));
          break;
        case 5:
          var2.g(this.c(var1));
          break;
        case 6:
          var2.h(this.c(var1).replace("住址", ""));
          break;
        case 7:
          var2.i(this.c(var1));
          break;
        case 9:
          var2.j(this.c(var1));
          break;
        case 11:
          var2.f(this.c(var1));
          break;
        case 99:
          var2.k(" ");
      }
    }

    return var2;
  }

  public long a(byte[] var1, int var2, int var3, int var4) {
    long var5 = 1L;
    var5 = this.h.YuvToRgb(var1, var2, var3, var4);
    return var5;
  }
}
