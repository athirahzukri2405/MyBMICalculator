package com.example.my.mybmicalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class AboutApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_app);
        TextView textView = findViewById(R.id.github_link);
        textView.setText(getString(R.string.link_description, "https://github.com/athirahzukri2405/MyBMICalculator"));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
