package com.example.preetikataly.simpletodoenh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    ArrayList<String> options;
    ArrayAdapter<String> optionsAdapter;
    ListView defItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.preetikataly.simpletodoenh.R.layout.activity_main);
        defItems = (ListView) findViewById(R.id.defItems);
        options = new ArrayList<String>();
        optionsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options);
        defItems.setAdapter(optionsAdapter);
        options.add("Create a new list");
        options.add("Open an existing list");

        setupOptionViewListener();
    }

    private void setupOptionViewListener() {
        defItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    Intent i = new Intent(MainActivity.this, ListDetails.class);
                    String selectedOption = "1";
                    i.putExtra(selectedOption, "1");
                    startActivity(i);
                }
                else {
                    Intent j = new Intent(MainActivity.this, FileList.class);
                    startActivity(j);
                }

                optionsAdapter.notifyDataSetChanged();

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.example.preetikataly.simpletodoenh.R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == com.example.preetikataly.simpletodoenh.R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}