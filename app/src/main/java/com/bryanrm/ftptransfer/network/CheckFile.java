package com.bryanrm.ftptransfer.network;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bryanrm.ftptransfer.Constants;
import com.bryanrm.ftptransfer.R;
import com.bryanrm.ftptransfer.ftp.WrapFTP;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Bryan R Martinez on 12/31/2016.
 */
public class CheckFile extends AsyncTask<Integer, Void, Integer> {
    private Context context;
    private ListView listView;
    private Button downloadButton;
    private WrapFTP wrapFTP;
    private ArrayList<String> listFiles;

    public CheckFile(Context context, ListView listView, Button downloadButton) {
        this.context = context;
        this.listView = listView;
        this.downloadButton = downloadButton;
        this.wrapFTP = WrapFTP.getInstance();

        downloadButton.setEnabled(false);
    }

    @Override
    protected Integer doInBackground(Integer... position) {
        Integer pos = position[0]-1;
        if (pos == -1) {
            wrapFTP.stepBackDir();
            wrapFTP.resetSelectedFile();
            String[] list = wrapFTP.listNames();
            if (list != null) {
                listFiles = new ArrayList<>(Arrays.asList(list));
                listFiles.add(0, "..");
                return Constants.SELECTED_DIR;
            } else {
                listFiles = new ArrayList<>();
                listFiles.add("..");
                return Constants.SELECTED_DIR;
            }
        } else {
            String selectedFile = wrapFTP.getFileAtPosition(pos);
            if (wrapFTP.isDirectory(pos)) {
                wrapFTP.updateDir(selectedFile);
                wrapFTP.resetSelectedFile();
                String[] list = wrapFTP.listNames();
                if (list != null) {
                    listFiles = new ArrayList<>(Arrays.asList(list));
                    listFiles.add(0, "..");
                    return Constants.SELECTED_DIR;
                } else {
                    listFiles = new ArrayList<>();
                    listFiles.add("..");
                    return Constants.SELECTED_DIR;
                }
            } else if (wrapFTP.isFile(pos)) {
                wrapFTP.setSelectedFile(pos);
                return Constants.SELECTED_FILE;
            }
            else {
                wrapFTP.resetSelectedFile();
                return Constants.SELECTED_UNKNOWN;
            }
        }
    }

    @Override
    protected void onPostExecute(Integer result) {
        switch (result) {
            case Constants.SELECTED_DIR:
                downloadButton.setEnabled(false);
                ArrayAdapter<String> arrayAdapter =
                        new ArrayAdapter<>(context.getApplicationContext(),
                        R.layout.modified_textview, listFiles);
                listView.setAdapter(arrayAdapter);
                break;
            case Constants.SELECTED_UNKNOWN:
                downloadButton.setEnabled(false);
                Toast.makeText(context.getApplicationContext(),
                       context.getString(R.string.message_invalid_file), Toast.LENGTH_SHORT).show();
                break;
            case Constants.SELECTED_FILE:
                downloadButton.setEnabled(true);
                break;
            default:
                break;
        }
    }
}
