package com.bryanrm.ftptransfer.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Bryan R Martinez on 12/30/2016.
 */
public class WrapFTP {
    private static WrapFTP wrapFTP = null;
    private static FTPClient ftpClient;
    private FTPFile[] files;

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

    public void stepBackDir() {
        try {
            ftpClient.changeToParentDirectory();
        } catch (IOException e) { }
    }

    public void updateDir(String dir) {
        try {
            ftpClient.changeWorkingDirectory(dir);
        } catch (IOException e) { }
    }

    public String[] listNames() {
        if (ftpClient.isConnected()) {
            try {
                listFiles();
                return ftpClient.listNames();
            } catch (IOException e) { return null; }
        } else return null;
    }

    private void listFiles() {
        if (ftpClient.isConnected()) {
            try {
                files = ftpClient.listFiles();
            } catch (IOException e) { }
        }
    }

    public String getFileAtPosition(int position) {
        return files[position].getName();
    }

    public boolean isFile(int position) {
        return files[position].isFile();
    }

    public boolean isDirectory(int position) {
        return files[position].isDirectory();
    }

    public boolean downloadFile(String remotePath, String destination) {
        if (ftpClient.isConnected()) {
            File file = new File(destination);
            File dir = file.getParentFile();
            if (!dir.exists()) dir.mkdir();
            OutputStream outputStream = null;
            try {
                outputStream = new BufferedOutputStream(new FileOutputStream(file));
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.retrieveFile(remotePath, outputStream);
            } catch (IOException e) { return false; }
            finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                        return true;
                    } catch (IOException e) { return false; }
                } else return false;
            }
        } else return false;
    }

    public void disconnect() {
        try { ftpClient.disconnect();
        } catch (Exception e) { }
    }
}
