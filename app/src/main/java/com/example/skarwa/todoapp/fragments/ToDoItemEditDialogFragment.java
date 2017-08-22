package com.example.skarwa.todoapp.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.skarwa.todoapp.R;

import static com.example.skarwa.todoapp.utils.ToDoAppConstants.CANCEL_BUTTON_VALUE;
import static com.example.skarwa.todoapp.utils.ToDoAppConstants.EDIT_DIALOG_FRAGMENT_TITLE;
import static com.example.skarwa.todoapp.utils.ToDoAppConstants.ITEM;
import static com.example.skarwa.todoapp.utils.ToDoAppConstants.ITEM_ID;
import static com.example.skarwa.todoapp.utils.ToDoAppConstants.ITEM_POSITION;
import static com.example.skarwa.todoapp.utils.ToDoAppConstants.OK_BUTTON_VALUE;


/**
 * This is the Edit Dialog Fragment for editing existing items in the To Do List
 * Created by skarwa on 8/16/17.
 */

public class ToDoItemEditDialogFragment extends DialogFragment {
    private EditText etItemName;

    public interface EditNameDialogListener {
        void onFinishEditDialog(String editedText,int id,int position);
    }

    public ToDoItemEditDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static ToDoItemEditDialogFragment newInstance(String title, String item, int id, int position) {
        ToDoItemEditDialogFragment frag = new ToDoItemEditDialogFragment();

        Bundle args = new Bundle();
        args.putString(EDIT_DIALOG_FRAGMENT_TITLE, title);
        args.putString(ITEM,item);
        args.putInt(ITEM_ID,id);
        args.putInt(ITEM_POSITION,position);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String name = getArguments().getString(ITEM);
        String title = getArguments().getString(EDIT_DIALOG_FRAGMENT_TITLE);

        LayoutInflater myLayout = LayoutInflater.from(this.getContext().getApplicationContext());
        View contentView = myLayout.inflate(R.layout.fragment_edit_item, null);

        etItemName = (EditText) contentView.findViewById(R.id.etEditItem);
        etItemName.setText(name);
        etItemName.requestFocus();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setView(contentView);
        alertDialogBuilder.setPositiveButton(OK_BUTTON_VALUE,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String editedItem = etItemName.getText().toString();
                EditNameDialogListener listener = (EditNameDialogListener) getActivity();
                listener.onFinishEditDialog(editedItem,getArguments().getInt(ITEM_ID),getArguments().getInt(ITEM_POSITION));

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        alertDialogBuilder.setNegativeButton(CANCEL_BUTTON_VALUE, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        return alertDialogBuilder.create();
    }
}
