package com.cdjysd.idcardlib.utils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.cdjysd.idcardlib.ui.IDCActivity;
import com.msd.ocr.idcard.id.ICVideoActivity;

import androidx.fragment.app.FragmentActivity;


/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:chenyujin
 * 时间： 2019/12/13
 * 修改人：
 * 修改时间：
 */


public class ScanCard {



    /**
     * 打开自带扫描框界面识别身份证
     * @param activity   Activity
     * @param requestCode
     */
    public static void openScanIdCard(Activity activity, int requestCode) {

        try {
            Intent itt = new Intent();
            itt.setClass(activity, IDCActivity.class);
            activity.startActivityForResult(itt, requestCode);


        } catch (Exception e) {
            Log.e("ScanCard", e.getMessage());
        }

    }

    /**
     * 打开自带扫描框界面识别身份证
     * @param fragmentActivity   FragmentActivity
     * @param requestCode
     */
    public static void openScanIdCard(FragmentActivity fragmentActivity, int requestCode) {
        {
            try {
                Intent itt = new Intent();
                itt.setClass(fragmentActivity, IDCActivity.class);
                fragmentActivity.startActivityForResult(itt, requestCode);


            } catch (Exception e) {
                Log.e("ScanCard", e.getMessage());
            }

        }

    }



}
