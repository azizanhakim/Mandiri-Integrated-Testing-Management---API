package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class StatusDeviceDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StatusDeviceRequestNewDTO {
        @NotNull
        private String peminjaman_status;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StatusDeviceRequestUpdateDTO {
        @NotNull
        private String peminjaman_id;
        @NotNull
        private String peminjaman_status;
    }

}
