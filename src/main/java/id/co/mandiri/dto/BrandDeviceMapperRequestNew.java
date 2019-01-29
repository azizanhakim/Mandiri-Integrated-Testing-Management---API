package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.BrandDevice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandDeviceMapperRequestNew extends ObjectMapper<BrandDevice, BrandDeviceDTO.BrandDeviceRequestNewDTO> {

    BrandDeviceMapperRequestNew converter = Mappers.getMapper(BrandDeviceMapperRequestNew.class);

}
