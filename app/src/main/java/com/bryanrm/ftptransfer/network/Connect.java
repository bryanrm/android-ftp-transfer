package com.bryanrm.ftptransfer.network;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.bryanrm.ftptransfer.Constants;
import com.bryanrm.ftptransfer.R;
import com.bryanrm.ftptransfer.ftp.WrapFTP;

/**
 * Created by Bryan R Martinez on 12/30/2016.
 */
public class Connect extends AsyncTask<String, Void, Integer> {
    private Context context;
    private WrapFTP wrapFtp;

    public Connect(Context context, WrapFTP wrapFtp) {
        this.context = context;
        this.wrapFtp = wrapFtp;
    }

    @Override
    protected Integer doInBackground(String... params) {
        if (wrapFtp.connect(params[0], params[1], params[2], params[3]))
            return Constants.CONNECTION_SUCCESS;
        else return Constants.CONNECTION_FAIL;
    }

    @Override
    protected void onPostExecute(Integer result) {
        switch (result) {
            case Constants.CONNECTION_SUCCESS:
                Toast.makeText(context.getApplicationContext(),
                        context.getString(R.string.toast_success_connect),Toast.LENGTH_LONG).show();
                wrapFtp.disconnect();
                break;
            case Constants.CONNECTION_FAIL:
                Toast.makeText(context.getApplicationContext(),
                        context.getString(R.string.toast_fail_connect),Toast.LENGTH_LONG).show();
                break;
        }
    }


}
