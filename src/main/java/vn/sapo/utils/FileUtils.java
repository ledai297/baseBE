package vn.sapo.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FileUtils {
    public static byte[] downloadFromUrl(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        connection.addRequestProperty("User-Agent", "Mozilla/5.0");

        InputStream inputStream = connection.getInputStream();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        while (true) {
            int len = inputStream.read(buf);
            if (len == -1) {
                break;
            }
            output.write(buf, 0, len);
        }
        return output.toByteArray();
    }
    public static String getFileNameFromUri(String uri){
        return FilenameUtils.getBaseName(uri);
    }
    public static String getStandardFileName(String originalFileName){
        String nonAccentName = TextUtils.removeAccentWithNormalizer(originalFileName);
        String nonDuplicateSpaceName = TextUtils.removeMultipleSpace(nonAccentName);
        String nonSpaceName = nonDuplicateSpaceName.replace(' ', '-');
        String lowerName = nonSpaceName.toLowerCase();

        return lowerName;
    }
    public static String getStandardFileNameFromUri(String uri){
        String originalFileName = getFileNameFromUri(uri);
        return getStandardFileName(originalFileName);
    }
    public static String getFileExtensionFromUri(String uri){
        return FilenameUtils.getExtension(uri);
    }
}
