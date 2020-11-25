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

    ImageButton museumButton;
    Button calculateButton;
    EditText childQuantity;
    EditText adultQuantity;
    EditText seniorQuantity;
    TextView total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_check);

        Intent intent = getIntent();
        String museumName = intent.getExtras().getString("MUSEUM_NAME");


        Context context = getBaseContext();
        CharSequence text = "Maximum of 5 tickets for each!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        museumButton = (ImageButton) findViewById(R.id.moma_button);

        //Open Chrome and direct to MOMA Link
        museumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri museumSelect = Uri.parse("https://www.moma.org/visit/");

                if (museumName.equals("MOMA")) {
                    museumSelect = Uri.parse("https://www.moma.org/visit/");
                }
                else if (museumName.equals("MET")) {
                    museumSelect = Uri.parse("https://www.metmuseum.org/");
                }
                else if (museumName.equals("Guggenheim")) {
                    museumSelect = Uri.parse("https://www.guggenheim.org/");
                }
                else if (museumName.equals("Whitney")) {
                    museumSelect = Uri.parse("https://whitney.org/");
                }

                Intent intent = new Intent(Intent.ACTION_VIEW, museumSelect);
                startActivity(intent);
            }
        });


        childQuantity = (EditText) findViewById(R.id.childQuantity);
        adultQuantity = (EditText) findViewById(R.id.adultQuantity);
        seniorQuantity = (EditText) findViewById(R.id.seniorQuantity);

        calculateButton = (Button) findViewById(R.id.calculate_btn);
        total = (TextView) findViewById(R.id.priceTotal);


        total.setText("0.00");

        //Calculate total price of tickets
        calculateButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final double TAX_RATE = 0.08875;
                final double CHILD_RATE = 14;
                final double ADULT_RATE = 25;
                final double SENIOR_RATE = 18;
                final int DEFAULT_QUANT = 0;
                final int MAX_TICKETS = 5;
                int child_quant;
                int adult_quant;
                int senior_quant;



                if (childQuantity.getText().toString().length() > 0) {
                    child_quant = Integer.parseInt(childQuantity.getText().toString());
                }
                else {
                    child_quant = DEFAULT_QUANT;
                }

                if (adultQuantity.getText().toString().length() > 0) {
                    adult_quant = Integer.parseInt(adultQuantity.getText().toString());
                }
                else {
                    adult_quant = DEFAULT_QUANT;
                }

                if (seniorQuantity.getText().toString().length() > 0) {
                    senior_quant = Integer.parseInt(seniorQuantity.getText().toString());
                }
                else {
                    senior_quant = DEFAULT_QUANT;
                }


                if(child_quant > MAX_TICKETS || adult_quant > MAX_TICKETS || senior_quant > MAX_TICKETS) {
                    Context context = getBaseContext();
                    CharSequence text = "INVALID: Maximum of 5 tickets for each type is required.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {
                    //calculate subtotal
                    double subtotal = ((CHILD_RATE * child_quant) + (ADULT_RATE * adult_quant) +
                            (SENIOR_RATE * senior_quant));

                    //calculate tax
                    double total_tax = subtotal * TAX_RATE;

                    //calculate final price
                    double total_price = subtotal + total_tax;

                    // set total price to total TextView
                    total.setText(String.format("%.2f", total_price));
                }

            }
        });

}

}