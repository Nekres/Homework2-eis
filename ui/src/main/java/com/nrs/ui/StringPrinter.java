/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.ui;

import com.nrs.service.WeekDayChecker;
import java.text.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author root
 */
public class StringPrinter {
    
    public static final Logger logger = LoggerFactory.getLogger(StringPrinter.class);
    
    public static void main(String[] args) {
        String argument = null;
        if(args == null || args.length != 1){
            System.err.println("Pass an argument, please. Format must be dd-MM-yyyy");
            return;
        }else{
            argument = args[0];
        }
        
        final WeekDayChecker weekChecker = new WeekDayChecker();
        
        try {
            String result = weekChecker.parseWeek(argument);
            System.out.println("The day of week is " + result);
        } catch (ParseException ex) {
            System.err.println("Wrong date format. Format must be \"dd-MM-yyyy\"");
        }
    }
}