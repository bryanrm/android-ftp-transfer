package com.bryanrm.ftptransfer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bryanrm.ftptransfer.ftp.FTP;

public class MainActivity extends AppCompatActivity {
    private FTP ftp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ftp = new FTP(Constants.FTP_TIMEOUT*Constants.S_MULTIPLIER);
    }

    public void connectPushed(View view) {
        
    }

}
