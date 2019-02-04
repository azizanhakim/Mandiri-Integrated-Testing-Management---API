package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class ConditionDeviceDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ConditionDeviceRequestNewDTO {
        @NotNull
        private String condition_status;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ConditionDeviceRequestUpdateDTO {
        @NotNull
        private String condition_id;
        @NotNull
        private String condition_status;
    }

}
