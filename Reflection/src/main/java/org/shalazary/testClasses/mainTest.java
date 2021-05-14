package org.shalazary.testClasses;

import org.shalazary.AutoInjectable;

public class mainTest {
    @AutoInjectable
    private someInterface some;
    @AutoInjectable
    private notSomeInterface notSome;

    public void print(){
        some.some1();
        some.some2();
        notSome.notSome1();
        notSome.notSome2();
    }
}
