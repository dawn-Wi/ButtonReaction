package com.example.buttonreaction;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    int x,y,r;

    public MyView(Context context) {
        super(context);
    }
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCircleX(int x) { this.x = x; }

    public void setCircleY(int y) { this.y = y; }

    public int getCircleX() { return x; }

    public int getCircleY() { return y; }

    public void setCircleR(int r) { this.r = r; }

    public int getCircleR() { return r; }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);

        Paint pnt = new Paint();
        pnt.setColor(Color.CYAN);

        canvas.drawCircle(getCircleX(), getCircleY(), getCircleR(), pnt);
    }
}
