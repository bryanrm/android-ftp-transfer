package com.bryanrm.ftptransfer.ftp;

import org.apache.commons.net.ftp.FTPClient;

/**
 * Created by Bryan R Martinez on 12/30/2016.
 */
public class FTP {
    private FTPClient ftpClient;

    public FTP(int timeout) {
        ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(timeout);
    }

    public boolean connect(String host, String username, String password) {
        try {
            ftpClient.connect(host);
            return login(username, password);
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean connect(String host, Integer port, String username, String password) {
        try {
            ftpClient.connect(host, port);
            return login(username, password);
        } catch (Exception e) { return false; }
    }

    private boolean login(String username, String password) {
        try {
            return ftpClient.login(username, password);
        } catch (Exception e) { e.printStackTrace(); return false; }
    }
}
