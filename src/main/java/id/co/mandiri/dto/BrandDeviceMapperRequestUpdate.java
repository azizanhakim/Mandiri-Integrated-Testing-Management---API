package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.BrandDevice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandDeviceMapperRequestUpdate extends ObjectMapper<BrandDevice, BrandDeviceDTO.BrandDeviceRequestUpdateDTO> {

    BrandDeviceMapperRequestUpdate converter = Mappers.getMapper(BrandDeviceMapperRequestUpdate.class);
}
