package com.company;

import com.company.demo.RangeList;
import org.junit.Test;

import org.junit.Assert;

public class RangeListTest {

    /**
     * junit unit test
     */
    @Test
    public void test() {
        RangeList rl = new RangeList();
        System.out.println(rl.toString()); // Should be ""
        Assert.assertEquals(rl.toString(), "");
        rl.add(1, 5);
        System.out.println(rl.toString()); // Should be: "[1, 5)"
        Assert.assertEquals(rl.toString(), "[1, 5)");
        rl.add(10, 20);
        System.out.println(rl.toString()); // Should be: "[1, 5) [10, 20)"
        Assert.assertEquals(rl.toString(), "[1, 5) [10, 20)");
        rl.add(20, 20);
        System.out.println(rl.toString()); // Should be: "[1, 5) [10, 20)"
        Assert.assertEquals(rl.toString(), "[1, 5) [10, 20)");
        rl.add(20, 21);
        System.out.println(rl.toString()); // Should be: "[1, 5) [10, 21)"
        Assert.assertEquals(rl.toString(), "[1, 5) [10, 21)");
        rl.add(2, 4);
        System.out.println(rl.toString()); // Should be: "[1, 5) [10, 21)"
        Assert.assertEquals(rl.toString(), "[1, 5) [10, 21)");
        rl.add(3, 8);
        System.out.println(rl.toString()); // Should be: "[1, 8) [10, 21)"
        Assert.assertEquals(rl.toString(), "[1, 8) [10, 21)");
        rl.remove(10, 10);
        System.out.println(rl.toString()); // Should be: "[1, 8) [10, 21)"
        Assert.assertEquals(rl.toString(), "[1, 8) [10, 21)");
        rl.remove(10, 11);
        System.out.println(rl.toString()); // Should be: "[1, 8) [11, 21)"
        Assert.assertEquals(rl.toString(), "[1, 8) [11, 21)");
        rl.remove(15, 17);
        System.out.println(rl.toString()); // Should be: "[1, 8) [11, 15) [17, 21)"
        Assert.assertEquals(rl.toString(), "[1, 8) [11, 15) [17, 21)");
        rl.remove(3, 19);
        System.out.println(rl.toString()); // Should be: "[1, 3) [19, 21)"
        Assert.assertEquals(rl.toString(), "[1, 3) [19, 21)");
        rl.remove(4, 18);
        System.out.println(rl.toString()); // Should be: "[1, 3) [19, 21)"
        Assert.assertEquals(rl.toString(), "[1, 3) [19, 21)");
        rl.add(1, 21);
        System.out.println(rl.toString()); // Should be: "[1, 21)"
        Assert.assertEquals(rl.toString(), "[1, 21)");
        rl.remove(1, 21);
        System.out.println(rl.toString()); // Should be: ""
        Assert.assertEquals(rl.toString(), "");
    }
}
