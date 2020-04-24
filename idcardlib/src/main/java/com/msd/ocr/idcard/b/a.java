package com.msd.ocr.idcard.b;

import android.app.Activity;
import android.content.Context;
import android.media.SoundPool;
import android.os.Vibrator;

import com.cdjysd.idcardlib.R;
import java.util.HashMap;

public class a {
  private static final String e = a.class.getSimpleName();
  private Context f;
  public boolean a = true;
  public boolean b = true;
  public SoundPool c;
  public HashMap<Integer, Integer> d;

  public a(Activity var1) {
    this.f = var1;
    this.d();
  }

  private void d() {
    this.d = new HashMap();
    this.c = new SoundPool(10, 1, 5);
    this.d.put(1, this.c.load(this.f,     R.raw.ocr_beep, 1));
  }

  public void a() {
    this.c.play((Integer)this.d.get(1), 1.0F, 1.0F, 0, 0, 1.0F);
  }

  public synchronized void b() {
    if (this.a) {
      this.a();
    }

    if (this.b) {
      Vibrator var1 = (Vibrator)this.f.getSystemService("vibrator");
      var1.vibrate(200L);
    }

  }

  public void c() {
    try {
      if (this.c != null) {
        this.c.release();
      }
    } catch (Exception var2) {
    }

  }
}
