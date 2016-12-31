package com.bryanrm.ftptransfer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.bryanrm.ftptransfer.network.Display;

public class DownloadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        ListView listView = (ListView) findViewById(R.id.list_files);
        new Display(getApplicationContext(), listView).execute();
    }
}
