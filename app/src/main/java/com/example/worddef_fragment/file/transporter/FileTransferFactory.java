package com.example.worddef_fragment.file.transporter;

public class FileTransferFactory {

    public FileTransporter create(String type) {
        FileTransporter fileTransporter;

        switch (type.toLowerCase()) {
            case "move":
                fileTransporter= new Move();
                break;
            case "zip":
                fileTransporter=new Zip();
                break;
            case "unzip":
                fileTransporter=new Unzip();
                break;
            case "copy":
                fileTransporter=new Copy();
                break;
            default:
                fileTransporter=null;
        }
        return fileTransporter;
    }
}
