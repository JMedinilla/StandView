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

    private ConstraintLayout layoutC;
    private TextView backgroundT;
    private TextView colorT;
    private TextView titleT;
    private ImageView leftI;
    private ImageView rightI;
    private boolean rightSide;

    public StandView(Context context) {
        super(context);
    }

    public StandView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rightSide = false;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StandView);
        int backgroundColor = typedArray.getColor(R.styleable.StandView_backgroundColor, Color.WHITE);
        Drawable leftImage = typedArray.getDrawable(R.styleable.StandView_leftImage);
        Drawable rightImage = typedArray.getDrawable(R.styleable.StandView_rightImage);
        String titleText = typedArray.getString(R.styleable.StandView_titleText);
        int titleColor = typedArray.getColor(R.styleable.StandView_titleColor, Color.WHITE);

        float titleSize = typedArray.getDimension(R.styleable.StandView_titleSize, 14);
        float colorSize = titleSize + titleSize + Math.round(titleSize / 3);
        float backgroundSize = colorSize + titleSize + Math.round(titleSize / 3);

        float titlePadding = typedArray.getDimension(R.styleable.StandView_titlePadding, 0);
        int intPadding = Math.round(titlePadding);
        int colorPadding = intPadding + intPadding + Math.round(intPadding / 3);
        int backgroundPadding = colorPadding + intPadding + Math.round(intPadding / 3);

        int titleBackground = typedArray.getColor(R.styleable.StandView_titleBackground, Color.WHITE);
        boolean rightSide = typedArray.getBoolean(R.styleable.StandView_titleRightSide, false);

        String service = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(service);
        if (inflater != null) {
            ConstraintLayout parent = (ConstraintLayout) inflater.inflate(R.layout.stand_view, this, true);

            layoutC = parent.findViewById(R.id.parentLayout);
            backgroundT = parent.findViewById(R.id.transparent_background);
            colorT = parent.findViewById(R.id.color_background);
            titleT = parent.findViewById(R.id.textTitle);
            leftI = parent.findViewById(R.id.left_image);
            rightI = parent.findViewById(R.id.right_image);

            colorT.setBackgroundColor(backgroundColor);
            leftI.setImageDrawable(leftImage);
            rightI.setImageDrawable(rightImage);
            titleT.setText(titleText);
            titleT.setTextColor(titleColor);
            titleT.setBackgroundColor(titleBackground);

            titleT.setTextSize(titleSize);
            colorT.setTextSize(colorSize);
            backgroundT.setTextSize(backgroundSize);
            titleT.setPadding(intPadding, intPadding, intPadding, intPadding);
            colorT.setPadding(colorPadding, colorPadding, colorPadding, colorPadding);
            backgroundT.setPadding(backgroundPadding, backgroundPadding, backgroundPadding, backgroundPadding);

            setRightSide(rightSide);
        }

        typedArray.recycle();
    }

    public StandView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBackgroundColor(int color) {
        colorT.setBackgroundColor(color);
    }

    public void setLeftImage(Drawable image) {
        leftI.setImageDrawable(image);
    }

    public void setRightImage(Drawable image) {
        rightI.setImageDrawable(image);
    }

    public void setTitleText(String text) {
        titleT.setText(text);
    }

    public String getTitleText() {
        return titleT.getText().toString();
    }

    public void setTitleColor(int color) {
        titleT.setTextColor(color);
    }

    public int getTitleColor() {
        return titleT.getCurrentTextColor();
    }

    public void setTitleSize(float size) {
        float sizeC = size + size + Math.round(size / 3);
        float sizeB = sizeC + size + Math.round(size / 3);
        titleT.setTextSize(size);
        colorT.setTextSize(sizeC);
        backgroundT.setTextSize(sizeB);
    }

    public float getTitleSize() {
        return titleT.getTextSize();
    }

    public void setTitlePadding(float padding) {
        int intPadding = Math.round(padding);
        int paddingC = intPadding + intPadding + Math.round(intPadding / 3);
        int paddingB = paddingC + intPadding + Math.round(intPadding / 3);
        titleT.setPadding(intPadding, intPadding, intPadding, intPadding);
        colorT.setPadding(paddingC, paddingC, paddingC, paddingC);
        backgroundT.setPadding(paddingB, paddingB, paddingB, paddingB);
    }

    public void setTitleBackground(int color) {
        titleT.setBackgroundColor(color);
    }

    public void setRightSide(boolean rightSide) {
        ConstraintSet set = new ConstraintSet();
        set.clone(layoutC);
        if (rightSide) {
            this.rightSide = true;
            titleT.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
            set.connect(R.id.left_image, ConstraintSet.BOTTOM, R.id.textTitle, ConstraintSet.BOTTOM, 0);
            set.connect(R.id.right_image, ConstraintSet.BOTTOM, R.id.textTitle, ConstraintSet.TOP, 0);
            set.applyTo(layoutC);
        } else {
            this.rightSide = false;
            titleT.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
            set.connect(R.id.left_image, ConstraintSet.BOTTOM, R.id.textTitle, ConstraintSet.TOP, 0);
            set.connect(R.id.right_image, ConstraintSet.BOTTOM, R.id.textTitle, ConstraintSet.BOTTOM, 0);
            set.applyTo(layoutC);
        }
    }

    public boolean getRightSide() {
        return this.rightSide;
    }

    public void setTypeface(Typeface titleFont) {
        if (titleFont != null) {
            titleT.setTypeface(titleFont);
            colorT.setTypeface(titleFont);
            backgroundT.setTypeface(titleFont);
        }
    }

    public Typeface getTypeface() {
        return titleT.getTypeface();
    }
}
