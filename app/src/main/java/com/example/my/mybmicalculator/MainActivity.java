package com.example.my.mybmicalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText heightText;
    EditText weightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightText = findViewById(R.id.heightEditText);
        weightText = findViewById(R.id.weightEditText);

        // Retrieve the saved data and populate the EditText fields if available
        retrieveSavedData();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Save the data when the app is stopped
        saveData();
    }

    private void saveData() {
        // Get the user input from the EditText fields
        String height = heightText.getText().toString();
        String weight = weightText.getText().toString();

        // Save the data to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("height", height);
        editor.putString("weight", weight);
        editor.apply();
    }

    private void retrieveSavedData() {
        // Retrieve the data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String height = sharedPreferences.getString("height", "");
        String weight = sharedPreferences.getString("weight", "");

        // Populate the EditText fields with the saved data
        heightText.setText(height);
        weightText.setText(weight);
    }

    public void calculateButtonClick(View view) {
        double height = 0;
        double weight = 0;

        if (!heightText.getText().toString().equals("") && Double.parseDouble(heightText.getText().toString()) != 0) {
            height = Double.parseDouble(heightText.getText().toString());
        }

        if (!weightText.getText().toString().equals("") && Double.parseDouble(weightText.getText().toString()) != 0) {
            weight = Double.parseDouble(weightText.getText().toString());
        }

        Health health = new Health();
        double bmiResult = health.calculateBMI(height, weight);

        String resultText;

        if (bmiResult != -1) {
            String bmiCat = health.determineCategory(bmiResult);
            String bmiRisk = health.determineHealthRisk(bmiResult);
            resultText = "Your BMI index is " + String.format("%.2f", bmiResult) + "\nBMI category: " + bmiCat + "\nHealth Risk: " + bmiRisk;

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("BMI");
            alertDialogBuilder.setMessage(resultText);
            alertDialogBuilder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    heightText.setText("");
                    weightText.setText("");
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else {
            Toast.makeText(this, health.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_item) {
            // Open the AboutActivity when "About App" menu item is clicked
            Intent intent = new Intent(MainActivity.this, AboutApp.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
