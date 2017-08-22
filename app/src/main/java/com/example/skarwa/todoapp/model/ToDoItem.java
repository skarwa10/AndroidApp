package com.example.skarwa.todoapp.model;

import com.example.skarwa.todoapp.database.ToDoAppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Table of ToDoItem aka Model of Item Object
 * Created by skarwa on 8/15/17.
 */
@Table(database = ToDoAppDatabase.class)
public class ToDoItem extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    private String itemName;

    public void setId(int id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }
}
