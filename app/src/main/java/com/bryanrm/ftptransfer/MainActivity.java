package com.bryanrm.ftptransfer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.bryanrm.ftptransfer.ftp.WrapFTP;
import com.bryanrm.ftptransfer.network.Connect;

public class MainActivity extends AppCompatActivity {
    private WrapFTP wrapFtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wrapFtp = new WrapFTP(Constants.FTP_TIMEOUT * Constants.S_MULTIPLIER);
    }

    public void connectPushed(View view) {
        String host = ((EditText) findViewById(R.id.field_host)).getText().toString();
        String port = ((EditText) findViewById(R.id.field_port)).getText().toString();
        String username = ((EditText) findViewById(R.id.field_username)).getText().toString();
        String password = ((EditText) findViewById(R.id.field_password)).getText().toString();
        new Connect(getApplicationContext(), wrapFtp).execute(host, port, username, password);
    }

}
