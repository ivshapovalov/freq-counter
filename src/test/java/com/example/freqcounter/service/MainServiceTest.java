package com.example.freqcounter.service;

import com.example.freqcounter.dto.Request;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainServiceTest {

    private final MainService mainService = new MainService();

    @Test
    public void countFrequencyWhenTextNotEmpty() {
        Request request = new Request("eeeeeaabbceecdddd");

        Map<String, Long> actual = mainService.countFrequency(request);
        Map<String, Long> expected = Map.ofEntries(
                Map.entry("e", 7L),
                Map.entry("d", 4L),
                Map.entry("a", 2L),
                Map.entry("b", 2L),
                Map.entry("c", 2L));

        assertEquals(expected, actual);
    }

    @Test
    public void countFrequencyWhenTextContainsOneSymbol() {
        Request request = new Request("bbbbb");

        Map<String, Long> actual = mainService.countFrequency(request);
        Map<String, Long> expected = Map.ofEntries(
                Map.entry("b", 5L));

        assertEquals(expected, actual);
    }

    @Test
    public void countFrequencyWhenTextContainsTwoSymbolsWithSameFrequency() {
        Request request = new Request("aabbaabbab");

        Map<String, Long> actual = mainService.countFrequency(request);
        Map<String, Long> expected = Map.ofEntries(
                Map.entry("a", 5L),
                Map.entry("b", 5L));

        assertEquals(expected, actual);
    }

    @Test
    public void countFrequencyWhenTextIsEmpty() {
        Request request = new Request("");

        Map<String, Long> actual = mainService.countFrequency(request);
        Map<String, Long> expected = new HashMap<>();
        assertEquals(expected, actual);
    }

    @Test
    public void countFrequencyWhenTextIsNull() {
        Request request = new Request(null);

        Map<String, Long> actual = mainService.countFrequency(request);
        Map<String, Long> expected = new HashMap<>();
        assertEquals(expected, actual);
    }


}
