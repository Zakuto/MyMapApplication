package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.myapplication.callerback.TextSelectionCallback;

public class FloatingActionMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_menu);

        EditText editText = findViewById(R.id.textOne);
        editText.setCustomSelectionActionModeCallback(new TextSelectionCallback(editText));
    }
}
