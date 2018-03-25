/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.test.it;

import com.nrs.cacheable.Cacheable;

/**
 *
 * @author nrs
 */
public class ImplStringCacheInterfaceTest implements CacheInterfaceTest{
    private int realInvokeCount = 0;
    @Cacheable
    @Override
    public String getString(String value){
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
