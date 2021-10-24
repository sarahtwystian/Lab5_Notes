package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.internal.NavigationMenuItemView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    TextView textView;
    RelativeLayout options;
    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = (TextView) findViewById(R.id.textView);
        SharedPreferences sharedPreferences = getSharedPreferences("myapplication", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        textView.setText("Welcome " + username +"!");

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);

        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        notes = dbHelper.readNotes(username);

        ArrayList<String> displayNotes = new ArrayList<>();
        for(Note note: notes) {
            displayNotes.add(String.format("Title:%s\nDate:%s", note.getTitle(), note.getDate()));
        }

        // Use ListView view to display notes on screen
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra("noteid", position);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logOut:
                SharedPreferences sharedPreferences = getSharedPreferences("myapplication", MODE_PRIVATE);
                sharedPreferences.edit().remove(MainActivity.usernameKey).apply();
                goToActivity();
                break;
            case R.id.addNote:
                Intent intent3 = new Intent(this, MainActivity3.class);
                //intent.putExtra("message", s);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("message", s);
        startActivity(intent);
    }




}