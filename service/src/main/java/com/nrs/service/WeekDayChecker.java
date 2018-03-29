/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.service;
import com.nrs.dao.BaseDateParser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
/**
 *
 * @author root
 */
public class WeekDayChecker implements BaseWeekDayChecker{
    public static final String FORMAT = "dd-MM-yyyy";
    private static final Logger logger = LoggerFactory.getLogger(WeekDayChecker.class);
    
    @Qualifier("rusDateParser")
    @Autowired
    private BaseDateParser parser;
    
    public WeekDayChecker(final BaseDateParser parser) {
        this.parser = parser;
    }

    public WeekDayChecker() {
    }
    
    @Override
    public String parseWeek(final String input) throws ParseException{
        logger.info("parseWeek input: " + input);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
        dateFormat.setLenient(false); 
        final Date resultDate = dateFormat.parse(input);
        return parser.getWeek(resultDate);
    }

    public void setParser(BaseDateParser parser) {
        this.parser = parser;
    }
    
    
    
}