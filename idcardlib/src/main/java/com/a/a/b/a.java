package com.a.a.b;

import com.ym.ocr.img.NativeImage;

public class a {
  protected long a = 0L;
  
  protected long b = 0L;
  
  protected NativeImage c = null;
  
  public a() {
    this.c = new NativeImage();
    this.a = this.c.createEngine();
  }
  @Override
  public void finalize() {
    if (this.c != null && this.a != 0L) {
      this.c.freeImage(this.a);
      this.c.closeEngine(this.a);
      this.a = 0L;
      this.b = 0L;
    } 
  }
  
  public boolean a(int paramInt1, int paramInt2) {
    if (this.c != null) {
      int i = this.c.initImage(this.a, paramInt1, paramInt2);
      if (i == 1)
        return true; 
    } 
    return false;
  }
  
  public boolean a(byte[] paramArrayOfByte) {
    if (this.c != null) {
      int i = this.c.loadmemjpg(this.a, paramArrayOfByte, paramArrayOfByte.length);
      if (i == 1)
        return true; 
    } 
    return false;
  }
  
  public int a() { return (this.c != null) ? this.c.getImageWidth(this.a) : 0; }
  
  public int b() { return (this.c != null) ? this.c.getImageHeight(this.a) : 0; }
  
  public int c() { return (this.c != null) ? this.c.getImageComponent(this.a) : 0; }
  
  public long d() { return (this.c != null) ? this.c.getImageDataEx(this.a) : 0L; }
}

