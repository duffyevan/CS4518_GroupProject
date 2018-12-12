package edu.wpi.cs4518_groupproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BurgerBuilder extends AppCompatActivity {

    ArrayList<Price> prices;
    LinearLayout scrollLayout;
    HashMap<CheckBox, Price> priceMap = new HashMap<>();
    Set<Price> burgerComponents = new HashSet<>();
    double totalPrice = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger_builder);

        scrollLayout = findViewById(R.id.scrollLayout);
        prices = getPrices();

        updateTotalPriceDisplay();

        for (Price price : prices){
            CheckBox c = new CheckBox(this);
            c.setText(price.getName() + " ($" + String.format("%.2f", price.getPrice()) + ")");
            c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        burgerComponents.add(priceMap.get(buttonView));
                    }
                    else{
                        burgerComponents.remove(priceMap.get(buttonView));
                    }
                    totalPrice = 0;
                    for (Price p : burgerComponents){
                        totalPrice += p.getPrice();
                    }

                    totalPrice = Math.round(totalPrice*100.0)/100.0;

                    updateTotalPriceDisplay();
                }
            });
            priceMap.put(c, price);
            scrollLayout.addView(c);
        }
        Log.d("Test", String.valueOf(priceMap.get(scrollLayout.getChildAt(0))));
    }

    private ArrayList<Price> getPrices(){
        ArrayList<Price> prices = new ArrayList<Price>();
        try {
            InputStreamReader isr = new InputStreamReader(getAssets().open("prices.csv"));
            CSVReader reader = new CSVReader(isr);
            String[] nextLine;
            while (true) {
                // nextLine[] is an array of values from the line
                nextLine = reader.readNext();
                if (nextLine == null)
                    break;
                Log.d("Checkpoint","Parsing A Price");
                prices.add(new Price(nextLine[0], Math.round(Float.parseFloat(nextLine[1])*100.0)/100.0));
            }
        } catch (IOException e) {
            Log.e("Error", "An Error Occurred Loading The Prices");
            e.printStackTrace();
        }
        return prices;
    }

    private void updateTotalPriceDisplay(){
        TextView priceView = findViewById(R.id.priceView);
        priceView.setText("Total: $" + String.format("%.2f", totalPrice));
    }

}
