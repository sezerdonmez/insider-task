package com.insider.modals.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PetRequest {

    private Integer id;
    private Category category;
    private List<Tag> tags;
    private String name;
    private List<String> photoUrls;
    private String status;

    @Data
    @Builder
    public static class Category {
        private Integer id;
        private String name;
    }

    @Data
    @Builder
    public static class Tag {
        private Integer id;
        private String name;
    }
}
