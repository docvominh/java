package com.vominh.example.thread.fx.utils;

import com.vominh.example.thread.fx.PlayApplication;
import org.apache.commons.io.FilenameUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class FileUtils {

    public static File createTempFile() {
        UUID uuid = UUID.randomUUID();
        return new File(PlayApplication.appTempDir + File.separator + uuid);
    }

    public static void writeToFile(Path path, byte[] bytes) {
        try (FileOutputStream outputStream = new FileOutputStream(path.toFile())) {
            outputStream.write(bytes);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void appendChunk(Path path, byte[] bytes) {
        try (FileOutputStream outputStream = new FileOutputStream(path.toFile(), true)) {
            outputStream.write(bytes);
//            System.out.println(String.format("Appended to: %s", path.toString()));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static byte[] download(String url, String range) throws IOException {
        URL publicUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) publicUrl.openConnection();
        conn.setRequestProperty("Range", "bytes=" + range);
        conn.connect();
        byte[] data;
        try (InputStream inputStream = conn.getInputStream()) {
            data = inputStream.readAllBytes();
        }
//        System.out.println(String.format("Download Range: %s", conn.getRequestProperty("Range")));
        return data;
    }

    public static File extractThumbnail(File file) throws FFmpegFrameGrabber.Exception {
        String dir = file.getParent();
        File outputFile = new File(dir + File.separator + file.getName() + ".png");
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(file.getAbsolutePath());
        frameGrabber.setFormat(FilenameUtils.getExtension(file.getName()));
        frameGrabber.start();

        Java2DFrameConverter frameConverter = new Java2DFrameConverter();

        try {
            BufferedImage bufferedImage;
            Frame f = frameGrabber.grabKeyFrame();
            bufferedImage = frameConverter.convert(f);
            while (bufferedImage != null) {
                ImageIO.write(bufferedImage, "png", outputFile);
                f = frameGrabber.grabKeyFrame();
                bufferedImage = frameConverter.convert(f);
            }
            frameGrabber.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputFile;
    }

    public static void checkTempFolder() throws IOException {
        if (!Files.isDirectory(Path.of(PlayApplication.appTempDir))) {
            Files.createDirectory(Path.of(PlayApplication.appTempDir));
        }
    }
}
