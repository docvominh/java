package com.vominh.example.thread.fx;

import com.vominh.example.thread.fx.utils.FileUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PlayApplication extends Application {

    public static String appTempDir;

    public PlayApplication() {
        String property = "java.io.tmpdir";
        String tempDir = System.getProperty(property);
        appTempDir = tempDir + File.separator + "video-play-app";
    }

    @Override
    public void start(Stage stage) throws IOException {
        showThreadInfo();
        FileUtils.checkTempFolder();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Play");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void showThreadInfo() {
        System.out.println(String.format("Thread ID: %s, Thread Name: %s, Group: %s",
                Thread.currentThread().getId(),
                Thread.currentThread().getName(),
                Thread.currentThread().getThreadGroup()));
    }

    public static void main(String[] args) {
        showThreadInfo();
        launch();
    }
}