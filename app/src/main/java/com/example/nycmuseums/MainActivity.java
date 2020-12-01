package com.example.nycmuseums;

/**
 * The MainActivity class is the class that displays the first activity with the
 * list of 4 museums, and allows the user to choose one of the four options.
 * @authors Joshua Atienza, Kyle Lee
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button button;
    private RadioGroup museumGroup;

    /**
     * Sets MainActivity UI with its title as soon as it's created
     * @param savedInstanceState The associated Bundle with MainActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        addListenerOnButton();

    }


    /**
     * Adds the radio group of museums to the MainActivity
     */
    public void addListenerOnButton() {
        museumGroup = (RadioGroup) findViewById(R.id.museum_radio);
        button = (Button) findViewById(R.id.submitButton);

        button.setOnClickListener(new View.OnClickListener() {

            /**
             * Redirects user to appropriate museum pricing page, according to selection
             * @param v The view being clicked, which is the submit button
             */
            @Override
            public void onClick(View v) {

                //get the selected museum from the radio group
                int selectId = museumGroup.getCheckedRadioButtonId();
                RadioButton r = (RadioButton) findViewById(selectId);
                String radioText = r.getText().toString();

                //pass the name of the museum into the new Intent for the next activity
                Intent i = new Intent(MainActivity.this, PriceCheckActivity.class);
                i.putExtra("MUSEUM_NAME",radioText);
                button = (RadioButton) findViewById(selectId);
                startActivity(i);

            }


        });

    }


}