module com.vominh.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires org.apache.commons.lang3;
    requires org.apache.commons.io;
    requires org.bytedeco.javacv;
    requires org.bytedeco.javacpp;
    requires org.bytedeco.opencv;
    requires org.bytedeco.ffmpeg;
    requires java.desktop;


    opens com.vominh.example.thread.fx to javafx.fxml;
    exports com.vominh.example.thread.fx;
    exports com.vominh.example.thread.fx.controller;
    opens com.vominh.example.thread.fx.controller to javafx.fxml;
}