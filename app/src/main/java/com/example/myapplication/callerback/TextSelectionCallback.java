package com.example.myapplication.callerback;


import android.graphics.Color;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
//import android.view.ActionMode;
import androidx.appcompat.view.ActionMode.Callback;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.example.myapplication.R;

public class TextSelectionCallback implements ActionMode.Callback {

    EditText editText;

    public TextSelectionCallback(EditText editText) {
        this.editText = editText;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.text_selection_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        int start = editText.getSelectionStart();
        int end = editText.getSelectionEnd();
        Spannable spannable = editText.getText();

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
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    private void colorSelectedText(Spannable span, int start, int end, int color){
        span.setSpan(new BackgroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}
