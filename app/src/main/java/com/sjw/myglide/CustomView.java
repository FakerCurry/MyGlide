package com.sjw.myglide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by pc on 2018/7/24.
 */
public class CustomView extends FrameLayout {
    private ImageView mImageView;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mImageView = new ImageView(getContext());
        addView(mImageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    public void setImage(Drawable drawable) {
        mImageView.setImageDrawable(drawable);
    }
}
