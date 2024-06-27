package com.example.tasks;


import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


public class PercentageCircleView extends View {
    private Paint baseCirclePaint;
    private Paint filledCirclePaint;
    private Paint glowPaint;
    private Paint textPaint;
    private int percentage;

    public PercentageCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        baseCirclePaint = new Paint();
        baseCirclePaint.setColor(Constants.PURPLE_OPACITY10);
        baseCirclePaint.setStyle(Paint.Style.STROKE);
        baseCirclePaint.setStrokeWidth(30);

        filledCirclePaint = new Paint();
        filledCirclePaint.setColor(Constants.PURPLE);
        filledCirclePaint.setStyle(Paint.Style.STROKE);
        filledCirclePaint.setStrokeWidth(30);

        glowPaint = new Paint();
        glowPaint.setColor(Constants.PURPLE);
        glowPaint.setStyle(Paint.Style.STROKE);
        glowPaint.setStrokeWidth(30);
        glowPaint.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.NORMAL));

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(110);
        textPaint.setTextAlign(Paint.Align.CENTER);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2 - 60;

        canvas.drawCircle(width / 2, height / 2, radius, baseCirclePaint);

        float angle = 360 * percentage / 100;

        RectF oval = new RectF(width / 2 - radius, height / 2 - radius, width / 2 + radius, height / 2 + radius);
        canvas.drawArc(oval, -90, angle, false, glowPaint);
        canvas.drawArc(oval, -90, angle, false, filledCirclePaint);

        // Rysowanie tekstu
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float textHeight = fontMetrics.descent - fontMetrics.ascent;
        float textOffset = (textHeight / 2) - fontMetrics.descent;
        canvas.drawText(percentage + "%", width / 2, height / 2 + textOffset, textPaint);
    }
}