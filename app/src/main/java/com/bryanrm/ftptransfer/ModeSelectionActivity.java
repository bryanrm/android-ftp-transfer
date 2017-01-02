package com.bryanrm.ftptransfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bryanrm.ftptransfer.ftp.WrapFTP;

public class ModeSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selection);
    }

    public void downloadMode(View view) {
        Intent intent = new Intent(this, DownloadActivity.class);
        startActivity(intent);
    }

    public void uploadMode(View view) {
        Intent intent = new Intent(this, UploadActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        WrapFTP.getInstance().disconnect();
        super.onBackPressed();
    }
}
