package com.example.baru_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
    }
    public void GoTo_Home(View view){
        startActivity(new Intent(getApplicationContext(),HomePage.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }
    public void GoTo_Services(View view){
        startActivity(new Intent(getApplicationContext(),Services.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

    }
}