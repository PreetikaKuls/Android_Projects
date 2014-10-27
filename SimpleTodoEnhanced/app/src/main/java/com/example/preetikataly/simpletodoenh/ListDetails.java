package com.example.preetikataly.simpletodoenh;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class ListDetails extends Activity {
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;
    TextView Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.preetikataly.simpletodoenh.R.layout.activity_list_details);
        lvItems = (ListView) findViewById(R.id.lvtems);
        Title = (TextView) findViewById(R.id.listTitle);
        readItems();
        // items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                return true;
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

    public void onAddItem(View view) {
        EditText etNewItem = (EditText) findViewById(com.example.preetikataly.simpletodoenh.R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        //writeItems();
    }

    public void onSave(View view) {
        EditText listTitle = (EditText) findViewById(com.example.preetikataly.simpletodoenh.R.id.listTitle);
        //String itemText = listTitle.getText().toString();
       // itemsAdapter.add(itemText);
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        if (getIntent().hasExtra("1")){
            items = new ArrayList<String>();
            return;
        }
        String filename = getIntent().getStringExtra(FileList.selectedFile);

        File todoFile = new File(filesDir, filename);

        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
            Title.setText(filename);
        } catch(IOException e) {
            items = new ArrayList<String>();
        }

    }

    private void writeItems() {
        File filesDir = getFilesDir();
        String filename = Title.getText().toString();
        filename = filename.concat(".txt");
        File todoFile = new File(filesDir, filename);
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}