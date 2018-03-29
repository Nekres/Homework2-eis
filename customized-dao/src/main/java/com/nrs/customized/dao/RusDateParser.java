/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.customized.dao;

import com.nrs.dao.BaseDateParser;
import com.nrs.dao.DateParser;
import static com.nrs.dao.DateParser.DAYS;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author root
 */
public class RusDateParser implements BaseDateParser{
    public static final String[] DAYS = {"Воскресенье", "Понедельник", "Вторник","Среда", "Четверг", "Пятница", "Суббота"};
    private static final Logger logger = LoggerFactory.getLogger(DateParser.class);
    
    @Override
    public String getWeek(Date input) {
        logger.info("date: " + input.toString());
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(input);
        
        int num = calendar.get(Calendar.DAY_OF_WEEK);
        return DAYS[num-1];
    }
    
}
