package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public static NotesDatabase notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesDatabase = Room.databaseBuilder(getApplicationContext(), NotesDatabase.class, "notes")
                .allowMainThreadQueries()
                .build();


        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        notesAdapter = new NotesAdapter();
        FloatingActionButton addButton = findViewById(R.id.add_note_btn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesDatabase.noteDao().create();
                notesAdapter.reload();
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(notesAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        notesAdapter.reload();
    }
}
