/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.tests.cache;

import com.nrs.cacheable.CacheManager;
import com.nrs.cacheable.CacheStrategy;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author root
 */
public class CacheManagerTest {
    CacheStrategy strategy = Mockito.mock(CacheStrategy.class);
    
    @Test
    public void testIsEqual() throws NoSuchMethodException{
//        when(strategy.getValue("example", null)).thenReturn(this)
//        CacheManager<Iservice> cacheManager = new CacheManager<>(service);
        
        
    }
}
