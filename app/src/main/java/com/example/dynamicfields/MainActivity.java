package com.example.dynamicfields;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements KVRecyclerAdapter.OnKVListener {

    private static final String TAG = "NotesRecyclerAdapter";

    private RecyclerView mRecyclerView;
    private ArrayList<KeyValue> mKeyValues = new ArrayList<>();
    private KVRecyclerAdapter mKVRecyclerAdapter;
    public HashMap<String, String> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.hashMapRecycler);

        initRecyclerView();
        //insertFakeEntries();
    }
/*    private void insertFakeEntries(){
        for (int i=0; i<20; i++){
            KeyValue kv = new KeyValue();
            kv.setKey("key: "+i);
            kv.setValue("value: "+i);
            mKeyValues.add(kv);
        }
        mKVRecyclerAdapter.notifyDataSetChanged();
    }*/

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mKVRecyclerAdapter = new KVRecyclerAdapter(mKeyValues, this);
        mRecyclerView.setAdapter(mKVRecyclerAdapter);
    }

    @Override
    public void onDeleteClick(int position) {
        deleteKeyValue(mKeyValues.get(position));
    }

    public void onAdd(View v){
        addEntries();
    }

    public void onSubmit(View v){
        submitEntries();
    }

    private void deleteKeyValue(KeyValue kv) {
        mKeyValues.remove(kv);
        mKVRecyclerAdapter.notifyDataSetChanged();

        //mNoteRepository.deleteNoteTask(note);
    }

    public void addEntries() {
        KeyValue kv = new KeyValue();
        kv.setKey("key");
        kv.setValue("value");
        mKeyValues.add(kv);
        mKVRecyclerAdapter.notifyDataSetChanged();
    }

    public void submitEntries() {
        /*for (int i=0; i<mStrings.size(); i++){
            mKeyValues.get(i).getKey(mStrings.get(i));
        }*/
        for (int i=0; i<mKeyValues.size(); i++) {
            hashMap.put(mKeyValues.get(i).getKey(), mKeyValues.get(i).getValue());
        }
        Log.d(TAG, "submitEntries: "+"Hash Values: "+hashMap.toString());
        hashMap.clear();
    }
}

