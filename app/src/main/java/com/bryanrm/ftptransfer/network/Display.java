package com.bryanrm.ftptransfer.network;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bryanrm.ftptransfer.R;
import com.bryanrm.ftptransfer.ftp.WrapFTP;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Bryan R Martinez on 12/31/2016.
 */
public class Display extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private WrapFTP wrapFTP;
    private ListView listView;
    private ArrayList<String> listFiles;

    public Display(Context context, ListView listView) {
        this.context = context;
        this.wrapFTP = WrapFTP.getInstance();
        this.listView = listView;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        String[] list = wrapFTP.listNames();
        if (list != null) {
            listFiles = new ArrayList<>(Arrays.asList(list));
            listFiles.add(0, "..");
            return true;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            ArrayAdapter<String> arrayAdapter
                    = new ArrayAdapter<>(context.getApplicationContext(),
                    R.layout.modified_textview, listFiles);
            listView.setAdapter(arrayAdapter);
        } else {
            Toast.makeText(context.getApplicationContext(),
                    context.getString(R.string.message_no_files_found), Toast.LENGTH_LONG).show();
        }
    }
}
