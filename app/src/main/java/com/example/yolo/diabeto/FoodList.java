package com.example.yolo.diabeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class FoodList extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        lv = (ListView) findViewById(R.id.list);
        String[] fooditem = new String[] {""};
        Integer[] givalue = new Integer[] {""};
        ArrayList<String> arrayList;
        arrayList = new ArrayList<>();
        for(int i=0; i<values.length; i++)
        {
            arrayList.add(values[i]);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String select = (String) adapterView.getItemAtPosition(i);

            }
        });

    }
}
