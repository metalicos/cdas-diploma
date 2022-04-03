package ua.com.cyberdone.accountmicroservice.common.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

@Slf4j
@UtilityClass
public class ImageConverterUtils {

    public static String convertBlobToBase64StringImage(byte[] imageBlob) {
        return Base64.getEncoder().encodeToString(imageBlob);
    }

    public static byte[] getImageBytes(String imageUrl) throws IOException {
        var url = new URL(imageUrl);
        var output = new ByteArrayOutputStream();
        try (var stream = url.openStream()) {
            var buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = stream.read(buffer)) >= 0) {
                output.write(buffer, 0, bytesRead);
            }
        }
        return output.toByteArray();
    }
}