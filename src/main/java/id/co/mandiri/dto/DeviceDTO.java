package id.co.mandiri.dto;

import id.co.mandiri.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class DeviceDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceRequestNewDTO {
        @NotNull
        private String name;
        @NotNull
        private CategoryDevice categoryDevice;
        @NotNull
        private ColorDevice colorDevice;
        @NotNull
        private BrandDevice brandDevice;
        @NotNull
        private ConditionDevice conditionDevice;
        @NotNull
        private UnitDevice unitDevice;
        @NotNull
        private StatusDevice statusDevice;

//        @NotNull
//        private String category_id;
//        @NotNull
//        private String color_id;
//        @NotNull
//        private String brand_id;
//        @NotNull
//        private String condition_id;
//        @NotNull
//        private String unit_id;
//        @NotNull
//        private String peminjaman_id;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceRequestUpdateDTO {
        @NotNull
        private String id;
        @NotNull
        private String name;
        @NotNull
        private CategoryDevice categoryDevice;
        @NotNull
        private ColorDevice colorDevice;
        @NotNull
        private BrandDevice brandDevice;
        @NotNull
        private ConditionDevice conditionDevice;
        @NotNull
        private UnitDevice unitDevice;
        @NotNull
        private StatusDevice statusDevice;

//        @NotNull
//        private String category_id;
//        @NotNull
//        private String color_id;
//        @NotNull
//        private String brand_id;
//        @NotNull
//        private String condition_id;
//        @NotNull
//        private String unit_id;
//        @NotNull
//        private String peminjaman_id;
    }

}
