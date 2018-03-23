/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.tests.cache;

import com.nrs.cacheable.LeastRecentlyUsed;
import org.junit.Test;

/**
 *
 * @author root
 */
public class LRUTest {
    
    @Test
    public void testLRU(){
        LeastRecentlyUsed lru = new LeastRecentlyUsed(10);
        
    }
}
