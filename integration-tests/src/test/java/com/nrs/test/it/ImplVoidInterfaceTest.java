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
public class ImplVoidInterfaceTest implements CacheInterfaceTest{
    
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
