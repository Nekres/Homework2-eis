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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author nrs
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:*/custom-dao-context.xml","classpath:*/service-context.xml"})
public class ParseWeekDayIntegrationTest implements ApplicationContextAware{
    @Qualifier("rusDateParser")
    @Autowired
    DateParser parser;
    @Qualifier("dayChecker")
    @Autowired
    WeekDayChecker dayChecker;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test() throws ParseException, ParseException {

        String result = dayChecker.parseWeek("18-03-2018");
        String expectedResult = "Sunday";
        Assert.assertEquals(result, expectedResult);

    }

    @Test
    public void testNull() throws ParseException {
        thrown.expect(NullPointerException.class);
        String result = dayChecker.parseWeek(null);
        String expectedResult = "Sunday";
        Assert.assertEquals(result, expectedResult);
    }

    @Test
    public void testBadDate() throws ParseException {
        thrown.expect(ParseException.class);
        String result = dayChecker.parseWeek("18/03/2018");
        String expectedResult = "Sunday";
        Assert.assertEquals(result, expectedResult);
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
    }
}
