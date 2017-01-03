package com.bryanrm.ftptransfer.network;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bryanrm.ftptransfer.R;
import com.bryanrm.ftptransfer.ftp.WrapFTP;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Bryan R Martinez on 1/3/2017.
 */
public class CheckDirectory extends AsyncTask<String, Void, Void> {
    private Context context;
    private ListView listView;
    private ArrayList<String> listDirs;

    public CheckDirectory(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected Void doInBackground(String... params) {
        WrapFTP wrapFTP = WrapFTP.getInstance();
        if (params[0].equals("..")) { wrapFTP.stepBackDir(); }
        else { wrapFTP.updateDir(params[0]); }
        listDirs = new ArrayList<>(Arrays.asList(wrapFTP.listDirectories()));
        listDirs.add(0, "..");
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                context.getApplicationContext(), R.layout.modified_textview, listDirs);
        listView.setAdapter(arrayAdapter);
    }
}
