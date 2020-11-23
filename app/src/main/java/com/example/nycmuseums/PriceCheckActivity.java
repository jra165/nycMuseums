package com.example.nycmuseums;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PriceCheckActivity extends AppCompatActivity {

    ImageButton momaButton;
    Button calculateButton;
    EditText childQuantity;
    EditText adultQuantity;
    EditText seniorQuantity;
    TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_check);

        Context context = getBaseContext();
        CharSequence text = "Maximum of 5 tickets for each!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        momaButton = (ImageButton) findViewById(R.id.moma_button);

        //Open Chrome and direct to MOMA Link
        momaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.moma.org/visit/"));
                startActivity(intent);
            }
        });

        childQuantity = (EditText) findViewById(R.id.childQuantity);
        adultQuantity = (EditText) findViewById(R.id.adultQuantity);
        seniorQuantity = (EditText) findViewById(R.id.seniorQuantity);
        childQuantity.setText("0");
        adultQuantity.setText("0");
        seniorQuantity.setText("0");


        calculateButton = (Button) findViewById(R.id.calculate_btn);
        total = (TextView) findViewById(R.id.priceTotal);

        //Calculate total price of tickets
        calculateButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final double TAX_RATE = 0.08875;
                final double CHILD_RATE = 0;
                final double ADULT_RATE = 25;
                final double SENIOR_RATE = 18;
                int child_quant;
                int adult_quant;
                int senior_quant;


                try {

                    child_quant = Integer.parseInt(childQuantity.getText().toString());
                    adult_quant = Integer.parseInt(adultQuantity.getText().toString());
                    senior_quant = Integer.parseInt(seniorQuantity.getText().toString());

                    //calculate subtotal
                    double subtotal = ((CHILD_RATE * child_quant) + (ADULT_RATE * adult_quant) +
                            (SENIOR_RATE * senior_quant));

                    //calculate tax
                    double total_tax = subtotal * TAX_RATE;

                    //calculate final price
                    double total_price = subtotal + total_tax;

                    // set total price to total TextView
                    total.setText(Double.toString(total_price));

                }
                catch(NumberFormatException ex) {
                    //They didn't enter a number.  Pop up a toast or warn them in some other way

                    Context context = getBaseContext();
                    CharSequence text = "Missing Quantity!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
                //total.setText(String.format("%0.2f", total_price));
            }
        });

}

}