package io.pivotal.literx;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.function.Consumer;
import java.util.function.Function;

class  TestingFunctionalInterfaceTest {

    TestingFunctionalInterface testingFunctionalInterface = new TestingFunctionalInterface();
    @Test
    void supply() {
        assertEquals(TestingFunctionalInterface.supply().get(), Arrays.asList(5, 9, 8,2, 0));
    }


    @Test
    void predict() {
        assertFalse(TestingFunctionalInterface.predict().test(5));
    }

    @Test
    void consume() {

        Mono.fromCallable( () -> System.currentTimeMillis() )
                .repeat()
                .publishOn(Schedulers.single())
                .log("foo.bar")
                .flatMap(time ->
                                Mono.fromCallable(() -> { Thread.sleep(1000); return time; })
                                        .subscribeOn(Schedulers.parallel())
                        , 8) //maxConcurrency 8
                .subscribe();
        assertFalse(TestingFunctionalInterface.consume().toString().isEmpty());
    }

    @Test
    void calculate() {
        assertEquals(TestingFunctionalInterface.calculate().apply(5), 25);
    }

    @Test
    public void consumeTODO() {
        Consumer<Integer> expected = null;
        Consumer<Integer> actual = TestingFunctionalInterface.consume();

        assertEquals(expected, actual);
    }

}
