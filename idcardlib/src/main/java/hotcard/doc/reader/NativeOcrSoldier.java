package hotcard.doc.reader;

import android.util.Log;

public class NativeOcrSoldier {
  private static final String TAG = "SoldierCard";
  private static final String LIB = "SoldierCard";

  static {
    try {
      System.loadLibrary("SoldierCard");
    } catch (Exception var1) {
      Log.e("ocrengine", "", var1);
    }

  }

  public NativeOcrSoldier() {
  }

  public native long loadImageMem(long var1, long var3, int var5, int var6, int var7);

  public native int LicenseStr(byte[] var1);

  public native int HcGrayConvert(long var1, int var3, int var4, int[] var5, int[] var6);

  public native int freeImage(long var1, long[] var3);

  public native int closeOCR(long[] var1);

  public native int startOCR(long[] var1, byte[] var2, byte[] var3, int var4, int var5, byte[] var6);

  public native int DoCarInvoiceOcr(long var1, long[] var3, byte[] var4, int var5);

  public native int GetColumnInfo(long var1, int var3, byte[] var4, int var5, int[] var6);
}
