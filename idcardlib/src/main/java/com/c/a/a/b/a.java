package com.c.a.a.b;

import android.graphics.Rect;
import android.os.Handler;
import com.ym.idcard.reg.NativeOcr;
import java.io.UnsupportedEncodingException;

public class a {
  protected long[] a = null;
  protected long[] b = null;
  protected long[] c = null;
  protected long d = 0L;
  protected long e = 0L;
  protected long f = 0L;
  protected long g = 0L;
  protected NativeOcr h = null;

  public a(Handler var1) {
    this.a = new long[1];
    this.b = new long[1];
    this.h = new NativeOcr(var1);
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

  public void a(long var1, String var3) {
    if (this.h != null) {
      StringBuffer var4 = new StringBuffer(var3);
      var4.append('0');
      byte[] var5 = (byte[])null;

      try {
        var5 = var4.toString().getBytes("gbk");
      } catch (UnsupportedEncodingException var7) {
        var7.printStackTrace();
      }

      var5[var5.length - 1] = 0;
      this.h.SaveImage(var1, var5);
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

  public long a(int[] var1) {
    return this.h.GetHeadInfo(var1);
  }

  public long a(long var1, int[] var3) {
    long var4 = -1L;
    if (this.h != null) {
      var4 = this.h.DupImage(var1, var3);
    }

    return var4;
  }

  public void a(int var1) {
    this.h.ClearAll(var1);
  }
}
