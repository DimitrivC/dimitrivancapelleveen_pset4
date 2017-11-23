package com.example.dimitrivc.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Layout;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.example.dimitrivc.todolist.R;

/**
 * Created by DimitrivC on 20-11-2017.
 */

// ResourceCursorAdapter: An easy adapter that creates views defined in an XML file. You can specify the XML file that defines the appearance of the views
public class TodoAdapter extends ResourceCursorAdapter{

        public TodoAdapter(Context context, Cursor cursor) {
            super(context, R.layout.row_todo, cursor, 0);
                // maar, zou met layout ipv id moeten, maar dat werkt niet.
                // al nu ook niet echt: super.
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
            // this method fills the right elements with data from the cursor.
            // lijkt me dat de controls, de elements (zoals textview) gevuld moeten worden met data van de cursor

        TextView todoItem = view.findViewById(R.id.textViewTodoItem);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String data = cursor.getString(cursor.getColumnIndex("Dishes"));
            todoItem.setText(data);
        }

        //if (cursor) {

//            TextView todoItem = view.findViewById(R.id.textViewTodoItem);
//            Integer name = cursor.getColumnIndex("Dishes");
//            //String column_value = cursor.getString(name);
//            // gets the column index for a column named name:
//            todoItem.setText(name.toString());
//            // werkt mogelijk niet goed.

            TextView completedview = view.findViewById(R.id.textViewCompleted);
            // retrieves the value of one column as an int (for completeness I guess).
            Integer completeness_int = cursor.getInt(2);
            if (completeness_int == 0)
                completedview.setText("completed");
            else
                completedview.setText(completeness_int.toString());
        //}

        // miss:
        //cursor.close();

    // EINDE BINDVIEW
    }

// EINDE CLASS
}
