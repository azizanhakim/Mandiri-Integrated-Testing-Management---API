package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class BrandDeviceDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BrandDeviceRequestNewDTO {
        @NotNull
        private String name;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BrandDeviceRequestUpdateDTO {
        @NotNull
        private String id;
        @NotNull
        private String name;
        private String description;
    }

}
