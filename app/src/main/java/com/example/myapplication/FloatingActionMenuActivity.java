package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class FloatingActionMenuActivity extends AppCompatActivity {

    private ActionMode actionMode;
    private int start, end;
    private Spannable spannable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_menu);

        EditText editText = findViewById(R.id.textOne);
        start = editText.getSelectionStart();
        end = editText.getSelectionEnd();
        spannable = editText.getText();

        //editText.setCustomSelectionActionModeCallback(new TextSelectionCallback(editText));
        editText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(actionMode!=null){
                    return false;
                }

                actionMode = startSupportActionMode(actionModeCallback);

                return true;
            }
        });

    }

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.text_selection_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {



            switch (item.getItemId()){
                case R.id.miBlue:
                    colorSelectedText(spannable, start, end, Color.BLUE);
                    break;
                case R.id.miRed:
                    colorSelectedText(spannable, start, end, Color.RED);
                    break;
                case R.id.miGreen:
                    colorSelectedText(spannable, start, end, Color.GREEN);
                    break;
            }
            return true;
            /*switch (item.getItemId()){
                case R.id.miRed:
                    Toast.makeText(FloatingActionMenuActivity.this,"Red selected",Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.miBlue:
                    Toast.makeText(FloatingActionMenuActivity.this,"Blue selected",Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.miGreen:
                    Toast.makeText(FloatingActionMenuActivity.this,"Green selected",Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                    default:
                        return false;
            }*/

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }

        private void colorSelectedText(Spannable span, int start, int end, int color){
            span.setSpan(new BackgroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    };


}
