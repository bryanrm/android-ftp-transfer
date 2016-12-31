package com.bryanrm.ftptransfer.ftp;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

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

    public boolean connect(String host, String username, String password) {
        try {
            ftpClient.connect(host);
            return login(username, password);
        } catch (Exception e) { return false; }
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
        } catch (Exception e) { return false; }
    }
}
