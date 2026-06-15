package com.example.languaugetranslator;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.languaugetranslator.R;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView listViewHistory = findViewById(R.id.listViewHistory);
        Button buttonBack = findViewById(R.id.buttonBack);

        // Get the history list passed from MainActivity
        ArrayList<String> history = getIntent()
                .getStringArrayListExtra("history");

        if (history == null) {
            history = new ArrayList<>();
        }

        // Display history in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                history
        );
        listViewHistory.setAdapter(adapter);

        // Go back when button is clicked
        buttonBack.setOnClickListener(v -> finish());
    }
}