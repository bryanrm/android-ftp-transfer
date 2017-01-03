package com.bryanrm.ftptransfer.network;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.bryanrm.ftptransfer.R;
import com.bryanrm.ftptransfer.ftp.WrapFTP;

import java.io.InputStream;

/**
 * Created by Bryan R Martinez on 1/3/2017.
 */
public class Upload extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private String fileName;
    private InputStream inputStream;

    public Upload(Context context, String fileName, InputStream inputStream) {
        this.context = context;
        this.fileName = fileName;
        this.inputStream = inputStream;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        publishProgress();
        return WrapFTP.getInstance().uploadFile(fileName, inputStream);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        Toast.makeText(context,
                context.getString(R.string.message_file_ul_start), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            Toast.makeText(context,
                    context.getString(R.string.message_file_uploaded),
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context,
                    context.getString(R.string.message_file_not_uploaded),
                    Toast.LENGTH_LONG).show();
        }
    }
}
