package com.example.bki;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);
        populateListView();

    }

    private void populateListView() {
        //GİRİLEN VERİLERİN LİSTELENMESİ AŞAMASINDA HANGİ SIRALAMA VE DÜZEN İLE
        //GÖSTERİLECEĞİ BURADA YAPILIR.GET İLE DATABASE'DEN VERİ ÇEKİLİR.
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(1) + "   /    " +
                    data.getString(2) + "        |" +
                    data.getString(3) +
                    data.getString(4) );
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
    }


}