/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.service;

import com.nrs.cacheable.Cacheable;
import java.text.ParseException;

/**
 *
 * @author nrs
 */
public interface BaseWeekDayChecker {
    @Cacheable
    public String parseWeek(String input) throws ParseException;
}
