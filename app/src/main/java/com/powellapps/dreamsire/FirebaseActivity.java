package com.powellapps.dreamsire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        DatabaseReference mDatabase;

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("testes").setValue("oi");
    }
}
