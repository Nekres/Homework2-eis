/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.tests.cache;

import com.nrs.cacheable.CacheManager;
import java.lang.reflect.Method;
import org.junit.Test;

/**
 *
 * @author root
 */
public class CacheManagerTest {
    
    @Test
    public void testIsEqual() throws NoSuchMethodException{
        Method isEqual = CacheManager.class.getDeclaredMethod("isEqual", Method.class,Method.class);
        isEqual.setAccessible(true);
        
        
    }
}
