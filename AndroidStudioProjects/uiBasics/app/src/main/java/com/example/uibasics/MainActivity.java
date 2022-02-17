package com.example.uibasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button setInJavaBtn;
    private Spinner dateSpinner, timesSpinner;
    private CheckBox checkOne, checkTwo, checkThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkOne = findViewById(R.id.checkOne);
        checkOne = findViewById(R.id.checkTwo);
        checkOne = findViewById(R.id.checkThree);

        System.out.println("This is fucking retarded");
        if (checkOne.isChecked()) {
            Toast.makeText(this, "Suck me", Toast.LENGTH_SHORT).show();
        }

        checkOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "You checked the first Box. Whoop", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "This is retarded", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setInJavaBtn = findViewById(R.id.setInJavaBtn);
        setInJavaBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Set in Java btn works", Toast.LENGTH_SHORT).show();
                System.out.println("This is a shit show");
            }
        });

        dateSpinner = findViewById(R.id.dates);
        timesSpinner = findViewById(R.id.times);


        ArrayList<String> times = new ArrayList<>();
        times.add("11:00");
        times.add("12:00");
        times.add("1:00");
        times.add("2:00");

        ArrayAdapter<String> timesAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                times
        );
    }

    public void doneBtnClicked(View view) {

        Toast.makeText(this, "You done baby", Toast.LENGTH_SHORT).show();

    }
}