package com.milesilac.androidsqlitepracticer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnCreate, btnUpdate, btnQuit;
    RecyclerView dbList;
    EditText txtString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btnCreate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnQuit = findViewById(R.id.btnQuit);
        dbList = findViewById(R.id.dbList);
        txtString = findViewById(R.id.txtString);

        DBEntryRecViewAdapter dbEntryRecViewAdapter = new DBEntryRecViewAdapter();
        dbList.setAdapter(dbEntryRecViewAdapter);
        dbList.setLayoutManager(new LinearLayoutManager(this));

        btnCreate.setOnClickListener(view -> {

            StringEntry stringEntry = new StringEntry(-1, "insert date", txtString.getText().toString());


            DBHelper dbHelper = new DBHelper(getApplicationContext());
            boolean success = dbHelper.addEntry(stringEntry);

            Toast.makeText(MainActivity.this,"Success= " + success, Toast.LENGTH_SHORT).show();
        });

        btnUpdate.setOnClickListener(view -> {

            DBHelper dbHelper = new DBHelper(getApplicationContext());
            ArrayList<StringEntry> stringEntries = dbHelper.getAllTexts();
            boolean test = dbEntryRecViewAdapter.setEntries(stringEntries);

            Toast.makeText(MainActivity.this,"Success = "+test+"\n"+stringEntries.toString(), Toast.LENGTH_SHORT).show();
            dbList.setAdapter(dbEntryRecViewAdapter);
            dbList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        });

        btnQuit.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this,"btnQuit clicked", Toast.LENGTH_SHORT).show();
        });
    }
}