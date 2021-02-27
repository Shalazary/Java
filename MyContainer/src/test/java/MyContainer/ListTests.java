package MyContainer;

import org.junit.Assert;
import org.junit.Test;

public class ListTests{
    @Test
    public void insertBackTest(){
        List expected = new List(new int[] {1, 2, 3, 4, 5});

        List actual = new List();
        for(int i = 0; i < 5; i++)
            actual.insertBack(i + 1);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void insertFrontTest(){
        List expected = new List(new int[] {5, 4, 3, 2, 1});

        List actual = new List();
        for(int i = 0; i < 5; i++)
            actual.insertFront(i + 1);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void insertAtTest(){
        List expected = new List(new int[] {1, 2, 3, 4, 5});

        List actual = new List(new int[] {2, 4});

        actual.insertAt(0, 1);
        actual.insertAt(3, 5);
        actual.insertAt(2, 3);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeAtTest(){
        List expected = new List(new int[] {2, 4});

        List actual = new List(new int[] {1, 2, 3, 4, 5});

        actual.removeAt(0);
        actual.removeAt(3);
        actual.removeAt(1);

        Assert.assertEquals(expected, actual);
    }
}