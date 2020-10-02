package android.example.dicerollapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    Button rollDice;
    ImageView imageV1, imageV2;
    int resultOne;
    int resultTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollDice = (Button) findViewById(R.id.roll);
        imageV1 = (ImageView) findViewById(R.id.image1);
        imageV2 = (ImageView) findViewById(R.id.image2);

        if (savedInstanceState!= null) {
            resultOne = savedInstanceState.getInt("left");
            resultTwo = savedInstanceState.getInt("right");
        }

        rollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valueOne = randomDice();
                int valueTwo = randomDice();

                resultOne = getResources().getIdentifier("dice_" + valueOne, "drawable", "android.example.dicerollapp");
                resultTwo = getResources().getIdentifier("dice_" + valueTwo, "drawable", "android.example.dicerollapp");

                imageV1.setImageResource(resultOne);
                imageV2.setImageResource(resultTwo);
            }
        });

    }

    public static int randomDice() {
        return RANDOM.nextInt(6) + 1;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("left", resultOne);
        outState.putInt("right", resultTwo);

    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        resultOne = savedInstanceState.getInt("left");
        resultTwo = savedInstanceState.getInt("right");

        imageV1.setImageResource(resultOne);
        imageV2.setImageResource(resultTwo);
    }
}