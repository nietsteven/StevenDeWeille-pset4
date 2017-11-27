package com.example.stevendeweille_pset4;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class TodoAdapter extends ResourceCursorAdapter {

    public TodoAdapter(Context context, Cursor cursor) {
        super(context, R.layout.row_todo, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView item = view.findViewById(R.id.item);
        CheckBox checkbox = view.findViewById(R.id.checkbox);

        item.setText(cursor.getString(cursor.getColumnIndex("title")));
        int bool = cursor.getInt(cursor.getColumnIndex("completed"));
        if (bool == 1) {
            checkbox.setChecked(true);
        }
        else {
            checkbox.setChecked(false);
        }
    }
}
