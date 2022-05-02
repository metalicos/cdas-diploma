package ua.com.cyberdone.accountmicroservice.common.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.Optional;

@Slf4j
@UtilityClass
public class ImageUtils {

    public static String imageBytesToBase64(byte[] image) {
        return image == null ? null : Base64.getEncoder().encodeToString(image);
    }

    public static byte[] getImageBytes(String imageUrl) throws IOException {
        try (var stream = new URL(imageUrl).openStream()) {
            return stream.readAllBytes();
        }
    }

    public static String getScaledBase64OrElseOriginal(byte[] image, ImageStandards imageStandards) {
        return imageBytesToBase64(ImageUtils.scaleImage(image, imageStandards.getWidth(), imageStandards.getHeight())
                .orElse(image));
    }

    public static Optional<byte[]> scaleImage(byte[] image, int width, int height) {
        try (var baos = new ByteArrayOutputStream()) {
            getBuilder(image)
                    .size(width, height)
                    .outputFormat("PNG")
                    .outputQuality(0.75)
                    .toOutputStream(baos);
            return Optional.of(baos.toByteArray());
        } catch (IOException e) {
            log.error("Error to fill output stream by converted image bytes.", e);
            return Optional.empty();
        }
    }

    public static Thumbnails.Builder<? extends InputStream> getBuilder(byte[] image) {
        return Thumbnails.of(new ByteArrayInputStream(image));
    }
}