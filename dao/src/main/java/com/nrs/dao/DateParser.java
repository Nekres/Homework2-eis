/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.dao;

import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author root
 */
public class DateParser implements BaseDateParser{
    public static final String[] DAYS = {"Sunday", "Monday", "Tuesday","Wednesday", "Thursday", "Friday", "Saturday"};
    private static final Logger logger = LoggerFactory.getLogger(DateParser.class);
    
    @Override
    public String getWeek(final Date date){
        logger.info("date: " + date.toString());
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        int num = calendar.get(Calendar.DAY_OF_WEEK);
        return DAYS[num-1];
    }
    
}