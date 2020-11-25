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
    private RadioButton museum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        addListenerOnButton();

        /*button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPriceCheckActivity();
            }
        });*/
    }

    /*public void openPriceCheckActivity() {
        Intent intent = new Intent(this, PriceCheckActivity.class);
        startActivity(intent);
    }*/


    public void openPriceCheckActivityNEW(String museumName) {
        Intent intent = new Intent(this, PriceCheckActivity.class);
        intent.putExtra("MUSEUM_NAME", museumName);
        startActivity(intent);
    }

    public void addListenerOnButton() {
        museumGroup = (RadioGroup) findViewById(R.id.radioGroup8);
        button = (Button) findViewById(R.id.submitButton);

        button.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

                //RadioButton r = (RadioButton) museumGroup.getChildAt()
                int selectId = museumGroup.getCheckedRadioButtonId();
                RadioButton r = (RadioButton) findViewById(selectId);
                String radioText = r.getText().toString();

                Context context = getBaseContext();
                CharSequence text = radioText;
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                button = (RadioButton) findViewById(selectId);
                openPriceCheckActivityNEW(radioText);

            }


        });

    }

}