/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.test.it;

import com.nrs.dao.DateParser;
import com.nrs.service.WeekDayChecker;
import java.text.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 * @author nrs
 */
public class ParseWeekDayIntegrationTest {
    
    @Test
    public void test() throws ParseException, ParseException{
         DateParser parser = new DateParser();
         WeekDayChecker dayChecker = new WeekDayChecker(parser);
         String result = dayChecker.parseWeek("18-03-2018");
         String expectedResult = "Sunday";
         Assert.assertEquals(result,expectedResult);
         
         
    }
    
    @Test(expected = NullPointerException.class)
    public void testNull() throws ParseException{
         DateParser parser = new DateParser();
         WeekDayChecker dayChecker = new WeekDayChecker(parser);
         String result = dayChecker.parseWeek(null);
         String expectedResult = "Sunday";
         Assert.assertEquals(result,expectedResult);
    }
    @Test(expected = ParseException.class)
    public void testBadDate() throws ParseException{
         DateParser parser = new DateParser();
         WeekDayChecker dayChecker = new WeekDayChecker(parser);
         String result = dayChecker.parseWeek("18/03/2018");
         String expectedResult = "Sunday";
         Assert.assertEquals(result,expectedResult);
    }
}
