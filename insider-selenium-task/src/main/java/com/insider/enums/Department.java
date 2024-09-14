package com.insider.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Department {

    BUSINESS_DEVELOPMENT("Business Development"),
    CUSTOMER_EDUCATION("Customer Education"),
    CUSTOMER_SUCCESS("Customer Success"),
    FINANCE_SUPPORT("Finance & Business Support"),
    HUMAN_RESOURCES("Human Resources"),
    MARKETING_COMMUNICATION("Marketing and Communications"),
    MARKETING_DESIGN("Marketing Design"),
    MIND("MindBehind"),
    MOBILE_DEV("Mobile Development"),
    PRODUCT_MANAGEMENT("Product Management"),
    SALES("Sales"),
    SALES_OPERATIONS("Sales Operations"),
    SECURITY("Security"),
    SOFTWARE_DEVELOPMENT("Software Development"),
    QUALITY_ASSURANCE("Quality Assurance");

    private final String name;
}
