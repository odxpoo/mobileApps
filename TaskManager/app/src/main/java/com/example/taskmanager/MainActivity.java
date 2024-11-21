package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Data> list = new ArrayList<>();
    private TaskAdapter taskAdapter;
    private int detail_position; // position of what you clicked on the list so you can update it

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            // got this to work after whooping 35min 🥰🥰🥰🥰🥰🥰
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    switch(result.getData().getStringExtra("action")) {
                        case "add_task": {
                            Data data = result.getData().getParcelableExtra("add_task_activity");
                            list.add(data);
                        }
                        case "edit_task": {
                            Data data = result.getData().getParcelableExtra("detail_edit");
                            list.set(detail_position, data);
                        }
                    }
                    taskAdapter.notifyDataSetChanged();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton button = findViewById(R.id.button);
        RecyclerView recyclerView = findViewById(R.id.recycleViewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for(int i = 1; i <= 25; i++) {
            // how to speedrun data collection: 😎
            list.add(new Data(""+i, "That is the "+i+" task to do."));
        }

        taskAdapter = new TaskAdapter(list, this);
        recyclerView.setAdapter(taskAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

    }

    public void openDetailEdit(int position) {
        // works somehow 🙂🙂🙂🙂🙂🙂
        detail_position = position;
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        activityResultLauncher.launch(intent);
    }
}