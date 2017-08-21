package com.example.skarwa.todoapp;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by skarwa on 8/15/17.
 */
@Database(name=ToDoAppDatabase.NAME,version = ToDoAppDatabase.VERSION)
public class ToDoAppDatabase {
    public static final String NAME = "ToDoAppDatabase";

    public static final int VERSION = 1;
}
