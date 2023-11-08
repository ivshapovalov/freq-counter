package com.example.freqcounter.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

public record Request(@Getter @Valid @NotEmpty String text) {
}
