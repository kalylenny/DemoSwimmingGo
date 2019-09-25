package com.example.swimminggo.view.coach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.swimminggo.R;

public class AddNewSwimmer extends AppCompatActivity {

    ElegantNumberButton numberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_swimmer);

        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);

        numberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                String num = numberButton.getNumber();
            }
        });
        numberButton.setRange(1,10);
    }
}
