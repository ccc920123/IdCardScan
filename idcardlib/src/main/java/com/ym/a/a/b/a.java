package com.ym.a.a.b;

import android.graphics.Rect;
import android.os.Handler;
import com.ym.idcard.reg.NativeOcrJz;
import java.io.UnsupportedEncodingException;

public class a {
  protected long[] a = null;
  protected long[] b = null;
  protected long[] c = null;
  protected long d = 0L;
  protected long e = 0L;
  protected long f = 0L;
  protected long g = 0L;
  protected NativeOcrJz h = null;

  public a(Handler var1) {
    this.a = new long[1];
    this.b = new long[1];
    this.h = new NativeOcrJz(var1);
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

  public void a(String var1) {
    if (this.h != null) {
      StringBuffer var2 = new StringBuffer(var1);
      var2.append('0');
      byte[] var3 = (byte[])null;

      try {
        var3 = var2.toString().getBytes("gbk");
      } catch (UnsupportedEncodingException var5) {
        var5.printStackTrace();
      }

      var3[var3.length - 1] = 0;
      this.h.GetImage(var3);
    }

  }

  public int a(byte[] var1, int var2, int var3, Rect var4, byte[] var5) {
    int[] var6 = new int[]{var4.left, var4.top, var4.right, var4.bottom};
    int var7 = this.h.RecYuvImg(var1, var2, var3, var6, var5);
    return var7;
  }

  public int a(byte[] var1) {
    int var2 = this.h.GetResult(var1, var1.length);
    return var2;
  }
}
