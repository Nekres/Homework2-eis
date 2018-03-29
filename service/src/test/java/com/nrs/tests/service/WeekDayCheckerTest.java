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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author nrs
 */
@RunWith(MockitoJUnitRunner.class)
public class WeekDayCheckerTest {
    
    @Mock
    BaseDateParser parser; 
    @Rule
    public ExpectedException thrown= ExpectedException.none();
    
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
    @Test
    public void testParseWeekOnParseException() throws ParseException{
        thrown.expect(ParseException.class);
        WeekDayChecker dayChecker = new WeekDayChecker(parser);
        dayChecker.parseWeek("20/9/2019");
        
    }
    @Test
    public void testParseWeekOnNull() throws ParseException{
        thrown.expect(NullPointerException.class);
        WeekDayChecker dayChecker = new WeekDayChecker(parser);
        dayChecker.parseWeek(null);
    }
    
    
}
