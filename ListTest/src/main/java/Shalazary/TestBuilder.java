package Shalazary;

import java.util.function.Consumer;
import java.util.function.Function;

public class TestBuilder<T> {
    /**
     * Building a function to test method of object and return test time
     * @param test - main testing method
     * @param before - preparation of the object before the test
     * @param after - post-test object handling
     * @return test function
     */
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

    /**
     * Building a function to test method of object and return test time
     * @param test - main testing method
     * @return test function
     */
    public Function<T, Long> build(Consumer<T> test) {
        return (T testObj) -> {
            long beginTime = System.nanoTime();
            test.accept(testObj);
            long endTime = System.nanoTime();
            return endTime - beginTime;
        };
    }
}
