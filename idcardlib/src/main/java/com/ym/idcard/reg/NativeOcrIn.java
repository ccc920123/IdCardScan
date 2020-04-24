package com.ym.idcard.reg;

public class NativeOcrIn {
  static {
    System.loadLibrary("ocr");
  }

  public NativeOcrIn() {
  }

  public native int start(String var1, String var2, String var3);

  public native boolean res();
}
