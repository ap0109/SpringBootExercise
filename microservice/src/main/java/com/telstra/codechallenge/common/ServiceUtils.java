package com.telstra.codechallenge.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ServiceUtils {

    private static final ZoneId ZONE_ID =  ZoneId.of("Australia/Sydney");

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate getDateNow(){
        return LocalDate.now(ZONE_ID).minus(Period.ofDays(7));
    }

    public static String getLocalDateWeekBefore(){
        return FORMATTER.format(getDateNow());
    }

}
