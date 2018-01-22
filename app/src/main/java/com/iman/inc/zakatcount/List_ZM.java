package com.iman.inc.zakatcount;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.iman.inc.zakatcount.adapter.ZM_Adapter;
import com.iman.inc.zakatcount.model.Mdl_ZM;

import java.util.ArrayList;

public class List_ZM extends AppCompatActivity {

    FloatingActionButton fabAdd;
    RecyclerView mRecyclerView;

    Db_helper db_helper;
    ZM_Adapter zm_adapter;
    ArrayList<Mdl_ZM> list;
    public static List_ZM activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__zf);
        activity=this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fabAdd = findViewById(R.id.floatingAdd);
        mRecyclerView = findViewById(R.id.recycleView);
        db_helper = new Db_helper(getApplicationContext());
        list = db_helper.getAllDataMal();
        zm_adapter = new ZM_Adapter(List_ZM.this, list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(zm_adapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(List_ZM.this, Count_ZM.class));
                finish();
            }
        });

    }


    public void deleteZmal(Mdl_ZM mdl_zm){
        db_helper.deleteDataMal(mdl_zm);
        list.remove(mdl_zm);
        zm_adapter.notifyDataSetChanged();
        Snackbar.make(mRecyclerView, "Berhasil menghapus data", Snackbar.LENGTH_SHORT).show();


    }
}
