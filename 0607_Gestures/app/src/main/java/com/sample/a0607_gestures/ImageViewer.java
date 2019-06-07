package com.sample.a0607_gestures;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class ImageViewer extends View {
    private Drawable image;
    private ScaleGestureDetector scaleGestureDetector;
    private float scale;

    public ImageViewer(Context context) {
        super(context);
        image= context.getDrawable(R.drawable.image);
        setFocusable(true);
        image.setBounds(100,100,600,400);
        image.setVisible(true,true);
        invalidate();
        scaleGestureDetector = new ScaleGestureDetector(context,new ScaleListener());
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            //return super.onScale(detector);
            scale*=detector.getScaleFactor();
            if(scale<0.1f)
                scale=0.1f;
            if(scale>10.0f)
                scale=10.0f;

            invalidate();
            return true;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scale,scale);
        image.draw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        scaleGestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }
}
