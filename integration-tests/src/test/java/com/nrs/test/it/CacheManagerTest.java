/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.test.it;

import com.nrs.cacheable.CacheManager;
import com.nrs.cacheable.Cacheable;
import com.nrs.cacheable.LeastRecentlyUsed;
import com.nrs.cacheable.exceptions.NonCacheableClassException;
import com.nrs.cacheable.exceptions.NonCacheableException;
import com.nrs.cacheable.exceptions.NonCacheableMethodException;
import com.nrs.dao.BaseDateParser;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author nrs
 */
public class CacheManagerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test() throws NonCacheableException {
        CacheManager<CacheInterfaceTest> manager = new CacheManager<>(new ImplStringCacheInterfaceTest(), new LeastRecentlyUsed(10));
        CacheInterfaceTest proxy = manager.buildProxy();
        String value = "Somevalue";
        proxy.getString(value);
        Assert.assertEquals(1, proxy.getInvokeCount());

        proxy.getString(value);
        Assert.assertEquals(1, proxy.getInvokeCount());
    }
    @Test
    public void testNonCacheableMethodException() throws NonCacheableException {
        thrown.expect(NonCacheableMethodException.class);
        CacheManager<CacheInterfaceTest> manager = new CacheManager<>(new ImplVoidInterfaceTest(), new LeastRecentlyUsed(10));
        CacheInterfaceTest proxy = manager.buildProxy();
        proxy.getVoid();
    }
    @Test
    public void testNonCacheableClassException() throws NonCacheableException{
        thrown.expect(NonCacheableClassException.class);
        CacheManager<CacheInterfaceTest> manager = new CacheManager<>(new ImplNoAnnotationInterface(), new LeastRecentlyUsed(10));
        manager.buildProxy();
    }

    public interface CacheInterfaceTest {

        public String getString(String value);

        public int getInvokeCount();

        public void getVoid();
    }

    public class ImplVoidInterfaceTest implements CacheInterfaceTest {
        @Override
        public String getString(String value) {
            return null;
        }
        @Override
        public int getInvokeCount() {
            return 0;
        }
        @Cacheable
        @Override
        public void getVoid() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public class ImplStringCacheInterfaceTest implements CacheInterfaceTest {

        private int realInvokeCount = 0;

        @Cacheable
        @Override
        public String getString(String value) {
            realInvokeCount++;
            return "string" + value;
        }

        @Override
        public int getInvokeCount() {
            return this.realInvokeCount;
        }

        @Override
        public void getVoid() {
        }
    }
    public class ImplNoAnnotationInterface implements CacheInterfaceTest{
        @Override
        public String getString(String value) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        @Override
        public int getInvokeCount() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        @Override
        public void getVoid() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
