/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.tests.service;

import com.nrs.dao.BaseDateParser;
import com.nrs.service.WeekDayChecker;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.when;
import org.mockito.Mockito;

/**
 *
 * @author nrs
 */
public class WeekDayCheckerTest {
    BaseDateParser parser = Mockito.mock(BaseDateParser.class);
    
    @Test
    public void testParseWeek() throws ParseException{
        WeekDayChecker dayChecker = new WeekDayChecker(parser);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        
        when(parser.getWeek(format.parse("18-03-2018"))).thenReturn("Sunday");
        final String sundayResult = dayChecker.parseWeek("18-03-2018");
        Assert.assertEquals("Sunday",sundayResult);
        
        when(parser.getWeek(format.parse("19-03-2018"))).thenReturn("Monday");
        final String mondayResult = dayChecker.parseWeek("19-03-2018");
        Assert.assertEquals("Monday", mondayResult);
        
        when(parser.getWeek(format.parse("20-03-2018"))).thenReturn("Tuesday");
        final String tuesdayResult = dayChecker.parseWeek("20-03-2018");
        Assert.assertEquals("Tuesday", tuesdayResult);
        
        when(parser.getWeek(format.parse("21-03-2018"))).thenReturn("Wednesday");
        final String wednesdayResult = dayChecker.parseWeek("21-03-2018");
        Assert.assertEquals("Wednesday", wednesdayResult);
        
        when(parser.getWeek(format.parse("22-03-2018"))).thenReturn("Thursday");
        final String thursdayResult = dayChecker.parseWeek("22-03-2018");
        Assert.assertEquals("Thursday", thursdayResult);
        
        when(parser.getWeek(format.parse("23-03-2018"))).thenReturn("Friday");
        final String fridayResult = dayChecker.parseWeek("23-03-2018");
        Assert.assertEquals("Friday", fridayResult);
        
        when(parser.getWeek(format.parse("24-03-2018"))).thenReturn("Saturday");
        final String saturdayResult = dayChecker.parseWeek("24-03-2018");
        Assert.assertEquals("Saturday", saturdayResult);
        
    }
    @Test(expected = ParseException.class)
    public void testParseWeekOnParseException() throws ParseException{
        WeekDayChecker dayChecker = new WeekDayChecker(parser);
        dayChecker.parseWeek("20/9/2019");
        
    }
    @Test(expected = NullPointerException.class)
    public void testParseWeekOnNull() throws ParseException{
        WeekDayChecker dayChecker = new WeekDayChecker(parser);
        dayChecker.parseWeek(null);
    }
    
    
}
