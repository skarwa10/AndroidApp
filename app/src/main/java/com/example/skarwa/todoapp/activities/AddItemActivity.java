package com.example.skarwa.todoapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.skarwa.todoapp.R;
import com.example.skarwa.todoapp.utils.ToDoAppConstants;

import static com.example.skarwa.todoapp.R.id.etNewItem;
import static com.example.skarwa.todoapp.utils.ToDoAppConstants.ITEM;

/***
 * Add Item activity.
 * This screen lets you add a new Item/Task to your To Do list
 */
public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
    }

    public void onSubmit(View v) {
        EditText etNewItem =  (EditText) findViewById(R.id.etNewItem);
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra(ITEM, etNewItem.getText().toString());
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response

        etNewItem.setText("");
        // closes the activity and returns to first screen
        this.finish();
    }
}
