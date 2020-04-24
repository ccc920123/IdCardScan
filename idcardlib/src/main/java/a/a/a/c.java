package a.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class c {
    public static String a(Context var0) {
        PackageManager var1 = var0.getPackageManager();
        String var2 = var0.getPackageName();
        PackageInfo var3 = null;

        try {
            var3 = var1.getPackageInfo(var2, 64);
        } catch (PackageManager.NameNotFoundException var16) {
            var16.printStackTrace();
        }

        Signature[] var4 = var3.signatures;
        byte[] var5 = var4[0].toByteArray();
        ByteArrayInputStream var6 = new ByteArrayInputStream(var5);
        CertificateFactory var7 = null;

        try {
            var7 = CertificateFactory.getInstance("X509");
        } catch (CertificateException var15) {
            var15.printStackTrace();
        }

        X509Certificate var8 = null;

        try {
            var8 = (X509Certificate)var7.generateCertificate(var6);
        } catch (CertificateException var14) {
            var14.printStackTrace();
        }

        String var9 = null;

        try {
            MessageDigest var10 = MessageDigest.getInstance("SHA1");
            byte[] var11 = var10.digest(var8.getEncoded());
            var9 = a(var11);
        } catch (NoSuchAlgorithmException var12) {
            var12.printStackTrace();
        } catch (CertificateEncodingException var13) {
            var13.printStackTrace();
        }

        return var9;
    }

    private static String a(byte[] var0) {
        StringBuilder var1 = new StringBuilder(var0.length * 2);

        for(int var2 = 0; var2 < var0.length; ++var2) {
            String var3 = Integer.toHexString(var0[var2]);
            int var4 = var3.length();
            if (var4 == 1) {
                var3 = "0" + var3;
            }

            if (var4 > 2) {
                var3 = var3.substring(var4 - 2, var4);
            }

            var1.append(var3.toUpperCase());
            if (var2 < var0.length - 1) {
                var1.append(':');
            }
        }

        return var1.toString();
    }
}
