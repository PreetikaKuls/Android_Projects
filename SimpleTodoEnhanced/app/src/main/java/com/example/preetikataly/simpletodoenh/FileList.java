package com.example.preetikataly.simpletodoenh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.preetikataly.simpletodoenh.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import android.util.Log;

public class FileList extends Activity {
    ArrayList<String> files;
    ArrayAdapter<String> itemsAdapter;
    ListView fileItems;
    public static final String selectedFile = "File";
    public static final String TAG = "DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
        fileItems = (ListView) findViewById(R.id.fileItems);
        files = new ArrayList<String>();
        readItems();
        Log.v(TAG, "Reached here");
        // items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files);
        fileItems.setAdapter(itemsAdapter);
        setupFileViewListener();
    }

    private void setupFileViewListener() {
        fileItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Intent i = new Intent(FileList.this, ListDetails.class);
                    i.putExtra(selectedFile, itemsAdapter.getItem(position));
                    startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.file_list, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File[] paths;
        Log.v(TAG, "The filesDir is:" + filesDir.listFiles());
        //try {
            paths = filesDir.listFiles();

            for(File path:paths){
                if (path.getName() != null) {
                    String name = path.getName();
                    Log.v(TAG, "The path is:" + path.getName());
                    files.add(name);
                }
                Log.v(TAG, "Added to file:" + files);
            }
            Log.v(TAG, "Files contains:" + files);

       // } catch(Exception e) {
        //    files = new ArrayList<String>();
        //    Log.v(TAG, "Reached inside new");
        //}

    }
}
