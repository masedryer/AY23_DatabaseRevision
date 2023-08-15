package com.myapplicationdev.databaserevision;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button btnInsertRecord, btnRetrieveRecordstv,btnRetrieveRecordslv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertRecord = findViewById(R.id.btnInsertRecord);
        btnRetrieveRecordstv = findViewById(R.id.btnGetRecordtv);
        btnRetrieveRecordslv = findViewById(R.id.btnGetRecordlv);

        btnInsertRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);

            }
        });

        btnRetrieveRecordstv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, RetrieveActivityTextView.class);
                startActivity(intent);
            }
        });
        btnRetrieveRecordslv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, RetrieveActivityListView.class);
                startActivity(intent);
            }
        });

        //hi thia is a test
    }
}