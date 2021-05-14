package org.shalazary.testClasses;

import org.shalazary.AutoInjectable;

public class MainTest {
    @AutoInjectable
    private SomeInterface some;
    @AutoInjectable
    private NotSomeInterface notSome;

    public String print(){

        return some.some1() + " " +
        some.some2() +  " " +
        notSome.notSome1() + " " +
        notSome.notSome2();
    }
}
