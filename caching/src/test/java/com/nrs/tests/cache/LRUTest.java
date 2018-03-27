/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.tests.cache;

import com.nrs.cacheable.LeastRecentlyUsed;
import com.nrs.cacheable.MethodInfo;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author root
 */
public class LRUTest {
    
    @Test
    public void testLRU(){
        LeastRecentlyUsed lru = new LeastRecentlyUsed(10);
        MethodInfo info = new MethodInfo("test", null, 0);
        lru.putValue(info, "200");
        Object result = lru.getValue(info);
        Object expected = "200";
        Assert.assertEquals(expected, result);
        
        List<Object> args = new ArrayList<>();
        args.add("One");
        args.add("Two");
        MethodInfo oneMore = new MethodInfo("test2",args, args.size());
        Object expected2 = "OneTwo";
        lru.putValue(oneMore, expected2);
        Object result2 = lru.getValue(oneMore);
        
        Assert.assertEquals(expected2, result2);
        
    }
}
