package com.example.freqcounter.service;

import com.example.freqcounter.dto.Request;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MainService {

    public Map<String, Long> countFrequency(Request request) {
        String text = request.getText();
        if (text == null || text.isEmpty()) {
            return new HashMap<>();
        }
        return Arrays.stream(text.split(""))
                .filter(symbol -> !symbol.isEmpty())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted((entry1, entry2) -> {
                    if (entry2.getValue().equals(entry1.getValue())) {
                        return entry1.getKey().compareTo(entry2.getKey());
                    }
                    return entry2.getValue().compareTo(entry1.getValue());
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
    }

}
