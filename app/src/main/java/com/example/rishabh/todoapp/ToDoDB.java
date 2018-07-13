package com.example.rishabh.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ToDoDB extends SQLiteOpenHelper {

    public ToDoDB(Context context) {
        super(context, "ToDoDB", null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

                String query = "CREATE TABLE todo(id INTEGER PRIMARY KEY,"+
                "title TEXT NOT NULL,"+
                        "description TEXT NOT NULL ,"+
                "completed INTEGER NOT NULL"+
                ");";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public long insertTask(Task task)
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put("id",task.getId());
        contentValues.put("title",task.getTitle());
        contentValues.put("description",task.getDescription());
        contentValues.put("completed",task.getCompleted());

        long position = getWritableDatabase().insert("todo",
                null,contentValues);

        return position;
    }


    public void removeTask(String[] position)
    {

         getWritableDatabase().delete("todo","id = ?",position);

    }

    public void updateTask(int value)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("completed",value);
        getWritableDatabase().update("todo",contentValues,"completed =?",null);
    }

    public ArrayList<Task> getAllTask()
    {
        String[] projection = new String[]{"id","title","description","completed"};

        ArrayList<Task> tasks = new ArrayList<>();

        Cursor c = getReadableDatabase().query("todo",projection,null,
                null,null,null,null);

        while (c.moveToNext())
        {
            Long id = c.getLong(c.getColumnIndex("id"));
            String title = c.getString(c.getColumnIndex("title"));
            String description = c.getString(c.getColumnIndex("description"));
            int completed = c.getInt(c.getColumnIndex("completed"));

            Task task = new Task(id,title,description,completed);
            tasks.add(task);
        }
        return tasks;
    }




}
