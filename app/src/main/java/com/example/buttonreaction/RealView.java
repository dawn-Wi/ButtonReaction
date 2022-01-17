package com.example.buttonreaction;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import java.util.Random;

public class RealView extends View {
    Random random= new Random();

    public RealView(Context context) {
        super(context);
    }

    public RealView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RealView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
//        Bitmap bluedot = BitmapFactory.decodeResource(getResources(),R.drawable.bluedot);
//        canvas.drawBitmap(bluedot,random.nextInt(),random.nextInt(),null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
//        Log.d("좌표값",(int)x+","+(int)y);
//        Log.d("랜덤값","x좌표값:"+getCircleX()+" y좌표값"+getCircleY());
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d("좌표값", (int) x + "," + (int)y );
                invalidate();
        }
        return true;
    }
}
