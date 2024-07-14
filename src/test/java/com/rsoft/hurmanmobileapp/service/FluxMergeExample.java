package com.rsoft.hurmanmobileapp.service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FluxMergeExample {

    public static void main(String[] args) {
        // Create the first Flux with a delay
        Flux<String> flux1 = Flux.just("A", "B", "C")
                .delayElements(Duration.ofMillis(100));

        // Create the second Flux with a different delay
        Flux<String> flux2 = Flux.just("D", "E", "F")
                .delayElements(Duration.ofMillis(150));

        // Merge the two Flux streams

        List<String> x = new ArrayList<>();
        x.add("1");
        x.add("2");
        x.add("3");
        String[] data = x.toArray(new String[0]);
        Flux<String> flux3 = Flux.just(data);
        Flux<String> mergedFlux = Flux.merge(flux1, flux2);

        Object xx = Flux.merge(flux3).flatMap(y->{
            System.out.println("==========================================: "+y);
            return Flux.just(y);
        });

        System.out.println("==========================================: "+xx.getClass());

        // Subscribe to the merged Flux and print the emitted items
        mergedFlux.subscribe(System.out::println);

        // Add a sleep to keep the main thread alive to see the output
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}