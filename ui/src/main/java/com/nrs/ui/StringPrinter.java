/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.ui;

import com.nrs.cacheable.CacheManager;
import com.nrs.cacheable.LeastRecentlyUsed;
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
public class StringPrinter {

    public static final Logger logger = LoggerFactory.getLogger(StringPrinter.class);

    public static void main(String[] args) throws NonCacheableException {
        WeekDayChecker dayChecker = new WeekDayChecker(new DateParser());
        CacheManager<BaseWeekDayChecker> manager = new CacheManager<>(dayChecker, new LeastRecentlyUsed(20));
        BaseWeekDayChecker weekChecker = manager.buildProxy();

        String argument = null;
        if (args == null || args.length != 1) {
            System.err.println("Pass an argument, please. Format must be dd-MM-yyyy");
            return;
        } else {
            argument = args[0];
        }
        try {
            //first call will be long
            long start = System.currentTimeMillis();
            String result = weekChecker.parseWeek(argument);
            long end = System.currentTimeMillis() - start;
            logger.info("Method execution time without caching: " + end + " ms");
            System.out.println("The day of week is " + result);
        } catch (ParseException ex) {
            System.err.println("Wrong date format. Format must be \"dd-MM-yyyy\"");
            return;
        }
            
        //this time method does not need to execute. We already invoked that method above, with same args
        try {
            long startAgain = System.currentTimeMillis();
            final String sameValue = weekChecker.parseWeek(argument);
            logger.info("Result: " + sameValue);
            logger.info("Method execution time with caching " + (System.currentTimeMillis() - startAgain) + " ms");
        } catch (ParseException ex) {
            System.err.println("Wrong date format. Format must be \"dd-MM-yyyy\"");
        }

    }
}
