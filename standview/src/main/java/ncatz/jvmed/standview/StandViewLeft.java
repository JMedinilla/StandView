package ncatz.jvmed.standview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

public class StandViewLeft extends ConstraintLayout {

    private ConstraintLayout parent;
    private TextView background;
    private TextView color;
    private TextView title;
    private ImageView left;
    private ImageView right;

    public StandViewLeft(Context context) {
        super(context);
    }

    public StandViewLeft(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StandViewLeft);
        int backgroundColor = typedArray.getColor(R.styleable.StandViewLeft_backgroundColorL, Color.WHITE);
        Drawable leftImage = typedArray.getDrawable(R.styleable.StandViewLeft_leftImageL);
        Drawable rightImage = typedArray.getDrawable(R.styleable.StandViewLeft_rightImageL);
        String titleText = typedArray.getString(R.styleable.StandViewLeft_titleTextL);
        int titleColor = typedArray.getColor(R.styleable.StandViewLeft_titleColorL, Color.WHITE);
        float titleSize = typedArray.getDimension(R.styleable.StandViewLeft_titleSizeL, 14);
        float titlePadding = typedArray.getDimension(R.styleable.StandViewLeft_titlePaddingL, 0);
        int intPadding = Math.round(titlePadding);
        float colorSize = titleSize + titleSize + Math.round(titleSize / 3);
        int colorPadding = intPadding + intPadding + Math.round(intPadding / 3);
        float backgroundSize = colorSize + titleSize + Math.round(titleSize / 3);
        int backgroundPadding = colorPadding + intPadding + Math.round(intPadding / 3);
        int titleBackground = typedArray.getColor(R.styleable.StandViewLeft_titleBackgroundL, Color.WHITE);
        Typeface titleFont = null;
        if (typedArray.hasValue(R.styleable.StandViewLeft_titleFontL)) {
            int fontId = typedArray.getResourceId(R.styleable.StandViewLeft_titleFontL, -1);
            if (fontId != -1) {
                titleFont = ResourcesCompat.getFont(context, fontId);
            }
        }

        String service = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(service);
        if (inflater != null) {
            parent = (ConstraintLayout) inflater.inflate(R.layout.stand_view_left, this, true);

            background = parent.findViewById(R.id.transparent_background_left);
            color = parent.findViewById(R.id.color_background_left);
            title = parent.findViewById(R.id.textTitle_left);
            left = parent.findViewById(R.id.left_image_left);
            right = parent.findViewById(R.id.right_image_left);

            color.setBackgroundColor(backgroundColor);
            left.setImageDrawable(leftImage);
            right.setImageDrawable(rightImage);
            title.setText(titleText);
            title.setTextColor(titleColor);
            title.setBackgroundColor(titleBackground);
            if (titleFont != null) {
                title.setTypeface(titleFont);
                color.setTypeface(titleFont);
                background.setTypeface(titleFont);
            }
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize);
            title.setPadding(intPadding, intPadding, intPadding, intPadding);
            color.setTextSize(TypedValue.COMPLEX_UNIT_SP, colorSize);
            color.setPadding(colorPadding, colorPadding, colorPadding, colorPadding);
            background.setTextSize(TypedValue.COMPLEX_UNIT_DIP, backgroundSize);
            background.setPadding(backgroundPadding, backgroundPadding, backgroundPadding, backgroundPadding);
        }

        typedArray.recycle();
    }

    public StandViewLeft(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
