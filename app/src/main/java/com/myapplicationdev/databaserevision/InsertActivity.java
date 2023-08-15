package com.myapplicationdev.databaserevision;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity {

    Button btnInsert;
    EditText etContent, etPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        btnInsert = findViewById(R.id.btnInsert);
        etContent = findViewById(R.id.etContent);
        etPriority = findViewById(R.id.etPriority);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the activity's Context
                DBHelper db = new DBHelper(InsertActivity.this);


                if(etContent.getText().toString().isEmpty()){
                    etContent.setError("Cannot be left blank");
                }
                else if(etPriority.getText().toString().isEmpty()){
                    etPriority.setError("Cannot be left blank");
                }
                else{
                    String content = etContent.getText().toString();
                    int priority = Integer.parseInt(etPriority.getText().toString());

                    db.insertTask(content, priority);
                    db.close();
                    Toast.makeText(InsertActivity.this, "Task added", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}