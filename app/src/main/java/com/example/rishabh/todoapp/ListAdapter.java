package com.example.rishabh.todoapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

ArrayList<Task> taskItems;
Context ctx;

    public ListAdapter(ArrayList<Task> taskItems, Context ctx) {
        this.taskItems = taskItems;
        this.ctx = ctx;
    }

//   final AlertDialog alertDialog = new AlertDialog.Builder(ctx).setTitle("DELETE").setCancelable(false)
//            .setPositiveButton("Conform", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    ToDoDB toDoDB = new ToDoDB(ctx);
//                    String[] place =new String[]{""+task.getId()};
//                    toDoDB.removeTask(place);
//                    Log.e("TAG", "onLongClick: REMOVED REMOVED REMOVED REMOVED REMOVED REMOVED" );
//
//                }
//            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(ctx, "Delete Cancled", Toast.LENGTH_SHORT).show();
//                }
//            }).setMessage("Are you sure to Delete ?").create();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(ctx).inflate(R.layout.item_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Task task = taskItems.get(position);
        final int completed = task.getCompleted();
        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
     holder.title.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(completed==0)
             {
                 holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

             }


         }
     });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

//            final AlertDialog alertDialog = new AlertDialog.Builder(ctx).setTitle("DELETE").setCancelable(false)
//                    .setPositiveButton("Conform", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            ToDoDB toDoDB = new ToDoDB(ctx);
//                            String[] place =new String[]{""+task.getId()};
//                            toDoDB.removeTask(place);
//                            Log.e("TAG", "onLongClick: REMOVED REMOVED REMOVED REMOVED REMOVED REMOVED" );
//
//                        }
//                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(ctx, "Delete Cancled", Toast.LENGTH_SHORT).show();
//                        }
//                    }).setMessage("Are you sure to Delete ?").create();

            @Override

            public boolean onLongClick(View v) {

                Log.e("TAG", "onLongClick:clicked clicked clicked clicked ");

                ToDoDB toDoDB = new ToDoDB(ctx);
                            String[] place =new String[]{""+task.getId()};
                            toDoDB.removeTask(place);
                            Log.e("TAG", "onLongClick: REMOVED REMOVED REMOVED REMOVED REMOVED REMOVED" );


//              alertDialog.show();


                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return taskItems.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView title;
        TextView description;


        public ViewHolder(View itemView) {

            super(itemView);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);

        }
    }

}



