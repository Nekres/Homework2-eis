/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.test.dao;

import com.nrs.dao.DateParser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author nrs
 */
public class DateParserTest {
    @Rule
    public ExpectedException thrown= ExpectedException.none();
    @Test
    public void testGetWeek() throws ParseException{
        final DateParser parser = new DateParser();
        final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        final Date sunday = format.parse("18-03-2018");
        
        Assert.assertEquals(parser.getWeek(sunday),"Sunday");
        
        final Date monday = format.parse("19-03-2018");
        Assert.assertEquals(parser.getWeek(monday), "Monday");
        
        final Date tuesday = format.parse("20-03-2018");
        Assert.assertEquals(parser.getWeek(tuesday), "Tuesday");
        final Date wednesday = format.parse("21-03-2018");
        Assert.assertEquals(parser.getWeek(wednesday), "Wednesday");
        
        final Date thursday = format.parse("22-03-2018");
        Assert.assertEquals(parser.getWeek(thursday), "Thursday");
        
        final Date friday = format.parse("23-03-2018");
        Assert.assertEquals(parser.getWeek(friday), "Friday");
        
        final Date saturday = format.parse("24-03-2018");
        Assert.assertEquals(parser.getWeek(saturday), "Saturday");
        
    }
    public void testGetWeekOnNull(){
        thrown.expect(NullPointerException.class);
        DateParser parser = new DateParser();
        parser.getWeek(null);
    }
}
