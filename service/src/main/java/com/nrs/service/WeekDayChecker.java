/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.service;
import com.nrs.dao.DateParser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author root
 */
public class WeekDayChecker {
    public static final String FORMAT = "dd-MM-yyyy";
    private static final Logger logger = LoggerFactory.getLogger(WeekDayChecker.class);
    
    public String parseWeek(final String input) throws ParseException{
        logger.info("parseWeek input: " + input);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
        dateFormat.setLenient(false); 
        final Date resultDate = dateFormat.parse(input);
        final DateParser parser = new DateParser();
        return parser.getWeek(resultDate);
    }
}