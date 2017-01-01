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
public class CheckFile extends AsyncTask<Integer, Void, Boolean> {
    private Context context;
    private ListView listView;
    private WrapFTP wrapFTP;
    private ArrayList<String> listFiles;

    public CheckFile(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
        this.wrapFTP = WrapFTP.getInstance();
    }

    @Override
    protected Boolean doInBackground(Integer... position) {
        String selectedFile = wrapFTP.getFileAtPosition(position[0]);
        System.out.println(selectedFile);
        if (wrapFTP.isDirectory(position[0])) {
            wrapFTP.updateDir(selectedFile);
            String[] list = wrapFTP.listNames();
            if (list != null) {
                listFiles = new ArrayList<>(Arrays.asList(list));
                return true;
            } return false;
        } return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context.getApplicationContext(),
                    R.layout.modified_textview, listFiles);
            listView.setAdapter(arrayAdapter);
        } else {
            Toast.makeText(context.getApplicationContext(),
                    context.getString(R.string.message_no_files_found), Toast.LENGTH_LONG).show();
        }
    }
}
