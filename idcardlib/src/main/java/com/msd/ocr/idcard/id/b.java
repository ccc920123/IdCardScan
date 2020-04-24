package com.msd.ocr.idcard.id;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.CardApplication;
import com.cdjysd.idcardlib.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
  private Context a;
  private com.c.a.a.a.b b;
  private boolean c;
  private Handler d;
  private int e;
  private String f;
  private String g;
  private Handler h = new Handler() {
    @Override
    public void handleMessage(Message var1) {
      switch(var1.what) {
        case 2:
          com.a.a.d.a var2 = (com.a.a.d.a)var1.obj;

          try {
            JSONObject var3 = new JSONObject();
            var3.put("name", var2.e());
            var3.put("sex", var2.f());
            var3.put("folk", var2.g());
            var3.put("birt", var2.h());
            var3.put("addr", var2.i());
            var3.put("num", var2.d());
            var3.put("issue", var2.j());
            var3.put("valid", var2.k());
            var3.put("type", var2.j().length() == 0 ? "1" : "0");
            var3.put("imgPath", b.this.g);
            String var4 = var3.toString();
            Intent var5 = new Intent();
            var5.putExtra("OCRResult", var4);
            var5.putExtra("headImg", b.this.f);
            var5.putExtra("fullImg", b.this.g);
            Message var6 = Message.obtain();
            var6.what = 2;
            var6.obj = var5;
            b.this.d.sendMessage(var6);
          } catch (JSONException var7) {
            var7.printStackTrace();
          }
          break;
        case 3:
        case 9:
        default:
          b.this.d.sendEmptyMessage(6);
          break;
        case 204:
          b.this.d.sendEmptyMessage(3);
      }

    }
  };
  private Handler i = new Handler() {
    @Override
    public void handleMessage(Message var1) {
      super.handleMessage(var1);
      Intent var2 = new Intent();
      switch(var1.what) {
        case 201:
          b.this.i.removeMessages(200);
          b.this.i.removeMessages(206);
          String var3 = "";
          String var4 = "";
          long var5 = System.currentTimeMillis();
          var3 = CardApplication.path + "/" + var5 + "-full.jpg";
          var4 = CardApplication.path+ "/" + var5 + "-head.jpg";
          com.c.a.a.c.a var7 = b.this.b.a(var3, var4);
          String var8 = null;

          try {
            var8 = new String(var7.a(), "gbk");
            var8 = var8.replaceAll("\r\n", "");
            var8 = var8.replaceAll("\"value\"", "");
            var8 = var8.replaceAll("[{]", "");
            var8 = var8.replaceAll("[}]", "");
            var8 = var8.replaceAll("::", ":");
            var8 = var8.replace("Name", "name");
            var8 = var8.replace("Sex", "sex");
            var8 = var8.replace("Folk", "folk");
            var8 = var8.replace("Birt", "birt");
            var8 = var8.replace("Addr", "addr");
            var8 = var8.replace("Num", "num");
            var8 = var8.replace("Issue", "issue");
            var8 = var8.replace("Valid", "valid");
            var8 = var8.trim();
            int var9 = var8.indexOf("issue");
            String var10 = var8.substring(var9 + 8, var8.indexOf(",", var9));

            try {
              if (!b.this.c) {
                File var11 = new File(var4);
                var11.delete();
                var11 = new File(var3);
                var11.delete();
                var4 = "";
                var3 = "";
              }
            } catch (Exception var12) {
              var12.printStackTrace();
            }

            if (var10.equals("\"")) {
              var8 = "{\"type\":\"1\",\"imgPath\":\"" + var3 + "\",\"headPath\":\"" + var4 + "\"," + var8 + "}";
            } else {
              var8 = "{\"type\":\"0\",\"imgPath\":\"" + var3 + "\"," + var8 + "}";
            }

            var2.putExtra("OCRResult", var8);
            var2.putExtra("headImg", var4);
            var2.putExtra("fullImg", var3);
            Message var14 = Message.obtain();
            var14.what = 2;
            var14.obj = var2;
            b.this.d.sendMessage(var14);
          } catch (Exception var13) {
            var13.printStackTrace();
          }
          break;
        case 202:
          b.this.d.sendEmptyMessage(4);
          break;
        case 203:
        case 205:
        case 206:
        default:
          b.this.d.sendEmptyMessage(var1.what);
          break;
        case 204:
          b.this.d.sendEmptyMessage(3);
          Toast.makeText(b.this.a, R.string.ocr_unauthorized, Toast.LENGTH_LONG).show();
          break;
        case 207:
          b.this.d.sendEmptyMessage(5);
      }

    }
  };

  public b(Context var1, Handler var2, Bundle var3) {
    this.a = var1;
    this.d = var2;
    this.b = new com.c.a.a.a.b(this.i, var1);
    if (var3 != null) {
      this.c = var3.getBoolean("saveImage", false);
      this.e = var3.getInt("type", 0);
    }

  }

  public void a() {
    try {
      this.b.a();
      this.d = null;
    } catch (Exception var2) {
    }

  }

  public void a(Rect var1, int var2, int var3, byte[] var4) {
    this.b.a(var4, var2, var3, var1);
  }

  public void a(String var1) {
    try {
      Bitmap var2 = com.msd.ocr.idcard.b.b.a(this.a, var1);
      ByteArrayOutputStream var3 = new ByteArrayOutputStream();
      var2.compress(CompressFormat.JPEG, 100, var3);
      byte[] var4 = var3.toByteArray();
      Rect var5 = new Rect(0, 0, var2.getWidth(), var2.getHeight());
      com.a.a.a.a var6 = new com.a.a.a.a();
      var6.a(true);
      var6.a(var2.getWidth(), var2.getHeight());
      this.g = var1;
      this.f = CardApplication.path + "/" + System.currentTimeMillis() + "-head.jpg";
      com.a.a.a.b var7 = new com.a.a.a.b(this.h, var4, var6, var5, true, true, "", this.f);
      var7.run();
    } catch (Exception var8) {
      var8.printStackTrace();
      this.h.sendEmptyMessage(3);
    }

  }
}
