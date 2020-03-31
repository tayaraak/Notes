package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {

    EditText noteEditText;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteEditText = findViewById(R.id.note_edit_text);
        String contents = getIntent().getStringExtra("contents");
        noteEditText.setText(contents);
        id = getIntent().getIntExtra("id", 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.notesDatabase.noteDao().save(noteEditText.getText().toString(), id);
    }
}
