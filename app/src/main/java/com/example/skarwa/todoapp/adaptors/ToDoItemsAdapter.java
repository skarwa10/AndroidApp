package com.example.skarwa.todoapp.adaptors;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.skarwa.todoapp.R;
import com.example.skarwa.todoapp.fragments.ToDoItemEditDialogFragment;
import com.example.skarwa.todoapp.model.ToDoItem;

import java.util.ArrayList;

import static com.example.skarwa.todoapp.utils.ToDoAppConstants.EDIT_ITEM;
import static com.example.skarwa.todoapp.utils.ToDoAppConstants.FRAGMENT_EDIT_ITEM_LAYOUT;
import static com.example.skarwa.todoapp.utils.ToDoAppConstants.JOB_DONE_GREETING;

/**
 * This is the Item Adaptor which converts a list of items into view objects to be displayed in list view.
 * Created by skarwa on 8/16/17.
 */

public class ToDoItemsAdapter extends ArrayAdapter<ToDoItem> {
    Context context;
    private ArrayList<ToDoItem> toDoItems;
    private static LayoutInflater inflater=null;


    public ToDoItemsAdapter(Context context, ArrayList<ToDoItem> toDoItems) {
        super(context, R.layout.list_row, toDoItems);
        this.toDoItems = toDoItems;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null)
            view =  LayoutInflater.from(getContext()).inflate(R.layout.list_row, null);

        EditText etItem = (EditText) view.findViewById(R.id.etItem);

        ImageView editImageView = (ImageView) view.findViewById(R.id.ivEdit);

        editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToDoItem item = toDoItems.get(position);
                FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                ToDoItemEditDialogFragment editNameDialogFragment = ToDoItemEditDialogFragment.newInstance(EDIT_ITEM, item.getItemName(),item.getId(),position);
                editNameDialogFragment.show(fragmentManager, FRAGMENT_EDIT_ITEM_LAYOUT);
            }
        });
        ImageView doneImageView = (ImageView) view.findViewById(R.id.ivDone);

        doneImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(context, JOB_DONE_GREETING, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                ToDoItem item = toDoItems.remove(position);
                item.delete();
                notifyDataSetChanged();
            }
        });
        etItem.setText(toDoItems.get(position).getItemName());
        return view;
    }
}
