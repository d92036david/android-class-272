package com.example.user.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    RadioGroup radioGroup;
    ListView listView;
    Spinner spinner;

    String drink = "Black Tea";

    List<Order> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        listView = (ListView)findViewById(R.id.listView);
        spinner = (Spinner)findViewById(R.id.spinner);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.blackTeaRadioButton)
                {
                    drink = "Black Tea";
                }
                else if (checkedId == R.id.greenTeaRadioButton)
                {
                    drink = "Green Tea";
                }
            }
        });

        setupListView();
        setupSpinner();
    }

    private void setupListView()
    {
//        String[] data = new String[]{"1","2","3","4","5","6","7","8"};

//        List<Map<String, String>> mapList = new ArrayList<>();
//
//        for(Order order : data)
//        {
//            Map<String, String> item = new HashMap<>();
//
//            item.put("note", order.note);
//            item.put("storeInfo", order.storeInfo);
//            item.put("drink", order.drink);
//
//            mapList.add(item);
//        }
//
//        String[] from = {"note", "storeInfo", "drink"};
//        int[] to = {R.id.noteTextView, R.id.storeInfoTextView, R.id.drinkTextView};
//
//        SimpleAdapter adapter = new SimpleAdapter(this, mapList, R.layout.listview_order_item, from, to);

        OrderAdapter adapter = new OrderAdapter(this, data);
        listView.setAdapter(adapter);
    }

    private void setupSpinner()
    {
        String[] storeInfo = getResources().getStringArray(R.array.storeInfo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, storeInfo);
        spinner.setAdapter(adapter);
    }

    public void click(View view)
    {
        String text = editText.getText().toString();
        String result = text + "  Order: " + drink;
        textView.setText(result);

        editText.setText("");

        Order order = new Order();

        order.note = text;
        order.drink = drink;
        order.storeInfo = (String)spinner.getSelectedItem();

        data.add(order);
        setupListView();
    }
}
