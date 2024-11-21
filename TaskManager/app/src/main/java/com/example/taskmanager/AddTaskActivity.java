package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_task_activity);

        EditText nameEdit = findViewById(R.id.nameEditText);
        EditText descEdit = findViewById(R.id.descEditText);

        Button button = findViewById(R.id.addTaskButton);

        button.setOnClickListener(v -> {
            Data data = new Data( String.valueOf(nameEdit.getText()), String.valueOf(descEdit.getText()));
            Intent resultIntent = new Intent();

            resultIntent.putExtra("action", "add_task");
            resultIntent.putExtra("add_task_activity", data);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
