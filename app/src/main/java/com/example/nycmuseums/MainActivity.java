package com.example.nycmuseums;

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
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        addListenerOnButton();

    }


    /**
     *
     */
    public void addListenerOnButton() {
        museumGroup = (RadioGroup) findViewById(R.id.museum_radio);
        button = (Button) findViewById(R.id.submitButton);

        button.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {

                int selectId = museumGroup.getCheckedRadioButtonId();
                RadioButton r = (RadioButton) findViewById(selectId);
                String radioText = r.getText().toString();
                
                Intent i = new Intent(MainActivity.this, PriceCheckActivity.class);
                i.putExtra("MUSEUM_NAME",radioText);
                button = (RadioButton) findViewById(selectId);
                startActivity(i);

            }


        });

    }


}