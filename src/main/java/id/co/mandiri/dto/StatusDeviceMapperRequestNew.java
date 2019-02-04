package id.co.mandiri.dto;

import com.maryanto.dimas.plugins.web.commons.mappers.ObjectMapper;
import id.co.mandiri.entity.StatusDevice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatusDeviceMapperRequestNew extends ObjectMapper<StatusDevice, StatusDeviceDTO.StatusDeviceRequestNewDTO> {

    StatusDeviceMapperRequestNew converter = Mappers.getMapper(StatusDeviceMapperRequestNew.class);

}
