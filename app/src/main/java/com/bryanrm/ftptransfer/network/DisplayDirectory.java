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
 * Created by Bryan R Martinez on 1/2/2017.
 */
public class DisplayDirectory extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private ListView listView;
    private WrapFTP wrapFTP;
    private ArrayList<String> listDirs;

    public DisplayDirectory(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
        this.wrapFTP = WrapFTP.getInstance();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        String[] list = wrapFTP.listDirectories();
        if (list != null) {
            listDirs = new ArrayList<>(Arrays.asList(list));
            listDirs.add(0, "..");
            return true;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            ArrayAdapter<String> arrayAdapter
                    = new ArrayAdapter<>(context.getApplicationContext(),
                    R.layout.modified_textview, listDirs);
            listView.setAdapter(arrayAdapter);
        } else {
            Toast.makeText(context.getApplicationContext(),
                    context.getString(R.string.message_no_dirs_found), Toast.LENGTH_LONG).show();
        }
    }
}
