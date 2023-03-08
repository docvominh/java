package com.vominh.design.pattern.observer.event;

import java.io.File;

public interface IEventListener {
    void update(String eventType, File file);
}
