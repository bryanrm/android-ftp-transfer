package com.bryanrm.ftptransfer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bryanrm.ftptransfer.network.CheckFile;
import com.bryanrm.ftptransfer.network.Display;

public class DownloadActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        final ListView listView = (ListView) findViewById(R.id.list_files);
        new Display(getApplicationContext(), listView).execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new CheckFile(getApplicationContext(), listView).execute(position);
            }
        });
    }
}
