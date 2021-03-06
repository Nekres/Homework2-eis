/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.cacheable;

import com.nrs.cacheable.exceptions.NonCacheableClassException;
import com.nrs.cacheable.exceptions.NonCacheableException;
import com.nrs.cacheable.exceptions.NonCacheableMethodException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Using proxy to intercept method calls.
 * 
 * @author root
 */
public class CacheManager<T> implements InvocationHandler {
    
    private final T target;
    private final HashMap<String, Method> cacheable = new HashMap<>();
    private final CacheStrategy strategy;

    public CacheManager(T target, CacheStrategy strategy) {
        this.target = target;
        this.strategy = strategy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if (cacheable.containsKey(method.getName()) && isEqual(cacheable.get(method.getName()), method)) {
                MethodInfo m = new MethodInfo(method.getName(), Arrays.asList(args), method.getParameterCount());
                result =  strategy.getValue(m);
                if(result == null){
                    result = method.invoke(target, args);
                    strategy.putValue(m, result);
                }
                return result;
        } else {
            result = method.invoke(target, args);
            return result;
        }
    }

    /**
     * Method allows to register your object as cacheable.
     *
     * @return proxy-object
     * @throws NonCacheableException
     */
    public T buildProxy() throws NonCacheableException {
        Class clazz = target.getClass();
        List<Method> common = this.getCommonMethods(clazz);
        //common ancestors methods with real type
        for (Method method : common) {
            if (checkOnAnnotation(method)) {
                cacheable.put(method.getName(), method);
            }
        }
        
        for (Method m : clazz.getMethods()) {
            if (checkOnAnnotation(m) && m.getDeclaringClass().equals(clazz)) { //check if this method declared in current class, not in ancestor's class
                if(!cacheable.containsKey(m.getName())){
                    cacheable.put(m.getName(), m); 
                }
            }
        }
        
        if (cacheable.isEmpty()) {
            throw new NonCacheableClassException("At least one method must be annotated as Cacheable");
        }
    
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    /**
     * Used to get common methods from interfaces in hierarchy
     *
     * @return list of common methods
     */
    private final List<Method> getCommonMethods(Class clazz) {
        List<Method> common = new ArrayList<>();
        for (Class c : clazz.getInterfaces()) {
            for (Method a : c.getMethods()) {
                for (Method m : clazz.getMethods()) {
                    if (isEqual(m, a)) {
                        common.add(a);
                    }
                }
            }

        }
        return common;
    }
    /**
     * Check if annotation present at given Method. 
     * @param method - method to check
     * @return true if annotation present
     * @throws NonCacheableMethodException if method has return type = Void. We can't cache void
     */
    private boolean checkOnAnnotation(final Method method) throws NonCacheableMethodException {
        if (method.getAnnotation(Cacheable.class) != null) {
            if (method.getReturnType().equals(Void.TYPE)) {
                throw new NonCacheableMethodException("Method return type must be non-void");
            }
            return true;
        }else
        return false;
    }
    /**
     * Compares methods by their names, parameters count and types
     * @param first
     * @param second
     * @return true if equal
     */
    private boolean isEqual(final Method first, final Method second) {
        boolean equal = first.getParameterCount() == second.getParameterCount() && first.getName().equals(second.getName());
        if (!equal) {
            return false;
        } else if (first.getParameterTypes() != null && second.getParameterTypes() != null) {
            return Arrays.equals(first.getParameterTypes(), second.getParameterTypes());
        } else if (first.getParameterTypes() == null) { //checking only one method, because we already knew that their length is equal
            return true;
        }else return false;
    }
}
