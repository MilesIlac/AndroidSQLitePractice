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
    DBEntryRecViewAdapter dbEntryRecViewAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btnCreate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnQuit = findViewById(R.id.btnQuit);
        dbList = findViewById(R.id.dbList);
        txtString = findViewById(R.id.txtString);

        dbEntryRecViewAdapter = new DBEntryRecViewAdapter(dbList, this);
        dbHelper = new DBHelper(getApplicationContext());


        setDbEntryRecViewAdapter();


        btnCreate.setOnClickListener(view -> {

            StringEntry stringEntry = new StringEntry(-1, "insert date", txtString.getText().toString());
            boolean success = dbHelper.addEntry(stringEntry);
            Toast.makeText(MainActivity.this,"Success= " + success, Toast.LENGTH_SHORT).show();
            setDbEntryRecViewAdapter();
        });

        btnUpdate.setOnClickListener(view -> setDbEntryRecViewAdapter());

        btnQuit.setOnClickListener(view -> finish());



    }

    private void setDbEntryRecViewAdapter() {
        dbEntryRecViewAdapter.setEntries(dbHelper.getAllTexts());
        dbList.setAdapter(dbEntryRecViewAdapter);
        dbList.setLayoutManager(new LinearLayoutManager(this));
    }
}