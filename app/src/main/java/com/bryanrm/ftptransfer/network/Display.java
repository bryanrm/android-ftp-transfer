package com.bryanrm.ftptransfer.network;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bryanrm.ftptransfer.R;
import com.bryanrm.ftptransfer.ftp.WrapFTP;

/**
 * Created by Bryan R Martinez on 12/31/2016.
 */
public class Display extends AsyncTask<Void, Void, Boolean> {
    private Context context;
    private WrapFTP wrapFTP;
    private ListView listView;
    private String[] listFiles;

    public Display(Context context, ListView listView) {
        this.context = context;
        this.wrapFTP = WrapFTP.getInstance();
        this.listView = listView;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        listFiles = wrapFTP.listFiles();
        return listFiles != null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            ArrayAdapter<String> arrayAdapter
                    = new ArrayAdapter<>(context.getApplicationContext(),
                    android.R.layout.simple_list_item_1, listFiles);
            listView.setAdapter(arrayAdapter);
        } else {
            Toast.makeText(context.getApplicationContext(),
                    context.getString(R.string.message_no_files_found), Toast.LENGTH_LONG).show();
        }
    }
}
