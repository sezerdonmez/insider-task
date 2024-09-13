package com.insider.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    DOG("DOG", 101),
    CAT("CAT", 102),
    BIRD("BIRD", 103),
    FROG("FROG", 104);

    private final String name;
    private final Integer id;
}
