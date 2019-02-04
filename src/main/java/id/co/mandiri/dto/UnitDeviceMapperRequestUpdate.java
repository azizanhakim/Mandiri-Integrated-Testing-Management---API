package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.UnitDevice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UnitDeviceMapperRequestUpdate extends ObjectMapper<UnitDevice, UnitDeviceDTO.UnitDeviceRequestUpdateDTO> {

    UnitDeviceMapperRequestUpdate converter = Mappers.getMapper(UnitDeviceMapperRequestUpdate.class);
}
