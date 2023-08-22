package com.htnguyen.gplxapp.view.base.textview.fall;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.htnguyen.gplxapp.view.base.textview.htext.AnimationListener;
import com.htnguyen.gplxapp.view.base.textview.htext.HTextView;

public class FallTextView extends HTextView {

    private FallText fallText;

    public FallTextView(Context context) {
        this(context,null);
    }

    public FallTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FallTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    @Override
    public void setAnimationListener(AnimationListener listener) {
        fallText.setAnimationListener(listener);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        fallText = new FallText();
        fallText.init(this, attrs, defStyleAttr);
        setMaxLines(1);
        setEllipsize(TextUtils.TruncateAt.END);
    }

    @Override
    public void setProgress(float progress) {
        fallText.setProgress(progress);
    }

    @Override
    public void animateText(CharSequence text) {
        fallText.animateText(text);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        fallText.onDraw(canvas);
    }
}
