package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class ColorDeviceDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ColorDeviceRequestNewDTO {
        @NotNull
        private String color_name;
        private String color_code;
        private String color_description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ColorDeviceRequestUpdateDTO {
        @NotNull
        private String color_id;
        @NotNull
        private String color_name;
        private String color_code;
        private String color_description;
    }

}
