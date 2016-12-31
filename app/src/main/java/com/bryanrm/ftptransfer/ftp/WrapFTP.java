package com.bryanrm.ftptransfer.ftp;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

/**
 * Created by Bryan R Martinez on 12/30/2016.
 */
public class WrapFTP {
    private static WrapFTP wrapFTP = null;
    private static FTPClient ftpClient;

    private WrapFTP() {
        ftpClient = new FTPClient();
    }

    public static WrapFTP getInstance() {
        if (wrapFTP == null) { wrapFTP = new WrapFTP(); }
        return wrapFTP;
    }

    public void setTimeout(int timeout) {
        ftpClient.setConnectTimeout(timeout);
    }

    public boolean connect(String host, String port, String username, String password) {
        if (port.equals("") || port.equals("21")) {
            try {
                ftpClient.connect(host);
                return login(username, password);
            } catch (Exception e) { return false; }
        } else {
            Integer portNumber = Integer.parseInt(port);
            try {
                ftpClient.connect(host, portNumber);
                return login(username, password);
            } catch (Exception e) { return false; }
        }
    }

    private boolean login(String username, String password) {
        try {
            return ftpClient.login(username, password);
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public String[] listFiles() {
        if (ftpClient.isConnected()) {
            try { return ftpClient.listNames();
            } catch (IOException e) { return null; }
        } else return null;
    }

    public void downloadFile() {
        if (ftpClient.isConnected()) {

        }
    }

    public void disconnect() {
        try { ftpClient.disconnect();
        } catch (Exception e) { }
    }
}
