package com.example.writingchallenge.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.writingchallenge.R;
import com.example.writingchallenge.entities.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_NOTE = 1;
    private static final int VIEW_TYPE_EMPTY = 2;

    private List<Note> notes;

    public NotesAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_NOTE) {
            View noteView = inflater.inflate(R.layout.item_container_note, parent, false);
            return new NoteViewHolder(noteView);
        } else {
            View emptyView = inflater.inflate(R.layout.item_empty_view, parent, false);
            return new EmptyViewHolder(emptyView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NoteViewHolder) {
            NoteViewHolder noteViewHolder = (NoteViewHolder) holder;
            noteViewHolder.setNoteData(notes.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (notes.isEmpty()) {
            return 1; // Return 1 for the empty view
        } else {
            return notes.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (notes.isEmpty()) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_NOTE;
        }
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private TextView textSubtitle;
        private TextView textDateTime;

        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textSubtitle = itemView.findViewById(R.id.textSubtitle);
            textDateTime = itemView.findViewById(R.id.textDateTime);
        }

        void setNoteData(Note note) {
            textTitle.setText(note.getTitle());
            if (note.getSubtitle().trim().isEmpty()) {
                textSubtitle.setVisibility(View.GONE);
            } else {
                textSubtitle.setVisibility(View.VISIBLE);
                textSubtitle.setText(note.getSubtitle());
            }
            textDateTime.setText(note.getDateTime());
        }
    }

    private static class EmptyViewHolder extends RecyclerView.ViewHolder {
        EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}