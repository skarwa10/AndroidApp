package com.example.skarwa.todoapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.skarwa.todoapp.fragments.ToDoItemEditDialogFragment;
import com.example.skarwa.todoapp.R;
import com.example.skarwa.todoapp.model.ToDoItem;
import com.example.skarwa.todoapp.adaptors.ToDoItemsAdapter;
import com.example.skarwa.todoapp.model.ToDoItem_Table;
import com.example.skarwa.todoapp.utils.ToDoAppConstants;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import static com.example.skarwa.todoapp.utils.ToDoAppConstants.ITEM;
import static com.raizlabs.android.dbflow.sql.language.SQLite.select;

/**
 * This is the main screen of the TO DO App.
 * This fetches the existing items from the DB and displays to the user.
 */
public class ToDoListActivity extends AppCompatActivity implements ToDoItemEditDialogFragment.EditNameDialogListener {
    ArrayList<ToDoItem> items;
    ToDoItemsAdapter itemsAdaptor;
    ListView lvItems;

    private final int REQUEST_CODE = 20;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        lvItems = (ListView)findViewById(R.id.lvItems);

        items = new ArrayList<ToDoItem>();
        items.addAll(getItemsFromDB());
        itemsAdaptor = new ToDoItemsAdapter(this,items);
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
        Intent i = new Intent(ToDoListActivity.this, AddItemActivity.class);

        startActivityForResult(i,REQUEST_CODE); // brings up the second activity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            ToDoItem item = new ToDoItem();

            item.setItemName(data.getExtras().getString(ITEM));
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
