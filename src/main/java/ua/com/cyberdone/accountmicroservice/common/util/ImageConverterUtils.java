package ua.com.cyberdone.accountmicroservice.common.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Base64;

import static java.util.Objects.nonNull;

@Slf4j
@UtilityClass
public class ImageConverterUtils {

    public static String convertMultipartFileToBase64StringImage(MultipartFile file) {
        try {
            return convertBlobToBase64StringImage(file.getBytes());
        } catch (IOException ex) {
            log.error("Multipart file GetBytes error. " + ex.getMessage());
            throw new UncheckedIOException(ex);
        } catch (NullPointerException ex) {
            log.error("Multipart file is null.");
        }
        return "";
    }

    public static byte[] convertImageToBlob(BufferedImage image) {
        try (var baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            var bais = new ByteArrayInputStream(baos.toByteArray());
            var bytes = bais.readAllBytes();
            bais.close();
            return bytes;
        } catch (IOException ex) {
            log.error("Read bytes error. " + ex.getMessage());
            throw new UncheckedIOException(ex);
        }
    }

    public static byte[] convertImageFromFileToBlob(String filePath) {
        try {
            return convertImageToBlob(ImageIO.read(new File(filePath)));
        } catch (IOException ex) {
            log.error("Read bytes error. " + ex.getMessage());
            throw new UncheckedIOException(ex);
        }

    }

    public static byte[] setDefaultImageFromFileWhileBlobNull(byte[] blob, String defaultImagePath) {
        if (nonNull(blob)) {
            return blob;
        }
        if (nonNull(defaultImagePath)) {
            return convertImageFromFileToBlob(defaultImagePath);
        }
        log.error("Error. Blob is null && path to default image is null");
        throw new RuntimeException("Error. Blob is null && path to default image is null");
    }

    public static String convertBlobToBase64StringImage(byte[] imageBlob) {
        return Base64.getEncoder().encodeToString(imageBlob);
    }

    public static byte[] convertBase64StringImageToBlob(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}