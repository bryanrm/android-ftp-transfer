package com.bryanrm.ftptransfer.ftp;

import org.apache.commons.net.ftp.FTPClient;

/**
 * Created by Bryan R Martinez on 12/30/2016.
 */
public class FTP {
    private FTPClient ftpClient;

    public FTP() {
        ftpClient = new FTPClient();
    }

    public FTP(int timeout) {
        ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(timeout);
    }

    public void setTimeout(int timeout) {
        ftpClient.setConnectTimeout(timeout);
    }
}
