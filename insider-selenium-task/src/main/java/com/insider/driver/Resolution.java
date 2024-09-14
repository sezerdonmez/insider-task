package com.insider.driver;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Resolution {

    FOUR_K_HD(3840, 2160),
    Q_HD(2560, 1440),
    FULL_HD(1920, 1080),
    LAPTOP(1440, 900),
    WIDE_LAPTOP(1600, 900),
    OLD_MONITOR(1280, 1024);

    private final Integer width;
    private final Integer height;
}
