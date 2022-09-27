package vn.sapo.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;
import org.apache.commons.io.FilenameUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ImageUtils {

    public static ByteArrayOutputStream resize(ByteArrayInputStream input, Integer width, Integer height, Boolean forceSize) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (forceSize == true) {
            Thumbnails.of(input)
                .scalingMode(ScalingMode.PROGRESSIVE_BILINEAR)
//                Force size là ép vào khung kích thước mình mong muốn
                .forceSize(width, height)
                .outputFormat("JPEG")
                .outputQuality(1)
                .toOutputStream(outputStream);
//             .toFile("resized.jpg");
            return outputStream;
        }
        else{
            Thumbnails.of(input)
                .scalingMode(ScalingMode.PROGRESSIVE_BILINEAR)
                //Size là scale giữ tỉ lệ ảnh gốc và scale tới khi 1 điều kiện WIDTH hoặc HEIGHT được thỏa mãn
                .size(width, height)
                .outputFormat("JPEG")
                .outputQuality(1)
                .toOutputStream(outputStream);
//             .toFile("resized.jpg");
        }

        return outputStream;
    }
}
