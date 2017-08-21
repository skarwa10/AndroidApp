package com.example.skarwa.todoapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.skarwa.todoapp.R.id.etNewItem;
import static com.example.skarwa.todoapp.ToDoItem_Table.id;
import static com.raizlabs.android.dbflow.sql.language.SQLite.select;
import static com.raizlabs.android.dbflow.sql.language.property.PropertyFactory.from;

public class ToDoActivity extends AppCompatActivity implements EditNameDialogFragment.EditNameDialogListener {
    ArrayList<ToDoItem> items;
    ToDoItemAdapter itemsAdaptor;
    ListView lvItems;

    private final int REQUEST_CODE = 20;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        lvItems = (ListView)findViewById(R.id.lvItems);

        items = new ArrayList<ToDoItem>();
        items.addAll(getItemsFromDB());
        itemsAdaptor = new ToDoItemAdapter(this,items);
        lvItems.setAdapter(itemsAdaptor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_AddItem){
            launchAddItemView();
        }
        return super.onOptionsItemSelected(item);
    }


    public void launchAddItemView() {
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(ToDoActivity.this, AddItemActivity.class);

        startActivityForResult(i,REQUEST_CODE); // brings up the second activity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            ToDoItem item = new ToDoItem();

            item.setItemName(data.getExtras().getString("item"));
            item.insert();
            items.add(item);
            itemsAdaptor.notifyDataSetChanged();
        }
    }

    @Override
    public void onFinishEditDialog(String editedText,int id,int position) {
        ToDoItem item = getItemById(id);
        item.setItemName(editedText);
        item.update();
        items.set(position,item);
        itemsAdaptor.notifyDataSetChanged();
    }

    public List<ToDoItem> getItemsFromDB(){
        return SQLite.select().
                from(ToDoItem.class).queryList();
    }

    public ToDoItem getItemById(int id){
        return select().from(ToDoItem.class).where(ToDoItem_Table.id.eq(id)).querySingle();
    }
}
