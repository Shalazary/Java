package Shalazary;

import java.util.function.Consumer;
import java.util.function.Function;

public class TestBuilder<T> {
    public Function<T, Long> build(Consumer<T> test, Consumer<T> before, Consumer<T> after) {
        return (T testObj) -> {
            before.accept(testObj);
            long beginTime = System.nanoTime();
            test.accept(testObj);
            long endTime = System.nanoTime();
            after.accept(testObj);
            return endTime - beginTime;
        };
    }

    public Function<T, Long> build(Consumer<T> test) {
        return (T testObj) -> {
            long beginTime = System.nanoTime();
            test.accept(testObj);
            long endTime = System.nanoTime();
            return endTime - beginTime;
        };
    }
}
