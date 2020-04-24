package com.msd.ocr.idcard.id;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;


import com.CardApplication;
import com.cdjysd.idcardlib.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import a.a.a.LiceseKeyCheck;
import androidx.annotation.NonNull;


public class DIVideoActivity extends Activity implements Callback, com.msd.ocr.idcard.a.a.PermissionsResultCallback {
  private final String b = "ocr";
  private Context c;
  private SurfaceView d;
  private SurfaceHolder e;
  private com.c.a.a.a.a f;
  private boolean g = true;
  private ViewfinderView h;
  private com.ym.a.a.a.a i;
  private Rect j;
  private boolean k;
  private boolean l = false;
  private ImageButton m;
  private ImageButton n;
  private ImageButton o;
  private boolean p = false;
  private boolean q = true;
  private boolean r = false;
  private com.msd.ocr.idcard.b.a s;
  private boolean t = false;
  private boolean u = false;
  private OnClickListener v = new OnClickListener() {
    @Override
    public void onClick(View var1) {
      int var2 = var1.getId();
      if (var2 == R.id.bt_cancel) {
        DIVideoActivity.this.finish();
      } else if (var2 == R.id.bt_flash) {
        if (DIVideoActivity.this.u) {
          if (DIVideoActivity.this.f.k()) {
            DIVideoActivity.this.u = false;
          }
        } else if (DIVideoActivity.this.f.j()) {
          DIVideoActivity.this.u = true;
        }
      } else if (var2 == R.id.selectImage && DIVideoActivity.this.t) {
        DIVideoActivity.this.f();
      }

    }
  };
  private Handler w = new Handler() {
    public void handleMessage(Message var1) {
      super.handleMessage(var1);
      Intent var2 = new Intent();
      switch(var1.what) {
        case 200:
          if (DIVideoActivity.this.i == null) {
            DIVideoActivity.this.i = new com.ym.a.a.a.a(DIVideoActivity.this.w, DIVideoActivity.this.c);
            DIVideoActivity.this.j = DIVideoActivity.this.f.a(DIVideoActivity.this.h.getFinder());
          }

          byte[] var3 = (byte[])((byte[])var1.obj);
          DIVideoActivity.this.i.a(var3, DIVideoActivity.this.f.h(), DIVideoActivity.this.f.i(), DIVideoActivity.this.j);
          DIVideoActivity.this.w.sendEmptyMessageDelayed(206, 100L);
          break;
        case 201:
          DIVideoActivity.this.w.removeMessages(200);
          DIVideoActivity.this.w.removeMessages(206);
          String var4 = DIVideoActivity.this.getIntent().getStringExtra("needimg");
          String var5 = "";
          String var6 = "";
          long var7 = System.currentTimeMillis();
          if (var4 == null || var4.equals("true")) {
            var5 = CardApplication.path + "/" + var7 + "-full.jpg";
            var6 = CardApplication.path + "/" + var7 + "-head.jpg";
          }

          try {
            com.ym.a.a.c.a var14 = DIVideoActivity.this.i.a(var5);
            String var10 = new String(var14.a(), "gbk");
            JSONObject var11 = new JSONObject(var10);
            if (!DIVideoActivity.this.p) {
              File var12 = new File(var6);
              var12.delete();
              var12 = new File(var5);
              var12.delete();
              var6 = "";
              var5 = "";
            }

            JSONObject var15 = new JSONObject();
            var15.put("name", var11.optString("Name"));
            var15.put("num", var11.optString("Num"));
            var15.put("sex", var11.optString("Sex"));
            var15.put("birt", var11.optString("Birt"));
            var15.put("addr", var11.optString("Addr"));
            var15.put("nation", var11.optString("Nation"));
            var15.put("startTime", var11.optString("Issue"));
            var15.put("validPeriod", var11.optString("ValidPeriod"));
            var15.put("drivingType", var11.optString("DrivingType"));
            var15.put("registerDate", var11.optString("RegisterDate"));
            var15.put("imgPath", var5);
            var2.putExtra("OCRResult", var15.toString());
            var2.putExtra("fullImg", var5);
            var2.putExtra("headImg", var6);
            DIVideoActivity.this.setResult(-1, var2);
            DIVideoActivity.this.finish();
          } catch (Exception var13) {
            var13.printStackTrace();
          }
          break;
        case 202:
          DIVideoActivity.this.f.g();
          DIVideoActivity.this.w.sendEmptyMessageDelayed(202, 2000L);
        case 203:
        case 205:
          break;
        case 204:
          Toast.makeText(DIVideoActivity.this.getBaseContext(), R.string.ocr_unauthorized, Toast.LENGTH_LONG).show();
          break;
        case 206:
          if (DIVideoActivity.this.g) {
            DIVideoActivity.this.f.g();
            DIVideoActivity.this.g = false;
            DIVideoActivity.this.w.sendEmptyMessageDelayed(206, 500L);
            DIVideoActivity.this.w.sendEmptyMessageDelayed(202, 1500L);
          } else {
            DIVideoActivity.this.f.f();
          }
          break;
        case 207:
          int var9 = (Integer)var1.obj;
          DIVideoActivity.this.h.setLineRect(var9);
          break;
        default:
          DIVideoActivity.this.f.c();
          DIVideoActivity.this.w.sendEmptyMessageDelayed(206, 500L);
          Toast.makeText(DIVideoActivity.this.getBaseContext(), "<>" + var1.what, Toast.LENGTH_SHORT).show();
      }

    }
  };
  String[] a = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
  private ProgressDialog x;
  private String y;
  private String z;
  private Handler A = new Handler() {
    public void handleMessage(Message var1) {
      if (DIVideoActivity.this.x != null && DIVideoActivity.this.x.isShowing()) {
        DIVideoActivity.this.x.dismiss();
      }

      switch(var1.what) {
        case 1:
          try {
            com.b.c.a var2 = (com.b.c.a)var1.obj;
            JSONObject var3 = new JSONObject();
            var3.put("num", var2.c());
            var3.put("name", var2.b());
            var3.put("sex", var2.d());
            var3.put("nation", var2.i());
            var3.put("addr", var2.f());
            var3.put("birt", var2.e());
            var3.put("startTime", var2.g());
            var3.put("drivingType", var2.j());
            var3.put("registerDate", var2.k());
            var3.put("valid", var2.h());
            var3.put("imgPath", DIVideoActivity.this.z);
            String var4 = var3.toString();
            Intent var5 = new Intent();
            var5.putExtra("OCRResult", var4);
            var5.putExtra("headImg", DIVideoActivity.this.y);
            var5.putExtra("fullImg", DIVideoActivity.this.z);
            DIVideoActivity.this.setResult(-1, var5);
            DIVideoActivity.this.finish();
          } catch (JSONException var6) {
            var6.printStackTrace();
          }
          break;
        case 7:
        default:
          Toast.makeText(DIVideoActivity.this.c, R.string.parse_error, Toast.LENGTH_LONG).show();
          break;
        case 8:
        case 204:
          DIVideoActivity.this.w.sendEmptyMessage(var1.what);
      }

    }
  };

  public DIVideoActivity() {
  }
@Override
  protected void onCreate(Bundle var1) {
    super.onCreate(var1);
  requestWindowFeature(Window.FEATURE_NO_TITLE);//
  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
          WindowManager.LayoutParams.FLAG_FULLSCREEN);
  getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
          WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    this.c = this;
    this.s = new com.msd.ocr.idcard.b.a(this);
    this.setContentView(R.layout.activity_card);
    this.b();
    this.p = this.getIntent().getBooleanExtra("saveImage", false);
    Bundle var2 = this.getIntent().getBundleExtra("bundle");
    if (var2 != null) {
      this.q = var2.getBoolean("showSelect", true);
      this.r = var2.getBoolean("showCamera", false);
    }

    if (!this.q) {
      this.o.setVisibility(View.GONE);
    }

    this.d();
  }
  @Override
  protected void onResume() {
    super.onResume();
    if (this.k) {
      this.e();
    }

    if (!LiceseKeyCheck.a()) {
      Toast.makeText(this.getBaseContext(), R.string.ocr_unauthorized, Toast.LENGTH_LONG).show();
    } else {
      this.t = true;
    }

  }
  @Override
  protected void onPause() {
    super.onPause();
    this.w.removeMessages(200);
    this.w.removeMessages(206);
    this.c();
  }

  private void a() {
    this.f.a("off");
    WindowManager var1 = (WindowManager)this.c.getSystemService(Context.WINDOW_SERVICE);
    Display var2 = var1.getDefaultDisplay();
    Point var3 = new Point();
    var2.getSize(var3);
    Camera var4 = this.f.e();
    if (var4 != null) {
      Parameters var6 = var4.getParameters();
      Point var7 = com.msd.ocr.idcard.id.a.a(var6, var3);
      var6.setPreviewSize(var7.x, var7.y);
      var4.setParameters(var6);
    }

    int var8 = this.f.h();
    int var9 = this.f.i();
    this.e = this.d.getHolder();
    this.e.addCallback(this);
    this.e.setType(3);
    this.h.a(var3.x, var3.y, this.w);
  }

  private void b() {
    this.d = (SurfaceView)this.findViewById(R.id.camera_sv);
    this.h = (ViewfinderView)this.findViewById(R.id.camera_finderView);
    this.n = (ImageButton)this.findViewById(R.id.bt_cancel);
    this.m = (ImageButton)this.findViewById(R.id.bt_flash);
    this.o = (ImageButton)this.findViewById(R.id.selectImage);
    this.m.setOnClickListener(this.v);
    this.n.setOnClickListener(this.v);
    this.o.setOnClickListener(this.v);
  }
  @Override
  public void surfaceCreated(SurfaceHolder var1) {
    Log.d("ocr", "surfaceCreated");
    if (!this.f.a()) {
      this.f.b();
      this.a();
    }

  }
  @Override
  public void surfaceChanged(SurfaceHolder var1, int var2, int var3, int var4) {
    if (var1.getSurface() == null) {
      Log.d("ocr", "holder.getSurface() == null");
    } else {
      Log.v("ocr", "surfaceChanged. w=" + var3 + ". h=" + var4);
      this.e = var1;
      this.f.a(this.e);
      this.f.c();
      this.w.sendEmptyMessageDelayed(206, 500L);
    }
  }
  @Override
  public void surfaceDestroyed(SurfaceHolder var1) {
    Log.d("ocr", "surfaceDestroyed");
    this.f.d();
    this.e = null;
  }

  private void c() {
    if (this.f != null) {
      this.f.d();
    }

  }
  @Override
  protected void onDestroy() {
    super.onDestroy();
    this.s.c();
  }

  private void d() {
    if (com.msd.ocr.idcard.a.a.a(this.c, this.a)) {
      this.k = true;
    } else {
      com.msd.ocr.idcard.a.a.a(this).a(this.getString(R.string.rationale_camera)).a(10049).a(this.a).a();
    }

  }

  private void e() {
    if (this.f != null) {
      this.f.d();
    }

    this.f = new com.c.a.a.a.a(this.getBaseContext(), this.w);

    try {
      DIVideoActivity.a var1 = new DIVideoActivity.a();
      var1.start();
      var1.join();
    } catch (Exception var2) {
      this.l = true;
    }

    if (this.l) {
      Toast.makeText(this.getBaseContext(), R.string.rationale_ask_again, Toast.LENGTH_SHORT).show();
      this.finish();
    } else {
      this.a();
    }
  }
  @Override
  public void onRequestPermissionsResult(int var1, @NonNull String[] var2, @NonNull int[] var3) {
    if (var1 == 10049) {
      com.msd.ocr.idcard.a.a.a(this, var1, var2, var3);
    } else {
      super.onRequestPermissionsResult(var1, var2, var3);
    }

  }
@Override
  public void requestSuccess(int var1, List<String> var2) {
    this.w.postDelayed(new Runnable() {
      @Override
      public void run() {
        DIVideoActivity.this.e();
      }
    }, 200L);
  }
@Override
  public void requestFailed(int var1, List<String> var2) {
    boolean var3 = com.msd.ocr.idcard.a.a.a(this, this.getString(R.string.rationale_ask_again), R.string.setting, R.string.cancel, (android.content.DialogInterface.OnClickListener)null, var2);
    if (!var3) {
    }

  }
  @Override
  protected void onActivityResult(int var1, int var2, Intent var3) {
    super.onActivityResult(var1, var2, var3);
    if (var1 == 10050 && var2 == -1) {
      ArrayList var4 = var3.getStringArrayListExtra("select_result");
      if (var4 != null) {
        String var5 = (String)var4.get(0);
        this.a(var5);
      }
    }

  }

  private void a(final String var1) {
    try {
      this.x = new ProgressDialog(this.c);
      this.x.setMessage(this.getString(R.string.parsing));
      this.x.show();
      this.s.b();
      (new Thread() {
        @Override
        public void run() {
          DIVideoActivity.this.b(var1);
        }
      }).start();
    } catch (Exception var3) {
      var3.printStackTrace();
      this.A.sendEmptyMessage(3);
    }

  }

  private void b(String var1) {
    try {
      Bitmap var2 = com.msd.ocr.idcard.b.b.a(this.c, var1);
      ByteArrayOutputStream var3 = new ByteArrayOutputStream();
      var2.compress(CompressFormat.JPEG, 100, var3);
      byte[] var4 = var3.toByteArray();
      new Rect(0, 0, var2.getWidth(), var2.getHeight());
      com.b.b.b var6 = new com.b.b.b();
      com.b.c.a var7 = var6.a(this.c, var4);
      this.A.obtainMessage(var7.a(), var7).sendToTarget();
      this.z = var1;
    } catch (Exception var8) {
      var8.printStackTrace();
      this.A.sendEmptyMessage(3);
    }

  }

  private void f() {
//    MultiImageSelector var1 = MultiImageSelector.create();
//    var1.showCamera(this.r);
//    var1.count(1);
//    var1.setTakePhotosBack(true);
//    var1.single();
//    var1.origin((ArrayList)null);
//    var1.start(this, 10050);
  }

  private class a extends Thread {
    private a() {
    }
@Override
    public void run() {
      try {
        DIVideoActivity.this.f.b();
      } catch (Exception var2) {
        DIVideoActivity.this.l = true;
      }

    }
  }
}
