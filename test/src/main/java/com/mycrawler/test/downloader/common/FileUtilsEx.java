package com.mycrawler.test.downloader.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class FileUtilsEx extends FileUtils
{
    /**
     * Writes a byte array to a file creating the file if it does not exist.
     *
     * @param file  the file to write to
     * @param data  the content to write to the file
     * @param off   the start offset in the data.
     * @param len   the number of bytes to write.
     * @param append if {@code true}, then bytes will be added to the
     * end of the file rather than overwriting
     * @throws IOException in case of an I/O error
     * @since IO 2.1
     */
    public static void writeByteArrayToFile(OutputStream out, byte[] data, int off, int len) throws IOException {
        try {
            out.write(data, off, len);
        }catch(Exception ex){
            IOUtils.closeQuietly(out);
        } 
    }
}
