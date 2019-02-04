package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class CategoryDeviceDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoriDeviceRequestNewDTO {
        @NotNull
        private String category_name;
        private String category_description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryDeviceRequestUpdateDTO {
        @NotNull
        private String category_id;
        @NotNull
        private String category_name;
        private String category_description;
    }

}
