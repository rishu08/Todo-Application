package com.example.rishabh.todoapp;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    EditText edtitle,etdescription;
    Button btnadd;
    ToDoDB toDoDB;
     RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtitle = findViewById(R.id.etTitle);
        etdescription = findViewById(R.id.etDescription);
        btnadd = findViewById(R.id.btnAdd);

        toDoDB = new ToDoDB(this);

        ArrayList<Task> result = fetchTaks();
        recyclerView = findViewById(R.id.rv);

        ListAdapter listAdapter = new ListAdapter(result,getBaseContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(listAdapter);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtitle.getText().toString().trim()))
                {
                    Toast.makeText(MainActivity.this, "Enter Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etdescription.getText().toString().trim()))
                {
                    Toast.makeText(MainActivity.this, "Enter Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                String sTitle = edtitle.getText().toString();
                String sDescription = etdescription.getText().toString();

                Task task = new Task(System.currentTimeMillis(),sTitle,sDescription,0);
                long position = toDoDB.insertTask(task);

                ArrayList<Task> result = fetchTaks();

                ListAdapter listAdapter = new ListAdapter(result,getBaseContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                recyclerView.setAdapter(listAdapter);


                Log.e("TAG", "onClick:----- "+ position );
                Log.e("TAG", "onClick: ----------------------------" );
                Log.e("TAG", "onClick:SIZE ===="+fetchTaks().size());
                Log.e("TAG", "onClick: ----------------------------" );


            }
        });


    }

    @Override
    protected void onStop() {
        ArrayList<Task> result = fetchTaks();

        ListAdapter listAdapter = new ListAdapter(result,getBaseContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(listAdapter);

        super.onStop();
    }

    ArrayList<Task> fetchTaks()
    {
        return toDoDB.getAllTask();
    }
}
