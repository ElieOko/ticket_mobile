package com.odc.ticket.ui.components;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.odc.ticket.R;


public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.MaterialTheme_NoActionBar);
//        FirebaseApp.initializeApp(this);

        super.onCreate(savedInstanceState);
    }
}
