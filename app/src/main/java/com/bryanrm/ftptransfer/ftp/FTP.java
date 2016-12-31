package com.bryanrm.ftptransfer.ftp;

import com.bryanrm.ftptransfer.Constants;

import org.apache.commons.net.ftp.FTPClient;

/**
 * Created by Bryan R Martinez on 12/30/2016.
 */
public class FTP {
    private FTPClient ftpClient;

    public FTP() {
        ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(Constants.CONNECTION_TIMEOUT * 1000);
    }
}
