package edu.wpi.cs4518_groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void toBurgerBuilderActivity(View view) {
        Intent intent = new Intent(this, BurgerBuilder.class);
        startActivity(intent);
    }
    public void toRecommendationActivity(View view) {
        Intent intent = new Intent(this, RecommendationAvtivity.class);
        startActivity(intent);
    }
}
