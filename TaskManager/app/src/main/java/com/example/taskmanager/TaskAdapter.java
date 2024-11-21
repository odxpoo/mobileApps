package com.example.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Data> dataList;
    protected static Context context;

    public TaskAdapter(List<Data> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.nameTextView.setText(data.getName());
        holder.descTextView.setText(data.getDesc());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descTextView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.task_name);
            descTextView = itemView.findViewById(R.id.task_desc);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    if (context instanceof MainActivity) {
                        ((MainActivity) context).openDetailEdit(getAdapterPosition());
                    }
                }
            });
        }
    }

}
