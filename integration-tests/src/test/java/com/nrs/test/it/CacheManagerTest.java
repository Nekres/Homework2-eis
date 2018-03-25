/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.test.it;

import com.nrs.cacheable.CacheManager;
import com.nrs.cacheable.LeastRecentlyUsed;
import com.nrs.cacheable.exceptions.NonCacheableException;
import com.nrs.cacheable.exceptions.NonCacheableMethodException;
import com.nrs.dao.BaseDateParser;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author nrs
 */
public class CacheManagerTest {
    
    @Test
    public void test() throws NonCacheableException{
        CacheManager<CacheInterfaceTest> manager = new CacheManager<>(new ImplStringCacheInterfaceTest(), new LeastRecentlyUsed(10));
        CacheInterfaceTest proxy = manager.buildProxy();
        String value = "Somevalue";
        proxy.getString(value);
        Assert.assertEquals(1, proxy.getInvokeCount());
        
        proxy.getString(value);
        Assert.assertEquals(1, proxy.getInvokeCount());
    }
    @Test(expected = NonCacheableMethodException.class)
    public void testNonCacheableMethodException() throws NonCacheableException{
        CacheManager<CacheInterfaceTest> manager = new CacheManager<>(new ImplVoidInterfaceTest(), new LeastRecentlyUsed(10));
        CacheInterfaceTest proxy = manager.buildProxy();
        proxy.getVoid();
    }
}
