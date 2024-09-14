package com.insider.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Location {

    ALL("All", ""),
    TURKEY_ISTANBUL("Istanbul, Turkey", "Istanbul"),
    AUSTRALIA("Sydney, Australia", "Sydney"),
    COLOMBIA("Bogota, Colombia", "Bogota"),
    FRANCE("Paris, France", "Paris"),
    BRAZIL("Sao Paulo, Brazil", "Sao Paulo"),
    MEXICO("Mexico City, Mexico", "Mexico City"),
    THAILAND("Bangkok, Thailand", "Bangkok"),
    HOLLAND("Amsterdam, Holland", "Amsterdam"),
    UNITED_KINGDOM("London, United Kingdom", "London"),
    VIETNAM_MINH("Ho Chi Minh City, Vietnam", "Ho Chi Minh City"),
    INDONESIA("Jakarta, Indonesia", "Jakarta"),
    CHILE("Chile, Chile", "Santiago"),
    SPAIN_MADRID("Madrid, Spain", "Madrid"),
    SPAIN_BARCELONA("Madrid, Spain", "Barcelona"),
    SOUTH_KOREA("Seoul,  South Korea", ""),
    USA("United States", "New York"),
    ARGENTINA("Argentina, Argentina"," Buenos Aires"),
    GERMANY("Berlin, Germany", "Berlin"),
    PHILIPPINES("Manila, Philippines", "Manila"),
    TAIPEI("Taipei", "Taipei"),
    VIETNAM_HANOI("Hanoi, Vietnam", "Hanoi"),
    TURKEY("Turkey", ""),
    MALAYSIA("Kuala Lumpur, Malaysia", "Kuala Lumpur"),
    POLAND("Warsaw, Poland", "Warsaw"),
    INDIA("India, India","");

    private final String name;
    private final String city;
}
