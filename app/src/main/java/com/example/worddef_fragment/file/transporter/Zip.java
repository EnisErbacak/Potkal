package com.example.worddef_fragment.file.transporter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip implements FileTransporter{

    private String FILE_NAME="Potkal.zip";
    @Override
    public boolean transfer(String src, String trgt) {

        boolean result;
        BufferedInputStream bis = null;
        BufferedOutputStream bos=null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        FileInputStream fi=null;
        File[] files=((File[])new File(src).listFiles());

        //compressFiles();
        try {
            fos = new FileOutputStream(trgt +File.separator + FILE_NAME);
            bos=new BufferedOutputStream(fos);
            zos = new ZipOutputStream(bos);

            byte data[] = new byte[1024];

            for (int i = 0; i < files.length; i++) {
                fi = new FileInputStream(files[i]);
                bis = new BufferedInputStream(fi, 1024);
                String name=files[i].getName();
                ZipEntry entry = new ZipEntry(files[i].getName());
                //zos.setLevel(ZipOutputStream.DEFLATED);
                //zos.setMethod();
                zos.setLevel(9);
                zos.putNextEntry(entry);
                int count;
                while ((count = bis.read(data, 0, 1024)) != -1) {
                    zos.write(data, 0, count);
                }

            }
            result=true;
        }
        catch (Exception e){e.printStackTrace(); result=false;}
        finally {
            try {
                zos.closeEntry();
                zos.close();
                bis.close();
                bos.close();
                fos.close();
                fi.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return  result;
    }
}
