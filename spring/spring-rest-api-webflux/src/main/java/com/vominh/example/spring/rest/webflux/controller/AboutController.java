package com.vominh.example.spring.rest.webflux.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class AboutController {
    private static final String longText = "Java is a high-level, class-based, object-oriented programming language.";

    @GetMapping(path = "/about-mono", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> aboutMono() {
        return Mono.just(longText);
    }

    @GetMapping(path = "/about-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> aboutStream() {

        return Flux.range(0, longText.length()).delayElements(Duration.ofMillis(50)).map(index -> longText.charAt(index) + "");
//        AtomicInteger start = new AtomicInteger(-1);
//        return Flux.interval(Duration.ofMillis(100))
//                .map(sequence -> {
//                    start.getAndIncrement();
//
//                    if(start.get() >= longText.length()){
//                    }
//                    return String.valueOf(longText.charAt(start.get()));
//                });
    }

    @GetMapping("/about-sse")
    public SseEmitter streamSseMvc() {
        SseEmitter emitter = new SseEmitter(15000l);
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                for (int i = 0; i < longText.length(); i++) {
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .data("SSE MVC - " + longText.charAt(i))
                            .id(String.valueOf(i))
                            .name("sse event - mvc");
                    emitter.send(event);
                    Thread.sleep(1000);
                }
                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }

        });
        return emitter;
    }
}
