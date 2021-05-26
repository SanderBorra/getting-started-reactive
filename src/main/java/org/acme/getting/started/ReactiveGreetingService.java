package org.acme.getting.started;

import java.time.Duration;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
@ApplicationScoped
public class ReactiveGreetingService {
    public Uni<String> greeting(String name) {
        return Uni.createFrom().item(name)
            .onItem()
            .transform(n -> String.format("hello %s", n));
    }
    public Multi<String> greetings(int count, String name) {
        return Multi.createFrom()
            .ticks().every(Duration.ofMillis(100))
            .onItem().transform(n -> String.format("Hallo %s - %d", name, n))
            .transform().byTakingFirstItems(count);
    }

}
