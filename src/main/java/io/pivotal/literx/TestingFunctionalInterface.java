package io.pivotal.literx;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
interface Square {
    int calculate(int x);
}

@FunctionalInterface
interface Cube {
    Integer calculate(Integer x);

    default Integer calculate2(Integer x, Integer y) {
        return (x + y) * (x + y) * (x + y);
    }
}

public class TestingFunctionalInterface {
    public static void main(String[] args) {
        testLazy();
    }

    static void testLazy() {
//        Flux<String> helloPauseWorld =
//                Mono.just("Hello")
//                        .concatWith(Mono.just("world")
//                                .delaySubscription(Duration.ofMillis(5000)));
//
////        helloPauseWorld.subscribe(System.out::println).;
//
//        helloPauseWorld.toStream()
//                .forEach(System.out::println);
//
//        Mono<String> a = Mono.just("oops I'm late")
//                .delaySubscription(Duration.ofMillis(450));
//        Flux<String> b = Flux.just("let's get", "the party", "started")
//                .delayElements(Duration.ofMillis(400));
//
//        Flux.firstWithSignal(a, b)
//                .toIterable().forEach(System.out::println);
//        List<String> words = Arrays.asList("the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dogs");
//        Flux<String> lettersWithPositionInAlphabet = Flux.fromIterable(words).flatMap(word -> Flux.just(word.split(""))).sort().distinct().zipWith(Flux.range(1, Integer.MAX_VALUE), (letter, position) -> String.format("%d.%s", position, letter));
//        lettersWithPositionInAlphabet.subscribe(System.out::println);


    }
    static Flux<String> hey(Mono<String> body) {
        return Mono.just("Hey mister ")
                .concatWith(body
                        .flatMap(sir -> Flux.fromArray(sir.[0].split("")))
                        .map(String::toUpperCase)
                        .take(1).concatWith(Mono.just(". how are you?"));
    }
    static Supplier<List<Integer>> supply() {
        Supplier<List<Integer>> supplier = () -> Arrays.asList(5, 9, 8, 2, 0);
        return supplier;
    }

    static Predicate<Integer> predict() {
        return (x) -> x > 5;
    }

    static Consumer<Integer> consume() {
        return System.out::println;
    }

    static Function<Integer, Integer> calculate() {
        Random r = new Random();
        return (x) -> x * x;
    }

    static Function<Integer, Integer> calculate2() {
        Random r = new Random();
        return (x) -> x + x;
    }

    static void test() {
        Supplier<List<Integer>> supplier = supply();
        Consumer<Integer> consumer = consume();
        Predicate<Integer> predicate = predict();
        Function<Integer, Integer> calc = calculate();
        Function<Integer, Integer> calc2 = calculate2();

        System.out.println(supplier.get());

        for (Integer n : supplier.get()) {
            consumer.accept(n);
            if (predicate.test(n)) {
                System.out.println(n);
            } else {
                System.out.println(calc.apply(n));
                System.out.println(calc.compose(calc2).apply(n));
            }
        }

        int a = 5;
        int b = 2;
        // lambda expression to define the calculate method
        Square s = (int x) -> x * x;
        // parameter passed and return type must be
        // same as defined in the prototype
        int ans = s.calculate(a);
        System.out.println(ans);
        Cube c = x -> x * x * x;
        String ss = "ss";

        System.out.println(c.calculate(a));
        System.out.println(c.calculate2(a, b));
    }

    private static class Sir {
        private String lastName;
        private String firstName;

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }
    }
}