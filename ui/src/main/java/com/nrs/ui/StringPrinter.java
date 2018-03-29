/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.ui;

import com.nrs.cacheable.CacheManager;
import com.nrs.cacheable.LeastRecentlyUsed;
import com.nrs.cacheable.exceptions.NonCacheableException;
import com.nrs.dao.BaseDateParser;
import com.nrs.dao.DateParser;
import com.nrs.service.BaseWeekDayChecker;
import com.nrs.service.WeekDayChecker;
import java.text.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author root
 */
public class StringPrinter {

    public static final Logger logger = LoggerFactory.getLogger(StringPrinter.class);

    public static void main(String[] args) throws NonCacheableException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:ui-context.xml", "classpath:custom-dao-context.xml","classpath:service-context.xml"});
        WeekDayChecker dayChecker = (WeekDayChecker)ctx.getBean("dayChecker");
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
            long start = System.currentTimeMillis();
            String result = weekChecker.parseWeek(argument);
            long end = System.currentTimeMillis() - start;
            logger.info("Method execution time without caching: " + end + " ms");
            System.out.println("The day of week is " + result);
        } catch (ParseException ex) {
            System.err.println("Wrong date format. Format must be \"dd-MM-yyyy\"");
            return;
        }
            //second invoke with same args, to show @Cacheable works fine
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
