package com.huyismeee.dailyemote;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huyismeee.dailyemote.database.Record;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class fragment_record extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Record> records;
    private RecyclerView recylerView;

    public fragment_record() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        recylerView = view.findViewById(R.id.recycler_view_Record);

        recylerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recylerView.setHasFixedSize(true);

        MyAdapter myAdapter = new MyAdapter(getContext(), records);
        recylerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    private void dataInitialize() {
        records = new ArrayList<Record>();
        records.add(new Record(1, R.drawable.angry, R.drawable.sun, "fuck", null, new Date(19920)));
        // điền dữ liệu database vô đây
    }
}