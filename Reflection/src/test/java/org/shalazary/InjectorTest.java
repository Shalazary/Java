package org.shalazary;

import org.junit.Assert;
import org.junit.Test;
import org.shalazary.testClasses.MainTest;

import java.io.IOException;

public class InjectorTest {
    Injector injector;

    @Test
    public void test1() throws IOException {
        injector = new Injector("test1.properties");

        MainTest test = new MainTest();
        injector.inject(test);

        String expected = "Some1 Some2 NotSome1ver1 NotSome2ver1";
        String actual = test.print();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test2() throws IOException {
        injector = new Injector("test2.properties");

        MainTest test = new MainTest();
        injector.inject(test);

        String expected = "Some1 Some2 NotSome1ver2 NotSome2ver2";
        String actual = test.print();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testInterfaceNotFound() throws IOException {
        injector = new Injector("testInterfaceNotFound.properties");

        MainTest test = new MainTest();
        try {
            injector.inject(test);
        } catch (InjectException e){
            String expected = "Not such interface for inject: org.shalazary.testClasses.SomeInterface";
            String actual = e.getMessage();
            Assert.assertEquals(expected, actual);
            return;
        }

        Assert.fail();
    }

    @Test
    public void testImpNotFound() throws IOException {
        injector = new Injector("testImplementationNotFound.properties");

        MainTest test = new MainTest();
        try {
            injector.inject(test);
        } catch (InjectException e){
            String expected = "Not such implementation for inject: org.shalazary.testClasses.IncorrectImp";
            String actual = e.getMessage();
            Assert.assertEquals(expected, actual);
            return;
        }

        Assert.fail();
    }

    @Test
    public void testImpNotInstanceOfInterface() throws IOException {
        injector = new Injector("testImplementationNotInstanceOfInterface.properties");

        MainTest test = new MainTest();
        try {
            injector.inject(test);
        } catch (InjectException e){
            String expected = "Can not set org.shalazary.testClasses.SomeInterface field org.shalazary.testClasses.MainTest.some to org.shalazary.testClasses.NotSomeInterfaceImp1";
            String actual = e.getMessage();
            Assert.assertEquals(expected, actual);
            return;
        }

        Assert.fail();
    }
}
