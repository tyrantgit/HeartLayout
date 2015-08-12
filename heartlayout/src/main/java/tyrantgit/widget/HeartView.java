package tyrantgit.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;


public class HeartView extends ImageView {

    private static Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    private int mHeartResId = R.drawable.heart;
    private int mHeartBorderResId = R.drawable.heart_border;

    public HeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public HeartView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setImageResource(mHeartBorderResId);
    }

    public void setColor(int color) {
        Bitmap heart = createHeart(color);
        setImageDrawable(new BitmapDrawable(getResources(), heart));
    }

    public void setColorAndDrawables(int color, int heartResId, int heartBorderResId) {
        mHeartResId = heartResId;
        mHeartBorderResId = heartBorderResId;
        setColor(color);
    }

    public Bitmap createHeart(int color) {
        Bitmap heart = BitmapFactory.decodeResource(getResources(), mHeartResId);
        Bitmap heartBorder = BitmapFactory.decodeResource(getResources(), mHeartBorderResId);
        Bitmap bm = Bitmap.createBitmap(heartBorder.getWidth(), heartBorder.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        Paint p = mPaint;
        canvas.drawBitmap(heartBorder, 0, 0, p);
        p.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
        float dx = (heartBorder.getWidth() - heart.getWidth()) / 2f;
        float dy = (heartBorder.getHeight() - heart.getHeight()) / 2f;
        canvas.drawBitmap(heart, dx, dy, p);
        p.setColorFilter(null);
        return bm;
    }
}
