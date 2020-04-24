package com.ym.idcard.reg;

import a.a.a.LiceseKeyCheck;
import a.a.a.LiceseByte;
import android.os.Handler;

public class NativeOcr {
  private static final String LIB = "IDCardengine";
  private static final String LIB_R10 = "IDCardengine_r10";
  private static Handler mHandler;
  private static int mProgress;
  private static boolean mCancel;

  static {
    try {
      System.loadLibrary("IDCardengine");
    } catch (Exception var1) {
    }

    mProgress = 0;
    mCancel = false;
  }

  public NativeOcr() {
  }

  public NativeOcr(Handler var1) {
    mHandler = var1;
  }

  public native int freeImage(long var1, long[] var3);

  public native int closeOCR(long[] var1);

  public native long GetCardNum(byte[] var1, int var2);

  public native long GetTrnImageThread();

  public native int GetCardNumRectThread(int[] var1);

  public native int GetCharInfoThread(int[] var1, int var2);

  public native int GetCardBinInfo(long var1, byte[] var3, int var4);

  public native int LicenseStr(byte[] var1);

  public native int RecYuvImg(byte[] var1, int var2, int var3, int[] var4, byte[] var5);

  public native int GetResult(byte[] var1, int var2);

  private static int GetState(int var0) {
    if (LiceseKeyCheck.a()) {
      switch(var0) {
        case 1:
          mHandler.sendEmptyMessage(LiceseByte.a(LiceseByte.b));
      }
    } else {
      mHandler.sendEmptyMessage(LiceseByte.a(LiceseByte.a));
    }

    return 1234;
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

  public native int startOCR(long[] var1, byte[] var2, byte[] var3, int var4, int var5);

  public native int startBCRBeiJing(long[] var1, byte[] var2, byte[] var3, int var4, byte[] var5);

  public native int closeBCR(long[] var1);

  public native int doImageBCR(long var1, long var3, long[] var5);

  public native int doLineOCR(long var1, long var3, long[] var5, byte[] var6, int var7);

  public native int imageChecking(long var1, long var3, int var5);

  public native int checkingCopyID(long var1);

  public native void freeBField(long var1, long var3, int var5);

  public native void setProgressFunc(long var1, boolean var3);

  public native long loadImageMem(long var1, long var3, int var5, int var6, int var7);

  public native int getFieldId(long var1);

  public native int getFieldText(long var1, byte[] var3, int var4);

  public native long getNextField(long var1);

  public native int getFieldRect(long var1, int[] var3);

  public native int codeConvert(long var1, byte[] var3, int var4);

  public native int setoption(long var1, byte[] var3, byte[] var4);

  public native int getLastErr(long var1, byte[] var3, int var4);

  public native long DupImage(long var1, int[] var3);

  public native long getheadImgRect(long var1, int[] var3);

  public native long SaveImage(long var1, byte[] var3);

  public native int GetCardType(long var1, long var3);

  public native long GetHeadInfo(int[] var1);

  public native int getheadImg(long var1, long var3, byte[] var5);

  public native int ClearAll(int var1);

  public native int startBCR(long[] var1, byte[] var2, byte[] var3, int var4);

  public native long getYData(byte[] var1, int var2, int var3);

  public native byte CheckCardEdgeLine(long var1, long var3, int[] var5, int var6, int var7, int var8, int var9);

  public native byte[] BImageToImagebyte(long var1);

  public native int BImageToImagebyte(long var1, byte[] var3);

  public native long YuvToRgb(byte[] var1, int var2, int var3, int var4);

  public native long FreeRgb(long var1);

  public native long SetSwitch(long var1, int var3, int var4);
}
