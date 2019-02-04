package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.UnitDevice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UnitDeviceMapperRequestNew extends ObjectMapper<UnitDevice, UnitDeviceDTO.UnitDeviceRequestNewDTO> {

    UnitDeviceMapperRequestNew converter = Mappers.getMapper(UnitDeviceMapperRequestNew.class);

}
