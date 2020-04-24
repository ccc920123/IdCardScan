package com.ym.idcard.reg;

import android.util.Log;

public class NativeOcrPp {
  private static final String TAG = "NativeOcr";
  private static final String LIB = "PassportEngine";
  private static int mProgress;
  private static boolean mCancel;

  static {
    try {
      System.loadLibrary("PassportEngine");
    } catch (Exception var1) {
      Log.e("ocrengine", "", var1);
    }

    mProgress = 0;
    mCancel = false;
  }

  public NativeOcrPp() {
  }


  public static int getProgress() {
    return mProgress;
  }

  public static int Progress(int var0, int var1) {
    if (var1 != 0) {
      mProgress += var0;
    } else {
      mProgress = var0;
    }

    return mProgress;
  }

  public native int startBCR(long[] var1, byte[] var2, byte[] var3, int var4, byte[] var5);

  public native int closeBCR(long[] var1);

  public native int doImageBCR(long var1, long var3, long[] var5);

  public native int freeImage(long var1, long[] var3);

  public native int imageChecking(long var1, long var3, int var5);

  public native void freeBField(long var1, long var3, int var5);

  public native void setProgressFunc(long var1, boolean var3);

  public native long loadImageMem(long var1, long var3, int var5, int var6, int var7);

  public native int getFieldId(long var1);

  public native int getFieldText(long var1, byte[] var3, int var4);

  public native long getNextField(long var1);

  public native int getFieldRect(long var1, int[] var3);

  public native int codeConvert(long var1, byte[] var3, int var4);

  public native int setoption(long var1, byte[] var3, byte[] var4);

  public native int LicenseStr(byte[] var1);

  public native int GetOcrResult(long var1, long[] var3, byte[] var4);
}
