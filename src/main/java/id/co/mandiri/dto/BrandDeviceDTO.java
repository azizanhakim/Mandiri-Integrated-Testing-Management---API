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
        private String brand_name;
        private String brand_description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BrandDeviceRequestUpdateDTO {
        @NotNull
        private String brand_id;
        @NotNull
        private String brand_name;
        private String brand_description;
    }

}
