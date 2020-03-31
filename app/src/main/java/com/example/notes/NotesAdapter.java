package com.example.notes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    public static class NotesViewHolder extends RecyclerView.ViewHolder {
        LinearLayout containerView;
        TextView textView;

        NotesViewHolder(View view){
            super(view);
            containerView = view.findViewById(R.id.note_row);
            textView = view.findViewById(R.id.note_text_view);
            Note note = (Note) containerView.getTag();
            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Note note = (Note) containerView.getTag();
                    Intent intent = new Intent(v.getContext(), NoteActivity.class);
                    intent.putExtra("id", note.id);
                    intent.putExtra("contents", note.contents);
                    v.getContext().startActivity(intent);
                }
            });

        }

    }

    private List<Note> notesList = new ArrayList<>();


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_row, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note currentNote = notesList.get(position);
        holder.textView.setText(currentNote.contents);
        holder.containerView.setTag(currentNote);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public void reload(){
        notesList = MainActivity.notesDatabase.noteDao().getAllNotes();
        notifyDataSetChanged();
    }

    
}
