package com.msd.ocr.idcard;

import a.a.a.LiceseKeyCheck;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;

import com.CardApplication;
import com.msd.ocr.idcard.id.ICVideoActivity;
import com.msd.ocr.idcard.id.b;

/**
 * 描述：程序的启动入口
 * 编写人：陈渝金-pc:chenyujin
 * 时间： 2020/4/16 10:28
 * 修改人：
 * 修改时间：
 */
public class LibraryInitOCR {
  public static final int DECODE_SUCCESS = 2;
  public static final int DECODE_UNAUTHORIZED = 3;
  public static final int DECODE_AUTO_FOCUS = 4;
  public static final int DECODE_LINE_IN_RECT = 5;
  public static final int DECODE_FAIL = 6;
  private static b idCardDecode;

  public LibraryInitOCR() {
  }

  public static void initOCR(Context var0) {
    try {
      LiceseKeyCheck.a(var0);
      CardApplication.path = CardApplication.a(var0);
    } catch (Exception var2) {
      var2.printStackTrace();
    }

  }

  public static void startScan(Activity var0, boolean var1, int var2) {
    ICVideoActivity.a(var0, var1, var2);
  }

  public static void startScan(Fragment var0, boolean var1, int var2) {
    ICVideoActivity.a(var0, var1, var2);
  }

//  public static void startScan(Fragment var0, boolean var1, int var2) {
//    ICVideoActivity.a(var0, var1, var2);
//  }

  public static void startScan(Object var0, Bundle var1) {
    if (!(var0 instanceof Activity) && !(var0 instanceof Fragment)) {
      throw new RuntimeException("context error");
    } else {
      ICVideoActivity.a(var0, var1);
    }
  }

  public static void initDecode(Context var0, Handler var1, boolean var2) {
    Bundle var3 = new Bundle();
    var3.putBoolean("saveImage", var2);
    initDecode(var0, var1, var3);
  }

  public static void initDecode(Context var0, Handler var1, Bundle var2) {
    idCardDecode = new b(var0, var1, var2);
  }

  public static void decode(Rect var0, int var1, int var2, byte[] var3) {
    idCardDecode.a(var0, var1, var2, var3);
  }

  public static void decode(String var0) {
    idCardDecode.a(var0);
  }

  public static void closeDecode() {
    if (idCardDecode != null) {
      idCardDecode.a();
      idCardDecode = null;
    }

  }
}
