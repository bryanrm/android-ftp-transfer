package com.bryanrm.ftptransfer;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bryanrm.ftptransfer.network.CheckFile;
import com.bryanrm.ftptransfer.network.Display;
import com.bryanrm.ftptransfer.network.Download;

public class DownloadActivity extends AppCompatActivity {
    public static final int WRITE_EXTERNAL_STORAGE = 1;
    public final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        final ListView listView = (ListView) findViewById(R.id.list_files);
        final Button button = (Button) findViewById(R.id.button_download);

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            button.setEnabled(false);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                WRITE_EXTERNAL_STORAGE);
                    } else {
                        new Download(getApplicationContext()).execute();
                    }
                }
            });
            new Display(getApplicationContext(), listView).execute();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    new CheckFile(getApplicationContext(), listView, button).execute(position);
                }
            });
        } else {
            Toast.makeText(this,
                    getString(R.string.message_no_external_storage), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    new Download(getApplicationContext()).execute();
                } else {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.message_write_permission_denied), Toast.LENGTH_LONG)
                            .show();
                }
        }
    }
}
