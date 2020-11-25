package com.example.nycmuseums;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Spinner;

public class PriceCheckActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ImageButton museumButton;
    Button calculateButton;
    TextView childRate;
    TextView adultRate;
    TextView seniorRate;
    Spinner childQuantity;
    Spinner adultQuantity;
    Spinner seniorQuantity;
    TextView total;
    TextView tax_total;
    TextView final_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_check);
        setTitle(R.string.price_name);

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


        childRate = findViewById(R.id.childRate);
        adultRate = findViewById(R.id.adultRate);
        seniorRate = findViewById(R.id.seniorRate);

        childQuantity = findViewById(R.id.childQuantity);
        childQuantity.setOnItemSelectedListener(this);
        adultQuantity = findViewById(R.id.adultQuantity);
        adultQuantity.setOnItemSelectedListener(this);
        seniorQuantity = findViewById(R.id.seniorQuantity);
        seniorQuantity.setOnItemSelectedListener(this);

        calculateButton = (Button) findViewById(R.id.calculate_btn);
        total = (TextView) findViewById(R.id.priceTotal);
        tax_total = (TextView) findViewById(R.id.taxTotal);
        final_total = (TextView) findViewById(R.id.finalTotal);

        total.setText("0.00");
        tax_total.setText("0.00");
        final_total.setText("0.00");


        //Calculate total price of tickets
        calculateButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                final double TAX_RATE = 0.08875;

                //pricing rates for each type of ticket
                double child_rate = Double.parseDouble(childRate.getText().toString());
                double adult_rate = Double.parseDouble(adultRate.getText().toString());
                double senior_rate = Double.parseDouble(seniorRate.getText().toString());

                //get quantity from each spinner as an integer
                int child_quant = Integer.parseInt(childQuantity.getSelectedItem().toString());
                int adult_quant = Integer.parseInt(adultQuantity.getSelectedItem().toString());;
                int senior_quant = Integer.parseInt(seniorQuantity.getSelectedItem().toString());

                //calculate subtotal
                double subtotal = ((child_rate * child_quant) + (adult_rate * adult_quant) +
                        (senior_rate * senior_quant));

                //calculate tax
                double calculated_tax = subtotal * TAX_RATE;

                //calculate final price
                double calculated_price = subtotal + calculated_tax;

                // set calculated numbers to text
                total.setText(String.format("%.2f", subtotal));
                tax_total.setText(String.format("%.2f", calculated_tax));
                final_total.setText(String.format("%.2f", calculated_price));

            }
        });

}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}