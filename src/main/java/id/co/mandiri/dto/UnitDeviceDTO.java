package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class UnitDeviceDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitDeviceRequestNewDTO {
        @NotNull
        private String unit_name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitDeviceRequestUpdateDTO {
        @NotNull
        private String unit_id;
        @NotNull
        private String unit_name;
    }

}
