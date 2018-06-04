package ncatz.jvmed.standview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

public class StandView extends ConstraintLayout {

    private TextView background;
    private TextView color;
    private TextView title;

    public StandView(Context context) {
        super(context);
    }

    public StandView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StandView);
        int backgroundColor = typedArray.getColor(R.styleable.StandView_backgroundColor, Color.WHITE);
        Drawable leftImage = typedArray.getDrawable(R.styleable.StandView_leftImage);
        Drawable rightImage = typedArray.getDrawable(R.styleable.StandView_rightImage);
        String titleText = typedArray.getString(R.styleable.StandView_titleText);
        int titleColor = typedArray.getColor(R.styleable.StandView_titleColor, Color.WHITE);
        float titleSize = typedArray.getDimension(R.styleable.StandView_titleSize, 14);
        float titlePadding = typedArray.getDimension(R.styleable.StandView_titlePadding, 0);
        int intPadding = Math.round(titlePadding);
        float colorSize = titleSize + titleSize + Math.round(titleSize / 3);
        int colorPadding = intPadding + intPadding + Math.round(intPadding / 3);
        float backgroundSize = colorSize + titleSize + Math.round(titleSize / 3);
        int backgroundPadding = colorPadding + intPadding + Math.round(intPadding / 3);
        int titleBackground = typedArray.getColor(R.styleable.StandView_titleBackground, Color.WHITE);
        boolean rightSide = typedArray.getBoolean(R.styleable.StandView_titleRightSide, false);

        String service = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(service);
        if (inflater != null) {
            ConstraintLayout parent = (ConstraintLayout) inflater.inflate(R.layout.stand_view, this, true);

            ConstraintLayout layout = parent.findViewById(R.id.parentLayout);
            background = parent.findViewById(R.id.transparent_background);
            color = parent.findViewById(R.id.color_background);
            title = parent.findViewById(R.id.textTitle);
            ImageView left = parent.findViewById(R.id.left_image);
            ImageView right = parent.findViewById(R.id.right_image);

            color.setBackgroundColor(backgroundColor);
            left.setImageDrawable(leftImage);
            right.setImageDrawable(rightImage);
            title.setText(titleText);
            title.setTextColor(titleColor);
            title.setBackgroundColor(titleBackground);

            title.setTextSize(titleSize);
            title.setPadding(intPadding, intPadding, intPadding, intPadding);
            color.setTextSize(colorSize);
            color.setPadding(colorPadding, colorPadding, colorPadding, colorPadding);
            background.setTextSize(backgroundSize);
            background.setPadding(backgroundPadding, backgroundPadding, backgroundPadding, backgroundPadding);

            ConstraintSet set = new ConstraintSet();
            set.clone(layout);
            if (rightSide) {
                title.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
                set.connect(R.id.left_image, ConstraintSet.BOTTOM, R.id.textTitle, ConstraintSet.BOTTOM, 0);
                set.connect(R.id.right_image, ConstraintSet.BOTTOM, R.id.textTitle, ConstraintSet.TOP, 0);
                set.applyTo(layout);
            } else {
                title.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
                set.connect(R.id.left_image, ConstraintSet.BOTTOM, R.id.textTitle, ConstraintSet.TOP, 0);
                set.connect(R.id.right_image, ConstraintSet.BOTTOM, R.id.textTitle, ConstraintSet.BOTTOM, 0);
                set.applyTo(layout);
            }
        }

        typedArray.recycle();
    }

    public StandView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTypeFace(Typeface titleFont) {
        if (titleFont != null) {
            title.setTypeface(titleFont);
            color.setTypeface(titleFont);
            background.setTypeface(titleFont);
        }
    }
}
