package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static String usernameKey;

    public void loginFunc(View view){
        EditText name = (EditText) findViewById(R.id.userName);
        EditText password = (EditText) findViewById(R.id.password);
        String str = name.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("myapplication", MODE_PRIVATE);
        sharedPreferences.edit().putString("username",str).apply();
        goToActivity2(str);
    }

    public void goToActivity2(String s){
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("message", s);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usernameKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("myapplication", MODE_PRIVATE);

        if(!sharedPreferences.getString(usernameKey,"").equals("")){
            String str = sharedPreferences.getString(usernameKey,"");
            goToActivity2(str);
        }else{
            setContentView(R.layout.activity_main);
        }


    }
}