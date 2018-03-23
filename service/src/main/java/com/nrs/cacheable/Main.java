/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.cacheable;

import com.nrs.cacheable.exceptions.NonCacheableException;
import com.nrs.dao.DateParser;
import com.nrs.service.BaseWeekDayChecker;
import com.nrs.service.WeekDayChecker;
import java.text.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  
 * @author root
 */
public class Main {
    static Logger logger = LoggerFactory.getLogger(Main.class.getName());
    
    public static void main(String[] args) throws NonCacheableException, ParseException {
        WeekDayChecker dayChecker = new WeekDayChecker(new DateParser());
        CacheManager<BaseWeekDayChecker> manager = new CacheManager<>(dayChecker, new LeastRecentlyUsed(20));
        BaseWeekDayChecker checker = manager.buildProxy();
        
        //first call will be long
        long start = System.currentTimeMillis();
        final String value = checker.parseWeek("23-03-2018");
        long result = System.currentTimeMillis() - start;
        logger.info("Method execution time without caching: " + result  + " ms");
        
        logger.info("Result: " + value);
        logger.info("One more execution");
        
        long startAgain = System.currentTimeMillis();
        //this time method does not need to execute. We already invoked that method above, with same args
        final String sameValue = checker.parseWeek("23-03-2018");
        logger.info("Result: " + sameValue); 
        logger.info("Method execution time with caching " + (System.currentTimeMillis() - startAgain) + " ms");
        
        
    }
    
}
