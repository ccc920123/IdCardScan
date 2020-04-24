package a.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.ym.idcard.reg.NativeOcrIn;

public class LiceseKeyCheck {
    private final static String KEY = "com.msd.ocr.LICENSE_KEY";//26f1f6a0d4d7cb0dd0e9b28f4cedef83
    private static NativeOcrIn b = new NativeOcrIn();

    public static String a(Context var0) {
//        String var1 = var0.getPackageName();
//        String var2 = c.a(var0);

        try {
//            ApplicationInfo var3 = var0.getPackageManager().getApplicationInfo(var1,128);
//            String var4 = var3.metaData.getString(KEY);
            //开始破解so
            String var1 = "com.tomcat.ocr.idcard";//包名
            String var2 = "19:F4:E8:B7:A9:EE:A3:67:90:07:B0:C6:56:8D:AD:60:44:AB:6D:C9";//MD5
            String var4 = "26f1f6a0d4d7cb0dd0e9b28f4cedef83";
            Log.d("ocr", "package: " + var1);
            Log.d("ocr", "sha1: " + var2);
            Log.d("ocr", "licens: " + var4);
            if (var4 != null && b.start(var1, var2, var4) != 0) {
                return "";
            } else {
                Log.e("ocr", "OCR_API_KEY ERROR");
                return "OCR_API_KEY ERROR";
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            return "OCR_API_KEY ERROR";
        }
    }
//判读是否授权
    public static boolean a() {
        return b.res();
    }
}
