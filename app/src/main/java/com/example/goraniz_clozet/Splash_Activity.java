package com.example.goraniz_clozet;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, Login_Activity.class);

        try{
            Thread.sleep(3000);
            startActivity(intent);
            finish();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finish();
    }
}
