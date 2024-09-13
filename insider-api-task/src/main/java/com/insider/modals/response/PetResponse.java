package com.insider.modals.response;

import lombok.Data;

import java.util.List;

@Data
public class PetResponse {

    private Long id;
    private Category category;
    private List<Tag> tags;
    private String name;
    private List<String> photoUrls;
    private String status;

    @Data
    public static class Category {
        private Long id;
        private String name;
    }

    @Data
    public static class Tag {
        private Long id;
        private String name;
    }
}
