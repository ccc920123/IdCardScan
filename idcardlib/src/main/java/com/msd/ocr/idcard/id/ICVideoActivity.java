package com.msd.ocr.idcard.id;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Bitmap.CompressFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.CardApplication;
import com.c.a.a.a.b;
import com.cdjysd.idcardlib.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import a.a.a.LiceseKeyCheck;
import androidx.annotation.NonNull;

public class ICVideoActivity extends Activity implements Callback, com.msd.ocr.idcard.a.a.PermissionsResultCallback {
    private final String b = "ocr";
    private Context c;
    private SurfaceView d;
    private SurfaceHolder e;
    private com.c.a.a.a.a f;
    private boolean g = true;
    private ViewfinderView h;
    private b i;
    private Rect j;
    private boolean k;
    private boolean l = false;
    private ImageButton m;
    private ImageButton n;
    private ImageButton o;
    private boolean p = false;
    private boolean q = true;
    private boolean r = false;
    private String s;
    private com.msd.ocr.idcard.b.a t;
    private boolean isAuteKey = false;
    private boolean v = false;
    private OnClickListener w = new OnClickListener() {
        @Override
        public void onClick(View var1) {
            int var2 = var1.getId();
            if (var2 == R.id.bt_cancel) {
                ICVideoActivity.this.finish();
            } else if (var2 == R.id.bt_flash) {
                if (ICVideoActivity.this.v) {
                    if (ICVideoActivity.this.f.k()) {
                        ICVideoActivity.this.v = false;
                    }
                } else if (ICVideoActivity.this.f.j()) {
                    ICVideoActivity.this.v = true;
                }
            } else if (var2 == R.id.selectImage && ICVideoActivity.this.isAuteKey) {
                ICVideoActivity.this.f();
            }

        }
    };
    private Handler xHandler = new Handler() {
        @Override
        public void handleMessage(Message var1) {
            super.handleMessage(var1);
            Intent var2 = new Intent();
            int var11;
            switch (var1.what) {
                case 200:
                    if (ICVideoActivity.this.i == null) {
                        ICVideoActivity.this.i = new b(ICVideoActivity.this.xHandler, ICVideoActivity.this);
                        ICVideoActivity.this.j = ICVideoActivity.this.f.a(ICVideoActivity.this.h.getFinder());
                    }

                    byte[] var3 = (byte[]) ((byte[]) var1.obj);
                    ICVideoActivity.this.i.a(var3, ICVideoActivity.this.f.h(), ICVideoActivity.this.f.i(), ICVideoActivity.this.j);
                    ICVideoActivity.this.xHandler.sendEmptyMessageDelayed(206, 100L);
                    break;
                case 201:
                    //得到识别的身份证数据
                    ICVideoActivity.this.xHandler.removeMessages(200);
                    ICVideoActivity.this.xHandler.removeMessages(206);
                    String var4 = ICVideoActivity.this.getIntent().getStringExtra("needimg");
                    String var5 = "";
                    String var6 = "";
                    long var7 = System.currentTimeMillis();
                    if (var4 == null || var4.equals("true")) {
                        var5 = CardApplication.path + "/" + var7 + "-full.jpg";
                        var6 = CardApplication.path + "/" + var7 + "-head.jpg";
                    }

                    com.c.a.a.c.a var9 = ICVideoActivity.this.i.a(var5, var6);
                    String var10 = null;

                    try {
                        var10 = new String(var9.a(), "gbk");
                        var10 = var10.replaceAll("\r\n", "");
                        var10 = var10.replaceAll("\"value\"", "");
                        var10 = var10.replaceAll("[{]", "");
                        var10 = var10.replaceAll("[}]", "");
                        var10 = var10.replaceAll("::", ":");
                        var10 = var10.replace("Name", "name");
                        var10 = var10.replace("Sex", "sex");
                        var10 = var10.replace("Folk", "folk");
                        var10 = var10.replace("Birt", "birt");
                        var10 = var10.replace("Addr", "addr");
                        var10 = var10.replace("Num", "num");
                        var10 = var10.replace("Issue", "issue");
                        var10 = var10.replace("Valid", "valid");
                        var10 = var10.trim();
                        var11 = var10.indexOf("issue");
                        String var12 = var10.substring(var11 + 8, var10.indexOf(",", var11));

                        try {
                            if (!ICVideoActivity.this.p) {
                                File var13 = new File(var6);
                                var13.delete();
                                var13 = new File(var5);
                                var13.delete();
                                var6 = "";
                                var5 = "";
                            }
                        } catch (Exception var14) {
                            var14.printStackTrace();
                        }

                        if (var12.equals("\"")) {
                            var10 = "{\"type\":\"1\",\"imgPath\":\"" + var5 + "\",\"headPath\":\"" + var6 + "\"," + var10 + "}";
                        } else {
                            var10 = "{\"type\":\"0\",\"imgPath\":\"" + var5 + "\"," + var10 + "}";
                        }

                        var2.putExtra("OCRResult", var10);
                        var2.putExtra("headImg", var6);
                        var2.putExtra("fullImg", var5);
                        ICVideoActivity.this.setResult(-1, var2);
                        ICVideoActivity.this.a(var10, var6, var5);
                        ICVideoActivity.this.finish();
                    } catch (Exception var15) {
                        var15.printStackTrace();
                    }
                    break;
                case 202:
                    ICVideoActivity.this.f.g();
                    ICVideoActivity.this.xHandler.sendEmptyMessageDelayed(202, 2000L);
                case 203:
                case 205:
                    break;
                case 204:
                    Toast.makeText(ICVideoActivity.this.getBaseContext(), R.string.ocr_unauthorized, Toast.LENGTH_LONG).show();
                    break;
                case 206:
                    if (ICVideoActivity.this.g) {
                        ICVideoActivity.this.f.g();
                        ICVideoActivity.this.g = false;
                        ICVideoActivity.this.xHandler.sendEmptyMessageDelayed(206, 500L);
                        ICVideoActivity.this.xHandler.sendEmptyMessageDelayed(202, 1500L);
                    } else {
                        ICVideoActivity.this.f.f();
                    }
                    break;
                case 207:
                    var11 = (Integer) var1.obj;
                    ICVideoActivity.this.h.setLineRect(var11);
                    break;
                default:
                    ICVideoActivity.this.f.c();
                    ICVideoActivity.this.xHandler.sendEmptyMessageDelayed(206, 500L);
                    Toast.makeText(ICVideoActivity.this.getBaseContext(), "<>" + var1.what, Toast.LENGTH_SHORT).show();
            }

        }
    };
    String[] permissionL = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    private ProgressDialog y;
    private String z;
    private String A;
    private Handler B = new Handler() {
        public void handleMessage(Message var1) {
            if (ICVideoActivity.this.y != null && ICVideoActivity.this.y.isShowing()) {
                ICVideoActivity.this.y.dismiss();
            }

            switch (var1.what) {
                case 2:
                    com.a.a.d.a var2 = (com.a.a.d.a) var1.obj;

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
                        var3.put("imgPath", ICVideoActivity.this.A);
                        String var4 = var3.toString();
                        Intent var5 = new Intent();
                        var5.putExtra("OCRResult", var4);
                        var5.putExtra("headImg", ICVideoActivity.this.z);
                        var5.putExtra("fullImg", ICVideoActivity.this.A);
                        ICVideoActivity.this.setResult(-1, var5);
                        ICVideoActivity.this.a(var4, ICVideoActivity.this.z, ICVideoActivity.this.A);
                        ICVideoActivity.this.finish();
                    } catch (JSONException var6) {
                        var6.printStackTrace();
                    }
                    break;
                case 3:
                case 9:
                default:
                    Toast.makeText(ICVideoActivity.this.c, R.string.parse_error, Toast.LENGTH_SHORT).show();
                    break;
                case 204:
                    ICVideoActivity.this.xHandler.sendEmptyMessage(var1.what);
            }

        }
    };

    public ICVideoActivity() {
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
        this.t = new com.msd.ocr.idcard.b.a(this);
        this.setContentView(R.layout.activity_card);
        this.b();
        this.p = this.getIntent().getBooleanExtra("saveImage", false);
        Bundle var2 = this.getIntent().getBundleExtra("bundle");
        if (var2 != null) {
            this.q = var2.getBoolean("showSelect", true);
            this.r = var2.getBoolean("showCamera", false);
            this.s = var2.getString("broadcastAction");
        } else {
            this.q = this.getIntent().getBooleanExtra("showSelect", true);
            this.r = this.getIntent().getBooleanExtra("showCamera", true);
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
            this.isAuteKey = true;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.xHandler.removeMessages(200);
        this.xHandler.removeMessages(206);
        this.c();
    }

    public static void a(Object var0, Bundle var1) {
        boolean var2 = var1.getBoolean("saveImage");
        int var3 = var1.getInt("requestCode", 10048);
        a(var0, var2, var3, var1);
    }

    public static void a(Object var0, boolean var1, int var2) {
        a(var0, var1, var2, (Bundle) null);
    }

    public static void a(Object var0, boolean var1, int var2, Bundle var3) {
        Class var4 = ICVideoActivity.class;
        if (var3 != null) {
            int var5 = var3.getInt("type", 0);
            switch (var5) {
                case 0:
                    var4 = ICVideoActivity.class;
                    break;
                case 1:
                    var4 = DIVideoActivity.class;
            }
        }

        Intent var6;
        if (var0 instanceof Activity) {
            Activity var7 = (Activity) var0;
            var6 = new Intent(var7, var4);
            var6.putExtra("saveImage", var1);
            var6.putExtra("bundle", var3);
            var7.startActivityForResult(var6, var2);
        } else if (var0 instanceof Fragment) {
            Fragment var8 = (Fragment) var0;
            var6 = new Intent(var8.getActivity(), var4);
            var6.putExtra("saveImage", var1);
            var6.putExtra("bundle", var3);
            var8.startActivityForResult(var6, var2);
        } else if (var0 instanceof Fragment) {
            Fragment var9 = (Fragment) var0;
            var6 = new Intent(var9.getActivity(), var4);
            var6.putExtra("saveImage", var1);
            var6.putExtra("bundle", var3);
            var9.startActivityForResult(var6, var2);
        }

    }

    private void a() {
        this.f.a("off");
        WindowManager var1 = (WindowManager) this.c.getSystemService(Context.WINDOW_SERVICE);
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
        this.h.a(var3.x, var3.y, this.xHandler);
    }

    private void b() {
        this.d = (SurfaceView) this.findViewById(R.id.camera_sv);
        this.h = (ViewfinderView) this.findViewById(R.id.camera_finderView);
        this.n = (ImageButton) this.findViewById(R.id.bt_cancel);
        this.m = (ImageButton) this.findViewById(R.id.bt_flash);
        this.o = (ImageButton) this.findViewById(R.id.selectImage);
        this.m.setOnClickListener(this.w);
        this.n.setOnClickListener(this.w);
        this.o.setOnClickListener(this.w);
    }

    private void a(String var1, String var2, String var3) {
        if (this.s != null) {
            Intent var4 = new Intent();
            var4.setAction(this.s);
            var4.putExtra("OCRResult", var1);
            var4.putExtra("headImg", var2);
            var4.putExtra("fullImg", var3);
            var4.addCategory(this.getPackageName());
            this.sendBroadcast(var4);
        }

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
            this.xHandler.sendEmptyMessageDelayed(206, 500L);
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
        this.t.c();
    }

    private void d() {
        //判断是否有权限
        if (com.msd.ocr.idcard.a.a.a(this.c, this.permissionL)) {
            this.k = true;
        } else {
            //申请权限
            com.msd.ocr.idcard.a.a.a(this).a(this.getString(R.string.rationale_camera)).a(10049).a(this.permissionL).a();
        }

    }

    private void e() {
        if (this.f != null) {
            this.f.d();
        }

        this.f = new com.c.a.a.a.a(this.getBaseContext(), this.xHandler);

        try {
            ICVideoActivity.cameraFocus var1 = new ICVideoActivity.cameraFocus();
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
        this.xHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ICVideoActivity.this.e();
            }
        }, 200L);
    }

    /**
     * 提示授权
     *
     * @param var1
     * @param var2
     */
    @Override
    public void requestFailed(int var1, List<String> var2) {
        boolean var3 = com.msd.ocr.idcard.a.a.a(this, this.getString(R.string.rationale_ask_again), R.string.setting, R.string.cancel, (android.content.DialogInterface.OnClickListener) null, var2);
        if (!var3) {
        }

    }


    @Override
    protected void onActivityResult(int var1, int var2, Intent var3) {
        super.onActivityResult(var1, var2, var3);
        //选择图片识别
        if (var1 == 10050 && var2 == -1) {
            ArrayList var4 = var3.getStringArrayListExtra("select_result");
            if (var4 != null) {
                String var5 = (String) var4.get(0);
                this.a(var5);
            }
        }

    }

    private void a(final String var1) {
        try {
            this.y = new ProgressDialog(this.c);
            this.y.setMessage(this.getString(R.string.parsing));
            this.y.show();
            this.t.b();
            (new Thread() {
                @Override
                public void run() {
                    ICVideoActivity.this.b(var1);
                }
            }).start();
        } catch (Exception var3) {
            var3.printStackTrace();
            this.B.sendEmptyMessage(3);
        }

    }

    private void b(String var1) {
        try {
            Bitmap var2 = com.msd.ocr.idcard.b.b.a(this.c, var1);
            ByteArrayOutputStream var3 = new ByteArrayOutputStream();
            var2.compress(CompressFormat.JPEG, 100, var3);
            byte[] var4 = var3.toByteArray();
            Rect var5 = new Rect(0, 0, var2.getWidth(), var2.getHeight());
            com.a.a.a.a var6 = new com.a.a.a.a();
            var6.a(true);
            var6.a(var2.getWidth(), var2.getHeight());
            this.A = var1;
            this.z = CardApplication.path + "/" + System.currentTimeMillis() + "-head.jpg";
            com.a.a.a.b var7 = new com.a.a.a.b(this.B, var4, var6, var5, true, true, "", this.z);
            var7.run();
        } catch (Exception var8) {
            var8.printStackTrace();
            this.B.sendEmptyMessage(3);
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


    //相机对焦
    private class cameraFocus extends Thread {

        @Override
        public void run() {
            try {
                ICVideoActivity.this.f.b();
            } catch (Exception var2) {
                ICVideoActivity.this.l = true;
            }

        }
    }
}
