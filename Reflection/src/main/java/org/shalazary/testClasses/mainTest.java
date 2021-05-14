package org.shalazary.testClasses;

import org.shalazary.AutoInjectable;

public class mainTest {
    @AutoInjectable
    private someInterface some;
    @AutoInjectable
    private notSomeInterface notSome;

    public void print(){
        String res =
        some.some1() + "\n" +
        some.some2() +  "\n" +
        notSome.notSome1() + "\n" +
        notSome.notSome2();

        System.out.println(res);
    }
}
