package com.example.skarwa.todoapp.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 *
 * Database of the To Do App
 * Created by skarwa on 8/15/17.
 */
@Database(name=ToDoAppDatabase.NAME,version = ToDoAppDatabase.VERSION)
public class ToDoAppDatabase {
    public static final String NAME = "ToDoAppDatabase";

    public static final int VERSION = 1;
}
