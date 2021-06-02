package com.example.editlistdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        EditAdapter adapter = new EditAdapter(new ArrayList<>());
        recyclerview.setAdapter(adapter);
        adapter.addData(new NumberBean());
        adapter.notifyDataSetChanged();
        adapter.addChildClickViewIds(R.id.add, R.id.sub);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (view.getId() == R.id.add) {
                    adapter.addData(new NumberBean());
                    adapter.notifyDataSetChanged();

                    LogUtils.d("date-->  " +  "  position  -->" + position);
                } else if (view.getId() == R.id.sub) {
                    NumberBean numberBean = (NumberBean) adapter.getItem(position);
                    adapter.removeAt(position);
//                    adapter.getData().remove(position);
                    LogUtils.d("delete number -->  " + numberBean.getNumber() + "  position  -->" + position);
                }

            }
        });
    }
}