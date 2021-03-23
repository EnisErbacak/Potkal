package com.example.worddef_fragment.file.transporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip implements FileTransporter{

    private final String FILE_NAME="Potkal.zip";
    @Override
    public boolean transfer(String src, String trgt) {
        boolean result;
        FileInputStream fin = null;
        ZipInputStream zin = null;
        FileOutputStream fout = null;
        try {
            fin = new FileInputStream(src + File.separator + FILE_NAME); // ZIP FILE LOCATION
            zin = new ZipInputStream(fin);   //
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {
                fout = new FileOutputStream(trgt + File.separator+ ze.getName());
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
            }
            result=true;
        } catch (Exception e) {
            e.printStackTrace();
            result=false;
        }
        finally {
            try {
                fin.close();
                zin.closeEntry();
                zin.close();
                fout.close();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return  result;
    }
}
