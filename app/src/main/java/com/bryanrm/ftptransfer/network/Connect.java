package com.bryanrm.ftptransfer.network;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.bryanrm.ftptransfer.ftp.FTP;

import java.util.Arrays;

/**
 * Created by Bryan R Martinez on 12/30/2016.
 */
public class Connect extends AsyncTask<String, String, Void> {
    private Context context;
    private FTP ftp;

    public Connect(Context context, int timeout) {
        this.context = context;
        this.ftp = new FTP(timeout);
    }

    @Override
    protected Void doInBackground(String... params) {
        if (ftp.connect(params[0], params[1], params[2], params[3])) {
            publishProgress("Success");
            ftp.disconnect();
        }
        else
            publishProgress("Failure");
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Toast.makeText(context.getApplicationContext(), Arrays.toString(values), Toast.LENGTH_LONG).show();
    }
}
