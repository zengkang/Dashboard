package com.test.zengkang.dashboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zengkang on 16/4/21.
 */
public class DashboardView extends View {

    private int mDegree;
    private int curDegree;

    private int mNum;
    private int curNum;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            curDegree += 2;
            curNum += mNum/mDegree*2;
            if(curDegree < mDegree){
                handler.sendEmptyMessageDelayed(0,2);
            }else{
                curDegree = mDegree;
                curNum = mNum;
            }
            invalidate();
        }
    };


    public void setDegree(int num){
        mNum = num;
        mDegree = (int) (num/1000f*259);
        handler.sendEmptyMessageDelayed(0,400);
    }

    public DashboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mNum = 0;
        curNum = 0;
        mDegree = 0;
        curDegree = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //配置画笔的颜色、粗细以及风格
        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setStrokeCap(Paint.Cap.ROUND);
        paintCircle.setColor(0xfff6efef);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(50);

        //定义仪表盘圆弧所在的区域，并绘制圆弧
        RectF oval = new RectF();
        oval.left = 150;
        oval.top = 150;
        oval.right = getWidth()-150;
        oval.bottom = getWidth()-150;
        canvas.drawArc(oval, 140, 260, false, paintCircle);

        //绘制仪表盘中心的数字显示区域背景
        paintCircle.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getWidth()/2, getWidth()/2, (getWidth() - 300) / 7, paintCircle);

        //绘制刻度
        paintCircle.setColor(0xffebebeb);
        paintCircle.setStrokeWidth(3);
        for (int i = 0;i < 36;i++){
            if(i > 13 && i < 23){
                canvas.rotate(10,getWidth()/2,getWidth()/2);
                continue;
            }else{
                canvas.drawLine(getWidth()/2,200,getWidth()/2,225,paintCircle);
                canvas.rotate(10,getWidth()/2,getWidth()/2);
            }

        }

        //设置进度条的颜色和渐变效果，并绘制此时需要显示的进度条长度
        Shader mShader = new RadialGradient(0,0,(getWidth()-300)/2, 0xffde5669,0xffe79950,Shader.TileMode.MIRROR);
        paintCircle.setShader(mShader);
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setStrokeWidth(50);
        canvas.drawArc(oval, 140, curDegree, false, paintCircle);

        //绘制此时需要显示的数字
        paintCircle.setColor(0xffde5669);
        paintCircle.setStrokeWidth(2.5f);
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setTextSize(70);
        canvas.drawText(String.format("%03d", curNum),getWidth()/2-55,getWidth()/2+25,paintCircle);

    }


}
