package com.milesilac.androidsqlitepracticer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class DBEntryRecViewAdapter extends RecyclerView.Adapter<DBEntryRecViewAdapter.ViewHolder> {

    ArrayList<StringEntry> stringEntries = new ArrayList<>();
    Context context;

    RecyclerView dbList;


    public DBEntryRecViewAdapter(RecyclerView dbList, Context context) {
        this.dbList = dbList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.db_entry_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.entryDate.setText(stringEntries.get(position).getDate());
        holder.entryString.setText(stringEntries.get(position).getText());
        holder.btnDelete.setOnClickListener(view -> {
            DBHelper dbHelper = new DBHelper(context);
            dbHelper.deleteEntry(stringEntries.get(position));
            boolean success = DBEntryRecViewAdapter.this.setEntries(dbHelper.getAllTexts());
            dbList.setAdapter(DBEntryRecViewAdapter.this);
            dbList.setLayoutManager(new LinearLayoutManager(context));
            Toast.makeText(context,"Delete success= " + success, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return stringEntries.size();
    }

    public boolean setEntries(ArrayList<StringEntry> stringEntries) {
        this.stringEntries = stringEntries;
        notifyDataSetChanged();
        return stringEntries != null;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView entryDate, entryString;
        ImageButton btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                entryDate = itemView.findViewById(R.id.entryDate);
                entryString = itemView.findViewById(R.id.entryString);
                btnDelete = itemView.findViewById(R.id.btnDelete);
        }

    }
}
