package com.bryanrm.ftptransfer.network;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import com.bryanrm.ftptransfer.R;
import com.bryanrm.ftptransfer.ftp.WrapFTP;

/**
 * Created by Bryan R Martinez on 1/1/2017.
 */
public class Download extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private WrapFTP wrapFTP;
    private String saveLocation;

    public Download(Context context) {
        this.context = context.getApplicationContext();
        this.wrapFTP = WrapFTP.getInstance();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        if (!wrapFTP.getSelectedFileName().equals("")) {
            saveLocation = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS) + "/" + wrapFTP.getSelectedFileName();
            publishProgress();
            return wrapFTP.downloadFile(saveLocation);
        }
        return false;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        Toast.makeText(context,
                context.getString(R.string.message_file_dl_start), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            String output = context.getString(R.string.message_file_saved) + " " + saveLocation;
            Toast.makeText(context, output, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context,
                    context.getString(R.string.message_file_not_saved), Toast.LENGTH_LONG).show();
        }
    }
}
