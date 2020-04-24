package com.c.a.a.a;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import java.io.IOException;

import a.a.a.LiceseKeyCheck;

public class a implements AutoFocusCallback {
  private final String a = "cc_camera";
  private int b = 1400;
  private int c = 1280;
  private Handler d;
  private Camera e;
  private Context f;
  private boolean g = true;
  private AutoFocusCallback h = new AutoFocusCallback() {
    public void onAutoFocus(boolean var1, Camera var2) {
      Log.d("cc_camera", "---autoFoucs-------------->>" + var1);
    }
  };
  private PreviewCallback i = new PreviewCallback() {
    public void onPreviewFrame(byte[] var1, Camera var2) {
      if (!a.this.g) {
        if (LiceseKeyCheck.a()) {
          Message var3 = a.this.d.obtainMessage();
          var3.what = 200;
          var3.obj = var1;
          a.this.d.sendMessage(var3);
        } else {
//          a.this.d.sendEmptyMessage(204);


          Message var3 = a.this.d.obtainMessage();
          var3.what = 200;
          var3.obj = var1;
          a.this.d.sendMessage(var3);


        }
      }

    }
  };

  public a(Context var1, Handler var2) {
    this.d = var2;
    this.f = var1;
  }

  public boolean a() {
    return this.e != null;
  }

  public void b() {
    if (this.e == null) {
      this.e = Camera.open();

      try {
        this.e.setOneShotPreviewCallback(this.i);
      } catch (Exception var2) {
      }
    }

  }

  public void a(SurfaceHolder var1) {
    if (this.e != null) {
      try {
        this.e.setPreviewDisplay(var1);
      } catch (IOException var3) {
        var3.printStackTrace();
      }
    }

  }

  public void c() {
    if (this.e != null) {
      try {
        this.e.startPreview();
      } catch (Exception var2) {
      }
    }

  }

  public void d() {
    if (this.e != null) {
      this.e.stopPreview();
      this.e.release();
      this.e = null;
    }

  }

  public void a(String var1) {
    if (this.e != null) {
      Parameters var2 = this.e.getParameters();
      var2.setFlashMode(var1);

      try {
        this.e.setParameters(var2);
      } catch (Exception var4) {
      }
    }

  }

  public Camera e() {
    return this.e;
  }
@Override
  public void onAutoFocus(boolean var1, Camera var2) {
    Log.d("cc_camera", "--onAutoFocus------>>>" + var1);
  }

  public void f() {
    if (this.e != null) {
      try {
        this.g = false;
        this.e.setOneShotPreviewCallback(this.i);
      } catch (Exception var2) {
        var2.printStackTrace();
        this.d.sendEmptyMessage(206);
      }
    }

  }

  public void g() {
    try {
      this.e.autoFocus(this.h);
    } catch (Exception var2) {
    }

  }

  public Rect a(Rect var1) {
    WindowManager var2 = (WindowManager)this.f.getSystemService("window");
    Display var3 = var2.getDefaultDisplay();
    float var4 = (float)var3.getWidth();
    float var5 = (float)var3.getHeight();
    int var6 = this.e.getParameters().getPreviewSize().width;
    int var7 = this.e.getParameters().getPreviewSize().height;
    float var8 = (float)var6 / var4;
    float var9 = (float)var7 / var5;
    Rect var10 = new Rect(var1);
    var10.left = (int)((float)var1.left * var8);
    var10.right = (int)((float)var1.right * var8);
    var10.top = (int)((float)var1.top * var9);
    var10.bottom = (int)((float)var1.bottom * var9);
    return var10;
  }

  public int h() {
    return this.e != null ? this.e.getParameters().getPreviewSize().width : 0;
  }

  public int i() {
    return this.e != null ? this.e.getParameters().getPreviewSize().height : 0;
  }

  public boolean j() {
    if (this.e == null) {
      this.e = Camera.open();
    }

    Parameters var1 = this.e.getParameters();
    if (var1.getSupportedFlashModes() == null) {
      return false;
    } else if (var1.getFlashMode().equalsIgnoreCase("torch")) {
      return false;
    } else {
      var1.setFlashMode("torch");

      try {
        this.e.setParameters(var1);
      } catch (Exception var3) {
      }

      return this.e.getParameters().getFlashMode().equals("torch");
    }
  }

  public boolean k() {
    if (this.e == null) {
      this.e = Camera.open();
    }

    if (this.e.getParameters().getFlashMode().equalsIgnoreCase("off")) {
      return false;
    } else {
      Parameters var1 = this.e.getParameters();
      var1.setFlashMode("off");

      try {
        this.e.setParameters(var1);
      } catch (Exception var3) {
      }

      return this.e.getParameters().getFlashMode().equalsIgnoreCase("off");
    }
  }
}
