package com.example.stevendeweille_pset4;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TodoDatabase db;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = TodoDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();
        ListView list = findViewById(R.id.list);
        adapter = new TodoAdapter(this, cursor);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new ClickHandler());
        list.setOnItemLongClickListener(new HoldHandler());
    }

    public void addItem(View view) {
        TextView inputText = findViewById(R.id.item_input);
        db.insert(inputText.getText().toString(),0);
        inputText.setText("");
        updateData();
    }

    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }

    private class ClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            CheckBox checkbox = view.findViewById(R.id.checkbox);
            if (checkbox.isChecked()) {
                checkbox.setChecked(false);
                db.update(l, 0);
            }
            else {
                checkbox.setChecked(true);
                db.update(l, 1);
            }
            updateData();
        }
    }

    private class HoldHandler implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            db.delete(l);
            updateData();
            return false;
        }
    }
}
