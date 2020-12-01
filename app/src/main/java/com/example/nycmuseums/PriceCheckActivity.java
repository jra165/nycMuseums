package com.example.nycmuseums;

/**
 * The PriceCheck class is the class that displays appropriate museum information based on selection
 * and allows the user to calculate the cost of their museum visit by selecting quantities
 * of different types of tickets.
 * @authors Joshua Atienza, Kyle Lee
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Spinner;

public class PriceCheckActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView museumInfo;
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


    /**
     * Sets the initial view of the PriceCheckActivity with the appropriate title,
     * museum image and pricing info
     * @param savedInstanceState The associated Bundle with PriceCheckActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_check);
        setTitle(R.string.price_name);

        //Display toast message that only 5 tickets of each kind can be selected
        Context context = getBaseContext();
        CharSequence text = context.getResources().getString(R.string.toast_message);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        museumButton = (ImageButton) findViewById(R.id.museum_button);
        childRate = findViewById(R.id.childRate);
        adultRate = findViewById(R.id.adultRate);
        seniorRate = findViewById(R.id.seniorRate);
        museumInfo = findViewById(R.id.museum_info);

        Intent intent = getIntent();
        String museumName = intent.getExtras().getString(context.getResources().getString(R.string.museum_indicator));

        //display MOMA information
        if(museumName.equals(context.getResources().getString(R.string.moma_name))) {
            museumButton.setImageResource(R.drawable.moma);
            museumInfo.setText(R.string.moma_info);
            childRate.setText(R.string.cr_name);
            adultRate.setText(R.string.ar_name);
            seniorRate.setText(R.string.sr_name);
        }

        //display MET information
        else if(museumName.equals(context.getResources().getString(R.string.met_name))) {
            museumButton.setImageResource(R.drawable.met);
            museumInfo.setText(R.string.met_info);
            childRate.setText(R.string.cr_name_met);
            adultRate.setText(R.string.ar_name_met);
            seniorRate.setText(R.string.sr_name_met);
        }

        //display Guggenheim information
        else if(museumName.equals(context.getResources().getString(R.string.guggenheim_name))) {
            museumButton.setImageResource(R.drawable.guggenheim);
            museumInfo.setText(R.string.guggenheim_info);
            childRate.setText(R.string.cr_name_gug);
            adultRate.setText(R.string.ar_name_gug);
            seniorRate.setText(R.string.sr_name_gug);
        }

        //display Whitney information
        else if(museumName.equals(context.getResources().getString(R.string.whitney_name))) {
            museumButton.setImageResource(R.drawable.whitney);
            museumInfo.setText(R.string.whitney_info);
            childRate.setText(R.string.cr_name_whit);
            adultRate.setText(R.string.ar_name_whit);
            seniorRate.setText(R.string.sr_name_whit);
        }

        //Open Chrome and direct to appropriate museum link
        museumButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Redirects the user to th appropriate museum link
             * @param v The view being clicked, which is the ImageButton
             */
            @Override
            public void onClick(View v) {

                Uri museumSelect = Uri.parse(context.getResources().getString(R.string.moma_link));

                //link to moma
                if (museumName.equals(context.getResources().getString(R.string.moma_name))) {
                    museumSelect = Uri.parse(context.getResources().getString(R.string.moma_link));
                }

                //link to met
                else if (museumName.equals(context.getResources().getString(R.string.met_name))) {
                    museumSelect = Uri.parse(context.getResources().getString(R.string.met_link));
                }

                //link to guggenheim
                else if (museumName.equals(context.getResources().getString(R.string.guggenheim_name))) {
                    museumSelect = Uri.parse(context.getResources().getString(R.string.gugg_link));
                }

                //link to whitney
                else if (museumName.equals(context.getResources().getString(R.string.whitney_name))) {
                    museumSelect = Uri.parse(context.getResources().getString(R.string.whit_link));
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

        total.setText(R.string.default_num);
        tax_total.setText(R.string.default_num);
        final_total.setText(R.string.default_num);


        //Calculate total price of tickets
        calculateButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Calculates the total price of the tickets based on quantities selected and
             * associated ticket price rates
             * @param v The view being clicked, which is the Calculate button
             */
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

    /**
     * Interface definition for a callback to be invoked when an item in this view has been selected.
     * Callback method to be invoked when an item in this view has been selected.
     * @param parent The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * Callback method to be invoked when the selection disappears from this view.
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}