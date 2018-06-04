package ncatz.jvmed.standviewsample;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ncatz.jvmed.standview.StandView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StandView first = findViewById(R.id.firstStand);
        StandView second = findViewById(R.id.secondStand);
        StandView third = findViewById(R.id.thirdStand);
        first.setTypeFace(ResourcesCompat.getFont(MainActivity.this, R.font.game_thrones));
        second.setTypeFace(ResourcesCompat.getFont(MainActivity.this, R.font.learners));
        third.setTypeFace(ResourcesCompat.getFont(MainActivity.this, R.font.italiana));
    }
}
