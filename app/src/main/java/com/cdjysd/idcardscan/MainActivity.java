package com.cdjysd.idcardscan;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cdjysd.idcardlib.utils.ScanCard;
import com.msd.ocr.idcard.LibraryInitOCR;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private final int REQUEST_CODE = 1;
    private final int REQUEST_PERMISS = 2;

    private Button idCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        LibraryInitOCR.initOCR(this);
        idCardButton = findViewById(R.id.idcard);


        if (Build.VERSION.SDK_INT >= 23) {
            checkAndRequestPermission();
        } else {
            init();
        }





    }

    private void init() {


        idCardButton.setOnClickListener(click);

    }




    private View.OnClickListener click=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("saveImage", false); // 是否保存识别图片
                bundle.putBoolean("showSelect", true);                          // 是否显示选择图片
                bundle.putBoolean("showCamera", true);                          // 显示图片界面是否显示拍照(驾照选择图片识别率比扫描高)
                bundle.putInt("requestCode", REQUEST_CODE);                     // requestCode
                bundle.putInt("type", 0);  // 0身份证, 1驾驶证

                //broadcastAction 将扫描结果广播出去, 注意增加 intent.addCategory(context.getPackageName());
                //如果不需要广播,就不会传这个参数

                LibraryInitOCR.startScan(MainActivity.this, bundle);


//            ScanCard.openScanIdCard(MainActivity.this,666);


        }
    };

    @TargetApi(Build.VERSION_CODES.M)
    private void checkAndRequestPermission() {
        List<String> lackedPermission = new ArrayList<String>();


        if (!(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.CAMERA);
        }

//        if (!(checkSelfPermission(Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED)) {
//            lackedPermission.add(Manifest.permission.VIBRATE);
//        }
        if (!(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.READ_PHONE_STATE);
        }
        // 权限都已经有了，那么直接
        if (lackedPermission.size() == 0) {

            init();

        } else {
            // 请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限，如果获得权限就可以调用SDK，否则不要调用SDK。
            String[] requestPermissions = new String[lackedPermission.size()];
            lackedPermission.toArray(requestPermissions);
            requestPermissions(requestPermissions, 1024);
        }
    }

    private boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1024 && hasAllPermissionsGranted(grantResults)) {
            init();
        } else {
            Toast.makeText(this, "没有权限请开启", Toast.LENGTH_SHORT).show();
        }
    }

}
