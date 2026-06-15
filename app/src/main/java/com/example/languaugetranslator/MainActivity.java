package com.example.languaugetranslator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerSource, spinnerTarget;
    EditText editTextInput;
    TextView textViewResult;
    Button buttonTranslate, buttonHistory;

    // Language display names and their ML Kit codes
    String[] languageNames = {"English", "French", "Spanish", "German",
            "Italian", "Portuguese", "Chinese", "Arabic", "Korean", "Hindi"};
    String[] languageCodes = {
            TranslateLanguage.ENGLISH,
            TranslateLanguage.FRENCH,
            TranslateLanguage.SPANISH,
            TranslateLanguage.GERMAN,
            TranslateLanguage.ITALIAN,
            TranslateLanguage.PORTUGUESE,
            TranslateLanguage.CHINESE,
            TranslateLanguage.ARABIC,
            TranslateLanguage.KOREAN,
            TranslateLanguage.HINDI
    };

    String sourceLanguageCode = TranslateLanguage.ENGLISH;
    String targetLanguageCode = TranslateLanguage.FRENCH;

    // History list to pass to HistoryActivity
    ArrayList<String> translationHistory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect UI elements
        spinnerSource = findViewById(R.id.spinnerSource);
        spinnerTarget = findViewById(R.id.spinnerTarget);
        editTextInput = findViewById(R.id.editTextInput);
        textViewResult = findViewById(R.id.textViewResult);
        buttonTranslate = findViewById(R.id.buttonTranslate);
        buttonHistory = findViewById(R.id.buttonHistory);

        // Set up language spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, languageNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSource.setAdapter(adapter);
        spinnerTarget.setAdapter(adapter);

        // Default target to French (index 1)
        spinnerTarget.setSelection(1);

        // Listen for source language selection
        spinnerSource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sourceLanguageCode = languageCodes[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Listen for target language selection
        spinnerTarget.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                targetLanguageCode = languageCodes[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Translate button click
        buttonTranslate.setOnClickListener(v -> {
            String inputText = editTextInput.getText().toString().trim();
            if (inputText.isEmpty()) {
                Toast.makeText(this, "Please enter text to translate", Toast.LENGTH_SHORT).show();
                return;
            }
            translateText(inputText);
        });

        // History button click
        buttonHistory.setOnClickListener(v -> {
            Intent intent = new Intent(this, HistoryActivity.class);
            intent.putStringArrayListExtra("history", translationHistory);
            startActivity(intent);
        });
    }

    private void translateText(String inputText) {
        textViewResult.setText("Translating...");

        TranslatorOptions options = new TranslatorOptions.Builder()
                .setSourceLanguage(sourceLanguageCode)
                .setTargetLanguage(targetLanguageCode)
                .build();

        Translator translator = Translation.getClient(options);

        // Download model if needed, then translate
        translator.downloadModelIfNeeded()
                .addOnSuccessListener(unused -> {
                    translator.translate(inputText)
                            .addOnSuccessListener(translatedText -> {
                                textViewResult.setText(translatedText);
                                // Save to history
                                String historyEntry = inputText + " → " + translatedText
                                        + " (" + sourceLanguageCode + " to " + targetLanguageCode + ")";
                                translationHistory.add(0, historyEntry);
                            })
                            .addOnFailureListener(e -> {
                                textViewResult.setText("Translation failed: " + e.getMessage());
                            });
                })
                .addOnFailureListener(e -> {
                    textViewResult.setText("Model download failed: " + e.getMessage());
                });
    }
}