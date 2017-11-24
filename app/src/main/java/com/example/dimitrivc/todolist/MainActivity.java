package com.example.dimitrivc.todolist;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // use the TodoDatabase to get all records from the database
        final TodoDatabase db = TodoDatabase.getInstance(getApplicationContext());
        //TodoDatabase db = TodoDatabase.getInstance(this);
        //db.onUpgrade(db.getWritableDatabase(), 3, 4);

        Cursor cursor = db.selectAll();

        //Integer index = cursor.getColumnIndex("title");
        //String test = cursor.getString(index);
        //Log.d("curosor_test", test);

        adapter = new TodoAdapter(this, cursor);
        final ListView listTodo = findViewById(R.id.listViewToDo);

        // link the ListView to the adapter.
        listTodo.setAdapter(adapter);

        listTodo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Cursor cursor = (Cursor) listTodo.getItemAtPosition(i);

                db.update(cursor);

                updateData();
            }
        });

        listTodo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                db.delete(l);
                // weet niet of het l moet zijn hoor, beetje random
                return false;
            }
        });

    }

    public void addItem(View view) {

        // use TodoDatabase.getInstance() to get to the database object,
        // weet niet of this de goede context is alleen.
        TodoDatabase db = TodoDatabase.getInstance(getApplicationContext());

        // lijkt me dat je de shit die ingevuld is als name in de database moet stoppen.
        EditText editText = findViewById(R.id.editText);
        String addToTodo = editText.getText().toString();

        Integer completeness = 1;

        // and call the insert method that we just defined.
        db.insert(addToTodo, completeness);

        // call updateData.
        updateData();
    }

    private void updateData(){
        TodoDatabase db = TodoDatabase.getInstance(getApplicationContext());
        // need access to db and adapter.
        // the body for the method updateData().
        // You can use the method swapCursor() on the adapter
        // to put in a new cursor for the updated data.
        Cursor cursor = db.selectAll();
        // make a new com.example.dimitrivc.todolist.TodoAdapter
        //TodoAdapter adapter = new TodoAdapter(this, cursor);

        // is het probleem dat ik een nieuwe adapter aanmaak?

        // bij adapter dus nieuwe cursor, die krijg je via selectAll().
        adapter.swapCursor(cursor);
        // with swapCursor, the old cursor is not closed, dus miss moet je die oude nog closen.
    }

}
