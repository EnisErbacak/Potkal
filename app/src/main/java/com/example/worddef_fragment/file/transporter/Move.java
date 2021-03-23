package com.example.worddef_fragment.file.transporter;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Move implements FileTransporter {
    @Override
    public boolean transfer(String srcPath, String trgtPath) {
        boolean result=false;

        InputStream in = null;
        OutputStream out = null;
        try {
            //create output directory if it doesn't exist
            in = new FileInputStream(srcPath);
            out = new FileOutputStream(trgtPath);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }

            in.close();
            out.close();

            new File(srcPath).delete();

            result=true;

        }  catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
            result=false;
        }

        finally {
            try {
                in.close();
                // write the output file (You have now copied the file)
                //out.flush();
                out.close();
                result=false;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return result;
    }

}
