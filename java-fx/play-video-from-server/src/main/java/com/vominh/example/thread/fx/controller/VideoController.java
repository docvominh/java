package com.vominh.example.thread.fx.controller;

import com.vominh.example.thread.fx.PlayApplication;
import com.vominh.example.thread.fx.dto.MediaInfo;
import com.vominh.example.thread.fx.task.Download;
import com.vominh.example.thread.fx.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VideoController {

    private MediaPlayer player;
    private long chunkSize;
    private long lastDownloadByte;
    private long bytesPerSecond;
    private int timeToPause;
    private Path filePath;
    private int totalSecond;
    private int currentSecond;
    private MediaInfo mediaInfo;
    private boolean downloadLock;
    private boolean waitForDownload;
    private final ExecutorService executorService;

    @FXML
    private TextField url;

    @FXML
    public Label message;

    @FXML
    private Label mediaName;

    @FXML
    private Label videoSecond;

    @FXML
    private Pane mediaPane;

    @FXML
    private MediaView mediaView;

    @FXML
    private ProgressBar playBar;

    @FXML
    private Label downloadLabel;

    @FXML
    private ProgressBar downloadBar;

    @FXML
    private Button play;

    @FXML
    private Button pause;

    public VideoController() {
        executorService = Executors.newSingleThreadExecutor();
    }

    @FXML
    public void initialize() {
        // https://pdminhdev.s3.ap-southeast-1.amazonaws.com/Captain+America+The+First+Avenger+2011.mp4
        url.setText("https://pdminhdev.s3.ap-southeast-1.amazonaws.com/Sting+-+Shape+of+My+Heart+(Leon).mp4");
        timeToPause = 30;
    }

    @FXML
    protected void loadUrl() throws IOException {
        reset();
        try {
            mediaInfo = getMedia(url.getText());
        } catch (IOException exception) {
            message.setText("Url not valid or network not work");
        }

        if (mediaInfo == null || !mediaInfo.isUrlValid()) {
            message.setText("Url not valid or network not work");
        } else {
            chunkSize = calculateChunkSize(mediaInfo.getContentLength());
            lastDownloadByte = chunkSize;
            bytesPerSecond = mediaInfo.getContentLength() / mediaInfo.getDuration();


            // download first time 10%
            byte[] data = FileUtils.download(url.getText(), String.format("0-%s", chunkSize));

            // Create local play file
            File file = FileUtils.createTempFile();
            filePath = Paths.get(file.getAbsolutePath());
            FileUtils.writeToFile(filePath, data);

            // Extract thumbnail
            try {
                File thumbnail = FileUtils.extractThumbnail(file);
                Image image = new Image(thumbnail.toURI().toString());
                BackgroundImage myBI = new BackgroundImage(image,
                        BackgroundRepeat.REPEAT,
                        BackgroundRepeat.REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(1.0, 1.0, true, true, false, false));
                mediaPane.setBackground(new Background(myBI));
            } catch (Exception exception) {
                System.out.println("Thumbnail not available");
            }

            // Update User interface
            mediaName.setText(mediaInfo.getName());
            Media media = new Media(file.toURI().toString());
            player = createMediaPlayerInstance(media);
            play.setDisable(false);

            totalSecond = mediaInfo.getDuration();
            updateDownloadProgressbar(lastDownloadByte, mediaInfo.getContentLength());
        }
    }

    @FXML
    protected void play() {
        if (mediaView.getMediaPlayer() == null) {
            mediaView.setMediaPlayer(player);
        }

        player.play();
    }

    @FXML
    protected void pause() {
        if (mediaView.getMediaPlayer() == null) {
            return;
        }
        player.pause();
    }

    @FXML
    protected void openCache() throws IOException {
        Desktop.getDesktop().open(filePath.getParent().toFile());
    }

    private MediaPlayer createMediaPlayerInstance(Media media) {
        MediaPlayer player = new MediaPlayer(media);

        player.currentTimeProperty().addListener(observable -> {
            int temp = (int) player.getCurrentTime().toSeconds();

            // Pause before 10 second when reached current downloaded
            // Or player will not able to resume when media end
//            System.out.println(bytesPerSecond * temp + "/" + (lastDownloadByte - (bytesPerSecond * 10)));
            if (bytesPerSecond * temp > lastDownloadByte - (bytesPerSecond * 10) && player.getStatus() == MediaPlayer.Status.PLAYING) {
                System.out.println("Try to pause player");
                player.pause();
                waitForDownload = true;
            }

            if (currentSecond != temp) {
                PlayApplication.showThreadInfo();
                currentSecond = temp;
                updateVideoSecond(currentSecond);
                updatePlayProgressBar(currentSecond);

                // Check to download new chunk
                if (timeToDownload(bytesPerSecond, currentSecond, lastDownloadByte)) {
                    downloadNewChunk();
                }
            }
        });

        player.setOnError(new Runnable() {
            @Override
            public void run() {
                System.out.println("Error: " + player.getStatus());
            }
        });

        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                System.out.println("End: " + player.getStatus());
            }
        });

        player.setOnPaused(new Runnable() {
            @Override
            public void run() {
                System.out.println("Paused: " + player.getStatus());
            }
        });

        return player;
    }

    private MediaInfo getMedia(String urlString) throws IOException {
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.setUrl(urlString);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        if (conn.getResponseCode() != 200) {
            mediaInfo.setUrlValid(false);
            return mediaInfo;
        }

        // Get file information
        String raw = conn.getHeaderField("Content-Disposition");
        // raw = "attachment; filename=abc.jpg"
        if (raw != null && raw.indexOf("=") != -1) {
            String fileName = raw.split("=")[1]; //getting value after '='
            mediaInfo.setName(fileName);
        } else {
            // fall back to random generated file name?
        }
        mediaInfo.setContentType(conn.getContentType());
        mediaInfo.setContentLength(conn.getContentLengthLong());

        String duration = conn.getHeaderField("x-amz-meta-duration");
        mediaInfo.setDuration(Integer.parseInt(duration));
        mediaInfo.setUrlValid(true);
        return mediaInfo;
    }

    private void reset() {
        if (player != null) {
            player.stop();
            player.dispose();
        }

        mediaView.setMediaPlayer(null);
        playBar.setProgress(0.0);
        downloadBar.setProgress(0.0);
        this.lastDownloadByte = 0;
        this.chunkSize = 0;
        this.totalSecond = 0;
        this.currentSecond = 0;
    }

    private long calculateChunkSize(long contentLength) {
        long kilobyte = 1024;
        long megabyte = kilobyte * 1024;
        long gigabyte = megabyte * 1024;
        long terabyte = gigabyte * 1024;

        if (contentLength / megabyte < 100) {
            return contentLength / 10;
        } else if (contentLength / megabyte > 100 && contentLength / megabyte <= 500) {
            return contentLength / 20;
        } else if (contentLength / megabyte > 500 && contentLength / megabyte <= 2000) {
            return contentLength / 50;
        } else {
            return contentLength / 100;
        }
    }

    private void downloadNewChunk() {
        if (!downloadLock) {
            downloadLock = true;
            Download download = new Download(mediaInfo.getUrl(), calculateRangeHeader());
            download.setOnSucceeded(e -> {
                FileUtils.appendChunk(filePath, download.getValue());
                lastDownloadByte = lastDownloadByte + chunkSize;
                updateDownloadProgressbar(lastDownloadByte, mediaInfo.getContentLength());
                downloadLock = false;
//                System.out.println(waitForDownload);
//                System.out.println("Status: " + player.getStatus());
//                System.out.println("getCurrentTime: " + player.getCurrentTime());
                if (player.getStatus() == MediaPlayer.Status.PAUSED && waitForDownload) {
                    player.play();
                    waitForDownload = false;
                }
            });

            executorService.submit(download);
        }
    }

    private void updateVideoSecond(int second) {
        String format = totalSecond >= 3600 ? "HH:mm:ss" : "mm:ss";
        String currentTime = DurationFormatUtils.formatDuration((second) * 1000L, format);
        String totalTime = DurationFormatUtils.formatDuration((this.totalSecond) * 1000L, format);
        videoSecond.setText(String.format("%s / %s", currentTime, totalTime));
    }

    private void updatePlayProgressBar(int currentSecond) {
        playBar.setProgress((double) currentSecond / totalSecond);

        if (playBar.getProgress() < 0.01) {
            playBar.setProgress(0.01);
        }
    }

    private void updateDownloadProgressbar(long downloaded, long total) {
        downloadBar.setProgress((double) downloaded / (double) total);
        downloadLabel.setText(String.format("Downloaded %s/%s", bytesIntoHumanReadable(downloaded), bytesIntoHumanReadable(total)));
    }


    private boolean timeToDownload(long bytesPerSecond, int currentSecond, long lastDownloadByte) {
        long playedBytes = bytesPerSecond * currentSecond;

        if (lastDownloadByte >= mediaInfo.getContentLength()) {
            return false;
        }

        return playedBytes > lastDownloadByte - (bytesPerSecond * timeToPause);
    }

    private String calculateRangeHeader() {
        long newLastBytes = lastDownloadByte + chunkSize;
        if (newLastBytes >= mediaInfo.getContentLength()) {
            newLastBytes = mediaInfo.getContentLength();
        }

        String range = String.format("%s-%s", lastDownloadByte + 1, newLastBytes);
        return range;
    }

    private String bytesIntoHumanReadable(long bytes) {
        long kilobyte = 1024;
        long megabyte = kilobyte * 1024;
        long gigabyte = megabyte * 1024;
        long terabyte = gigabyte * 1024;

        if ((bytes >= 0) && (bytes < kilobyte)) {
            return bytes + " B";

        } else if ((bytes >= kilobyte) && (bytes < megabyte)) {
            return (bytes / kilobyte) + " KB";

        } else if ((bytes >= megabyte) && (bytes < gigabyte)) {
            return (bytes / megabyte) + " MB";

        } else if ((bytes >= gigabyte) && (bytes < terabyte)) {
            return (bytes / gigabyte) + " GB";

        } else if (bytes >= terabyte) {
            return (bytes / terabyte) + " TB";

        } else {
            return bytes + " Bytes";
        }
    }
}