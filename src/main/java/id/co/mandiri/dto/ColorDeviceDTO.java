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
        private String name;
        private String code;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ColorDeviceRequestUpdateDTO {
        @NotNull
        private String id;
        @NotNull
        private String name;
        private String code;
        private String description;
    }

}
