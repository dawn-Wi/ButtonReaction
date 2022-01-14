package com.example.buttonreaction;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    int x, y, r;
    String str;
    private Path mPath;
//    Path mPath = new Path();

    public MyView(Context context) {
        super(context);
        mPath = new Path();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCircleX(int x) {
        this.x = x;
    }

    public void setCircleY(int y) {
        this.y = y;
    }

    public void setCircleR(int r) {
        this.r = r;
    }

    public int getCircleX() {
        return x;
    }

    public int getCircleY() {
        return y;
    }

    public int getCircleR() {
        return r;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawCircle(getCircleX(), getCircleY(), getCircleR(), paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
//        Log.d("좌표값",(int)x+","+(int)y);
//        Log.d("랜덤값","x좌표값:"+getCircleX()+" y좌표값"+getCircleY());
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (((getCircleX() - 40 <= (int) x) && ((int) x <= getCircleX() + 40))
                    || ((getCircleY() - 40 <= (int) y) && ((int) y <= getCircleY() + 40))) {
                Log.d("좌표값", (int) x + "," + (int) y);
                Log.d("랜덤값", "x좌표값:" + getCircleX() + " y좌표값" + getCircleY());
                mPath.reset();
                mPath.moveTo(0, 0);
                invalidate();
            } else {
                Log.d("touch", "뭔가 잘못됨");
            }
        }
//        if(((getCircleX()-40<=(int)x)&&((int)x<=getCircleX()+40))||((getCircleY()-40<=(int)y)&&(int)y<=getCircleY()+40)){
//            if (event.getAction()==MotionEvent.ACTION_DOWN){
//                mPath.moveTo(0,0);
//                invalidate();
//            }
//            else{
//                Log.d("touch", "뭔가 잘못됨");
//            }
//
//        }
        return true;
    }
}
