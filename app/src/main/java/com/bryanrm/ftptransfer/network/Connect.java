package com.bryanrm.ftptransfer.network;

import android.content.Context;
import android.os.AsyncTask;

import com.bryanrm.ftptransfer.ftp.FTP;

/**
 * Created by Bryan R Martinez on 12/30/2016.
 */
public class Connect extends AsyncTask<String, Void, Void> {
    private Context context;
    private FTP ftp;

    public Connect(Context context, int timeout) {
        this.context = context;
        this.ftp = new FTP(timeout);
    }

    @Override
    protected Void doInBackground(String... params) {

        return null;
    }
}
