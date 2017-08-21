package com.example.skarwa.todoapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by skarwa on 8/16/17.
 */

public class EditNameDialogFragment extends DialogFragment {
    private EditText etItemName;

    public interface EditNameDialogListener {
        void onFinishEditDialog(String editedText,int id,int position);
    }

    public EditNameDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static EditNameDialogFragment newInstance(String title,String item,int id,int position) {
        EditNameDialogFragment frag = new EditNameDialogFragment();

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("item",item);
        args.putInt("id",id);
        args.putInt("position",position);
        frag.setArguments(args);
        return frag;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String name = getArguments().getString("item");
        String title = getArguments().getString("title");

        LayoutInflater myLayout = LayoutInflater.from(this.getContext().getApplicationContext());
        View contentView = myLayout.inflate(R.layout.fragment_edit_name, null);

        etItemName = (EditText) contentView.findViewById(R.id.etEditItem);
        etItemName.setText(name);
        etItemName.requestFocus();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setView(contentView);
        alertDialogBuilder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String editedItem = etItemName.getText().toString();
                EditNameDialogListener listener = (EditNameDialogListener) getActivity();
                listener.onFinishEditDialog(editedItem,getArguments().getInt("id"),getArguments().getInt("position"));

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
