package com.example.dynamicfields;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class KVRecyclerAdapter extends RecyclerView.Adapter<KVRecyclerAdapter.ViewHolder> {

    private static final String TAG = "NotesRecyclerAdapter";

    private ArrayList<KeyValue> mKeyValues = new ArrayList<>();
    private OnKVListener mOnKVListener;

    public KVRecyclerAdapter(ArrayList<KeyValue> KeyValues, OnKVListener onKVListener) {
        this.mKeyValues = KeyValues;
        this.mOnKVListener = onKVListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hashmap_fields, viewGroup, false);
        return new ViewHolder(view, mOnKVListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.key.setText(mKeyValues.get(i).getKey());
        viewHolder.value.setText(mKeyValues.get(i).getValue());
    }

    @Override
    public int getItemCount() {
        return mKeyValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        EditText key, value;
        Button deleteBtn;

        public ViewHolder(@NonNull View itemView, OnKVListener onKVListener) {
            super(itemView);
            key = itemView.findViewById(R.id.key);
            value = itemView.findViewById(R.id.value);
            deleteBtn = itemView.findViewById(R.id.delete_button);
            deleteBtn.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Log.d(TAG, "onClick: " + getAdapterPosition());
                    mOnKVListener.onDeleteClick(getAdapterPosition());
                }
            });
            key.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                public void afterTextChanged(Editable editable) {}
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                   mKeyValues.get(getAdapterPosition()).setKey(charSequence.toString());
                }
            });
            value.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                public void afterTextChanged(Editable editable) {}
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mKeyValues.get(getAdapterPosition()).setValue(charSequence.toString());
                }
            });
        }
    }

    public interface OnKVListener{
        void onDeleteClick(int position);
    }

}
