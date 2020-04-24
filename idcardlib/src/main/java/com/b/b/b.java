package com.b.b;

import android.content.Context;
import android.content.res.AssetManager;
import android.telephony.TelephonyManager;
import com.b.a.a;
import com.ym.idcard.reg.NativeOcrJz;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class b {
  protected long[] a = null;
  protected long[] b = null;
  protected long[] c = null;
  protected long d = 0L;
  protected long e = 0L;
  protected long f = 0L;
  protected NativeOcrJz g = null;
  protected boolean h = false;
  private static boolean i = false;
  private List<String> j = new ArrayList();
  private boolean k = false;
  private boolean l = false;
  private TelephonyManager m;

  public b() {
    this.a = new long[1];
    this.b = new long[1];
    this.c = new long[1];
    this.g = new NativeOcrJz();
  }
@Override
  public void finalize() {
    this.a = null;
    this.b = null;
    this.c = null;
    this.g = null;
    this.d = 0L;
    this.e = 0L;
  }

  private int a(String var1, String var2, int var3, byte[] var4) {
    boolean var5 = false;
    int var6 = this.g.startBCR(this.a, com.b.a.a.a(var2), com.b.a.a.a(var1), var3, var4);
    if (var6 == 1) {
      this.d = this.a[0];
      var5 = true;
    }

    return var6;
  }

  private void a() {
    if (this.a != null && this.g != null) {
      this.g.closeBCR(this.a);
      this.a[0] = 0L;
      this.d = 0L;
    }

  }

  private boolean a(long var1, int var3, int var4, int var5) {
    boolean var6 = false;
    if (var1 != 0L) {
      this.e = this.g.loadImageMem(this.d, var1, var3, var4, var5);
      if (this.e != 0L) {
        this.b[0] = this.e;
        var6 = true;
      }
    }

    return var6;
  }

  private boolean b() {
    boolean var1 = false;
    this.h = false;
    this.g.setoption(this.d, com.b.a.a.b("-fmt"), (byte[])null);
    int var2 = this.g.doImageBCR(this.d, this.e, this.c);
    if (var2 == 1) {
      this.f = this.c[0];
      var1 = true;
    } else if (var2 == 3) {
      this.h = true;
    }

    return var1;
  }

  private boolean c() {
    return this.h;
  }

  private void d() {
    if (this.g != null) {
      this.g.freeImage(this.d, this.b);
      this.b[0] = 0L;
      this.e = 0L;
    }

  }

  private void e() {
    if (this.g != null) {
      this.g.freeBField(this.d, this.c[0], 0);
      this.c[0] = 0L;
      this.f = 0L;
    }

  }

  private boolean f() {
    boolean var1 = false;
    if (this.g != null) {
      int var2 = this.g.imageChecking(this.d, this.e, 0);
      if (var2 == 2) {
        var1 = true;
      }
    }

    return var1;
  }

  private int g() {
    long var1 = this.f;
    return this.g.getFieldId(var1);
  }

  private String a(int var1) {
    long var2 = this.f;
    short var4 = 256;
    byte[] var5 = new byte[var4];
    this.g.getFieldText(var2, var5, var4);
    byte var6 = 2;
    if (var1 == 3 && var6 == 2) {
      this.g.codeConvert(this.d, var5, var1);
      return com.b.a.a.e(var5);
    } else {
      return var6 == 3 ? com.b.a.a.b(var5) : com.b.a.a.a(var5);
    }
  }

  private void h() {
    if (!this.i()) {
      this.f = this.g.getNextField(this.f);
    }

  }

  private boolean i() {
    return this.f == 0L;
  }

  private boolean a(com.b.c.a var1, int var2) {
    if (var1 != null) {
      for(; !this.i(); this.h()) {
        switch(this.g()) {
          case 1:
            var1.a(this.a(var2));
          case 2:
          case 7:
          case 11:
          case 12:
          default:
            break;
          case 3:
            var1.b(this.a(var2));
            break;
          case 4:
            var1.c(this.a(var2));
            break;
          case 5:
            var1.d(this.a(var2));
            break;
          case 6:
            var1.e(this.a(var2));
            break;
          case 8:
            var1.f(this.a(var2));
            break;
          case 9:
            var1.g(this.a(var2));
            break;
          case 10:
            var1.h(this.a(var2));
            break;
          case 13:
            var1.i(this.a(var2));
            break;
          case 14:
            var1.j(this.a(var2));
        }
      }

      return true;
    } else {
      return false;
    }
  }

  public com.b.c.a a(Context var1, byte[] var2) {
    return this.a(var1, 2, var2, (byte[])null);
  }

  private com.b.c.a a(Context var1, int var2, byte[] var3, byte[] var4) {
    com.b.c.a var5 = new com.b.c.a();
    boolean var6;
    if (this.k) {
      if (this.m == null) {
        this.m = (TelephonyManager)var1.getSystemService(Context.TELEPHONY_SERVICE);
      }

      String var7 = this.m.getDeviceId();
      var6 = this.a(var7);
      if (!var6) {
        var5.a(5);
        return var5;
      }
    }

    if (this.l) {
      boolean var12 = this.a(var1);
      if (!var12) {
        var5.a(6);
        return var5;
      }
    }

    byte var13 = 1;
    var6 = false;
    if (var2 == 21) {
      var13 = 3;
    }

    i = false;
    byte[] var8 = new byte[256];
    AssetManager var9 = var1.getAssets();

    try {
      InputStream var10 = var9.open("license.info");
      var10.read(var8);
      var10.close();
    } catch (IOException var11) {
    }

    int var14 = this.a("", "", var2, var8);
    if (var14 == 1) {
      if (var3 != null) {
        var5 = this.a(var3, var6, var13);
      }

      this.a();
    } else {
      switch(var14) {
        case 100:
          var5.a(7);
          break;
        case 200:
          var5.a(8);
          break;
        default:
          var5.a(-2);
      }
    }

    return var5;
  }

  private boolean a(String var1) {
    this.j.add("860486020187290");
    this.j.add("860173019034341");
    this.j.add("356380052217276");
    this.j.add("356380052217284");
    this.j.add("356394051387741");
    this.j.add("356394051387758");
    this.j.add("867591010180784");
    this.j.add("862059010072380");
    this.j.add("356380052316136");
    this.j.add("356380052366396");
    this.j.add("356380053266404");
    this.j.add("356394052367163");
    this.j.add("356394052367171");
    this.j.add("862059010051301");
    this.j.add("356394052361448");
    this.j.add("356394052361455");
    this.j.add("356394051320460");
    this.j.add("356394051320478");
    Iterator var2 = this.j.iterator();

    while(var2.hasNext()) {
      String var3 = var2.next().toString();
      if (var1.equals(var3)) {
        return true;
      }
    }

    return false;
  }

  private boolean a(Context var1) {
    if (this.m == null) {
      this.m = (TelephonyManager)var1.getSystemService(Context.TELEPHONY_SERVICE);
    }

    return this.m.getPhoneType() == 2;
  }

  private com.b.c.a a(byte[] var1, boolean var2, int var3) {
    com.b.c.a var4 = new com.b.c.a();
    com.b.b.a var5 = new com.b.b.a();
    if (var5.a(1, 90) && var5.a(var1)) {
      int var6 = var5.b();
      int var7 = var5.c();
      int var8 = var5.d();
      long var9 = var5.a();
      boolean var11 = this.a(var9, var6, var7, var8);
      var5.finalize();
      var5 = null;
      if (var11) {
        if (var2 && this.f() && !i) {
          this.d();
          var4.a(3);
          return var4;
        }

        if (this.b()) {
          if (this.a(var4, var3)) {
            var4.a(1);
          }

          this.e();
        } else if (this.c()) {
          var4.a(-1);
          this.e();
        }

        this.d();
      }
    }

    return var4;
  }
}
