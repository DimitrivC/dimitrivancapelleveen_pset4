package com.example.dimitrivc.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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

        TextView todoItem = view.findViewById(R.id.textViewTodoItem);
        TextView completedview = view.findViewById(R.id.textViewCompleted);

       // if (cursor.moveToFirst()){
         //   while (!cursor.isAfterLast()){

                Integer name = cursor.getColumnIndex("title");
                String name2 = cursor.getString(name);
                //Log.d("taart", name2);
                todoItem.setText(name2);

                Integer name3 = cursor.getColumnIndex("completed");
                Integer name4 = cursor.getInt(name3);
                //Log.d("taart1", String.valueOf(name4));

                //CheckBox completedness = view.findViewById(R.id.checkBox);

                completedview.setText(String.valueOf(name4));

            //    cursor.moveToNext();
          //  }
       // }

    // EINDE BINDVIEW
    }

// EINDE CLASS
}
