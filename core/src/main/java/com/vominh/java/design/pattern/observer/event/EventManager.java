package com.vominh.java.design.pattern.observer.event;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    Map<String, List<IEventListener>> listeners = new HashMap<>();

    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, IEventListener listener) {
        List<IEventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, IEventListener listener) {
        List<IEventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType, File file) {
        List<IEventListener> users = listeners.get(eventType);
        for (IEventListener listener : users) {
            listener.update(eventType, file);
        }
    }
}
