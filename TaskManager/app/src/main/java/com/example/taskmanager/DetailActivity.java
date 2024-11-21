package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        EditText nameEdit = findViewById(R.id.nameDetailEditText);
        EditText descEdit = findViewById(R.id.descDetailEditText);

        Button button = findViewById(R.id.detailEditButton);

        button.setOnClickListener(v -> {
            Data data = new Data( String.valueOf(nameEdit.getText()), String.valueOf(descEdit.getText()));
            Intent resultIntent = new Intent();
            resultIntent.putExtra("action", "edit_task");
            resultIntent.putExtra("detail_edit", data);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

}
