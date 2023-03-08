package com.vominh.example.thread.fx.task;

import com.vominh.example.thread.fx.PlayApplication;
import com.vominh.example.thread.fx.utils.FileUtils;
import javafx.concurrent.Task;

public class Download extends Task<byte[]> {

    private String url;

    // Example 0-5000
    private String range;

    public Download(String url, String range) {
        this.url = url;
        this.range = range;
    }

    @Override
    public byte[] call() throws Exception {
        PlayApplication.showThreadInfo();
        return FileUtils.download(this.url, range);
    }
}
