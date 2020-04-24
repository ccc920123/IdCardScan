package com.cdjysd.idcardlib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.cdjysd.idcardlib.R;

/**
 * 描述：扫描预览界面
 * 编写人：陈渝金-pc:chenyujin
 * 时间： 2020/4/15
 * 修改人：
 * 修改时间：
 */


public class ViewfinderView extends View {
    private int f;

    private int g;

    private Paint h;

    private Context i;

    private int j;

    private int k;

    private float l;

    private float m;

    private float n;

    private float o;

    private int p = 0;

    private float q = 0.0F;

    private float r = 0.0F;

    private float s = 0.0F;

    private int t = 12;

    private int u = 60;

    private int v;

    private int w;

    boolean a = false;

    boolean b = false;

    boolean c = false;

    boolean d = false;

    boolean e = false;

    public ViewfinderView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        this.i = paramContext;
    }

    public ViewfinderView(Context paramContext) {
        super(paramContext);
        this.i = paramContext;
    }

    public ViewfinderView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        this.i = paramContext;
    }

    public void a(int paramInt1, int paramInt2, Handler paramHandler) {
        double d2;
        double d1;
        this.v = paramInt1;
        this.w = paramInt2;
        WindowManager windowManager = (WindowManager)this.i.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        this.f = display.getWidth();
        this.g = display.getHeight();
        Log.d("tag", "-1-------->>" + this.f);
        this.s = this.i.getResources().getDimension(R.dimen.lib_48_dp);
        this.q = (float)((this.f - paramInt1) / 2.0D);
        this.r = (float)((this.g - paramInt2) / 2.0D);
        this.j = this.f / 2;
        this.k = this.g / 2;
        float f1 = this.g - this.s * 2.0F;
        float f2 = f1 * 1.58F;
        float f3 = 10.0F;
        Log.d("ocr", f2 + "<<--Height 1---->>" + f1);
        while (f2 > paramInt1) {
            f3--;
            f2 *= f3 / 10.0F;
            f1 *= f3 / 10.0F;
        }
        Log.d("ocr", f2 + "<<--k---Height2---->>" + f1);
        this.l = (float)(this.j - f2 / 2.0D);
        this.m = (float)(this.j + f2 / 2.0D);
        this.n = (float)(this.k - f1 / 2.0D);
        this.o = (float)(this.k + f1 / 2.0D);
        int i1 = display.getWidth();
        int i2 = display.getHeight();
        int i3 = this.v;
        int i4 = this.w;
        double d3 = 0.0D;
        double d4 = 0.0D;
        double d5 = 0.0D;
        if (i3 * i2 < i1 * i4) {
            d2 = i2;
            d1 = i3 / i4 * d2;
        } else {
            d1 = i1;
            d2 = d1 * i4 / i3;
        }
        if (d1 / d2 >= 1.0D) {
            d4 = d2;
            d3 = 4.0D * d4 / 3.0D;
        } else {
            d3 = d1;
            d4 = 3.0D * d3 / 4.0D;
        }
        d5 = d3 / 480.0D * 420.0D;
        this.h = new Paint();
        this.t = (int)d5 / 28;
        this.t = 4;
        this.h.setStrokeWidth(this.t);
        this.u = (int)d5 / 6;
    }

    public Rect getFinder() { return new Rect((int)(this.l - this.q), (int)(this.n - this.r), (int)(this.m + this.q), (int)(this.o + this.r)); }

    public void setLineRect(int paramInt) {
        this.p = paramInt;
        invalidate();
    }

    public void draw(Canvas paramCanvas) {
        super.draw(paramCanvas);
        if (this.h == null)
            return;
        this.h.setColor(-16711936);
        paramCanvas.drawLine(this.l - (this.t / 2), this.n, this.l + this.u, this.n, this.h);
        paramCanvas.drawLine(this.l, this.n - (this.t / 2), this.l, this.n + this.u, this.h);
        paramCanvas.drawLine(this.m, this.n - (this.t / 2), this.m, this.n + this.u, this.h);
        paramCanvas.drawLine(this.m + (this.t / 2), this.n, this.m - this.u, this.n, this.h);
        paramCanvas.drawLine(this.l, this.o + (this.t / 2), this.l, this.o - this.u, this.h);
        paramCanvas.drawLine(this.l - (this.t / 2), this.o, this.l + this.u, this.o, this.h);
        paramCanvas.drawLine(this.m + (this.t / 2), this.o, this.m - this.u, this.o, this.h);
        paramCanvas.drawLine(this.m, this.o + (this.t / 2), this.m, this.o - this.u, this.h);
        switch (this.p) {
            case 1:
                paramCanvas.drawLine(this.l, this.n, this.l, this.o, this.h);
                break;
            case 2:
                paramCanvas.drawLine(this.m, this.n, this.m, this.o, this.h);
                break;
            case 3:
                paramCanvas.drawLine(this.l, this.n, this.l, this.o, this.h);
                paramCanvas.drawLine(this.m, this.n, this.m, this.o, this.h);
                break;
            case 4:
                paramCanvas.drawLine(this.l, this.n, this.m, this.n, this.h);
                break;
            case 5:
                paramCanvas.drawLine(this.l, this.n, this.l, this.o, this.h);
                paramCanvas.drawLine(this.l, this.n, this.m, this.n, this.h);
                break;
            case 6:
                paramCanvas.drawLine(this.m, this.n, this.m, this.o, this.h);
                paramCanvas.drawLine(this.l, this.n, this.m, this.n, this.h);
                break;
            case 7:
                paramCanvas.drawLine(this.l, this.n, this.l, this.o, this.h);
                paramCanvas.drawLine(this.m, this.n, this.m, this.o, this.h);
                paramCanvas.drawLine(this.l, this.n, this.m, this.n, this.h);
                break;
            case 8:
                paramCanvas.drawLine(this.l, this.o, this.m, this.o, this.h);
                break;
            case 9:
                paramCanvas.drawLine(this.l, this.n, this.l, this.o, this.h);
                paramCanvas.drawLine(this.l, this.o, this.m, this.o, this.h);
                break;
            case 10:
                paramCanvas.drawLine(this.m, this.n, this.m, this.o, this.h);
                paramCanvas.drawLine(this.l, this.o, this.m, this.o, this.h);
                break;
            case 11:
                paramCanvas.drawLine(this.l, this.n, this.l, this.o, this.h);
                paramCanvas.drawLine(this.m, this.n, this.m, this.o, this.h);
                paramCanvas.drawLine(this.l, this.o, this.m, this.o, this.h);
                break;
            case 12:
                paramCanvas.drawLine(this.l, this.n, this.m, this.n, this.h);
                paramCanvas.drawLine(this.l, this.o, this.m, this.o, this.h);
                break;
            case 13:
                paramCanvas.drawLine(this.l, this.n, this.m, this.n, this.h);
                paramCanvas.drawLine(this.l, this.o, this.m, this.o, this.h);
                paramCanvas.drawLine(this.l, this.n, this.l, this.o, this.h);
                break;
            case 14:
                paramCanvas.drawLine(this.l, this.n, this.m, this.n, this.h);
                paramCanvas.drawLine(this.l, this.o, this.m, this.o, this.h);
                paramCanvas.drawLine(this.m, this.n, this.m, this.o, this.h);
                break;
            case 15:
                paramCanvas.drawLine(this.l, this.n, this.l, this.o, this.h);
                paramCanvas.drawLine(this.m, this.n, this.m, this.o, this.h);
                paramCanvas.drawLine(this.l, this.n, this.m, this.n, this.h);
                paramCanvas.drawLine(this.l, this.o, this.m, this.o, this.h);
                break;
        }
        this.h.setColor(-16777216);
        this.h.setAlpha(100);
        paramCanvas.drawRect(0.0F, 0.0F, this.f, this.n - (this.t / 2), this.h);
        paramCanvas.drawRect(0.0F, this.n - (this.t / 2), this.l - (this.t / 2), this.o + (this.t / 2), this.h);
        paramCanvas.drawRect(0.0F, this.o + (this.t / 2), this.f, this.g, this.h);
        paramCanvas.drawRect(this.m + (this.t / 2), this.n - (this.t / 2), this.f, this.o + (this.t / 2), this.h);
    }
}
