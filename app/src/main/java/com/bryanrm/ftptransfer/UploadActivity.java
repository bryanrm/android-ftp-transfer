package com.bryanrm.ftptransfer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bryanrm.ftptransfer.network.DisplayDirectory;

public class UploadActivity extends AppCompatActivity {
    private static final int READ_EXTERNAL_STORAGE = 1;
    private static final int SELECT_FILE = 2;
    private ListView listView;
    private Button uploadButton;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        listView = (ListView) findViewById(R.id.list_dirs);
        uploadButton = (Button)findViewById(R.id.button_upload);

        uploadButton.setEnabled(false);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri != null) {
                    //TODO upload to server
                }
            }
        });
        new DisplayDirectory(getApplicationContext(), listView).execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //new CheckFile(getApplicationContext(), listView, button).execute(position);
            }
        });
    }

    public void selectFile(View view) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_EXTERNAL_STORAGE);
        } else { fileSelection(); }
    }

    private void fileSelection() {
        Intent fileChooser = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        fileChooser.addCategory(Intent.CATEGORY_OPENABLE);
        fileChooser.setType("*/*");
        startActivityForResult(fileChooser, READ_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fileSelection();
                } else {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.message_read_permission_denied), Toast.LENGTH_LONG)
                            .show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_FILE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                uri = data.getData();
                if (uploadButton.isEnabled()) { uploadButton.setEnabled(true); }
            } else {
                Toast.makeText(this,
                        getString(R.string.message_invalid_file), Toast.LENGTH_LONG).show();
            }
        }
    }
}
