package com.b.b;

import com.ym.ocr.img.NativeImage;

public class a {
  protected long a = 0L;
  protected NativeImage b = null;

  public a() {
    this.b = new NativeImage();
    this.a = this.b.createEngine();
  }
@Override
  public void finalize() {
    if (this.b != null && this.a != 0L) {
      this.b.freeImage(this.a);
      this.b.closeEngine(this.a);
      this.a = 0L;
    }

  }

  public boolean a(int var1, int var2) {
    if (this.b != null) {
      int var3 = this.b.initImage(this.a, var1, var2);
      if (var3 == 1) {
        return true;
      }
    }

    return false;
  }

  public boolean a(byte[] var1) {
    if (this.b != null) {
      int var2 = this.b.loadmemjpg(this.a, var1, var1.length);
      if (var2 == 1) {
        return true;
      }
    }

    return false;
  }

  public long a() {
    return this.b != null ? this.b.getImageDataEx(this.a) : 0L;
  }

  public int b() {
    return this.b != null ? this.b.getImageWidth(this.a) : 0;
  }

  public int c() {
    return this.b != null ? this.b.getImageHeight(this.a) : 0;
  }

  public int d() {
    return this.b != null ? this.b.getImageComponent(this.a) : 0;
  }
}
