package com.example.freqcounter.controller;

import com.example.freqcounter.dto.Request;
import com.example.freqcounter.service.MainService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Validated
@RestController
public class MainController {
    private final MainService mainService;

    public MainController(final MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/",
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    ResponseEntity<Map<String, Long>> countFrequency(
            @NotNull @Valid @RequestBody Request request
    ) {
        return ResponseEntity.ok(mainService.countFrequency(request));
    }
}
