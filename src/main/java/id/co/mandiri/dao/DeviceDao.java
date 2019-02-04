package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.*;
import id.co.mandiri.repository.DeviceRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DeviceDao implements DaoCrudDataTablesPattern<Device, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device findId(String s) {
        return deviceRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<Device> findAll() {
        return null;
    }

    @Override
    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Device update(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public boolean remove(Device device) {
        deviceRepository.delete(device);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        deviceRepository.delete(s);
        return true;
    }

    @Override
    public List<Device> datatables(DataTablesRequest<Device> params) {
        String baseQuery = "select device.device_id, device.device_name, device_category.c , device_color.color_name, device_brand.brand_name, device_condition.condition_status, device_unit.unit_name, device_peminjaman.peminjaman_status FROM device,device_category, device_color, device_brand, device_condition, device_unit, device_peminjaman WHERE device.category_id = device_category.category_id AND device.color_id = device_color.color_id AND device.brand_id = device_brand.brand_id AND device.condition_id = device_condition.condition_id AND device.unit_id = device_unit.unit_id AND device.peminjaman_id = device_peminjaman.peminjaman_id";

        Device param = params.getValue();

        DeviceQueryCompare compare = new DeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        switch (params.getColOrder().intValue()) {
            case 0:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by device_id asc ");
                else
                    query.append(" order by device_id desc ");
                break;
            case 1:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by device_name asc ");
                else
                    query.append(" order by device_name desc ");
                break;
            default:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by device_id asc ");
                else
                    query.append(" order by device_id desc ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

        return this.jdbcTemplate.query(query.toString(), values, new RowMapper<Device>() {
                    @Override
                    public Device mapRow(ResultSet resultSet, int i) throws SQLException {
                        ColorDevice color = (new BeanPropertyRowMapper<>(ColorDevice.class)).mapRow(resultSet, i);
                        StatusDevice peminjaman = (new BeanPropertyRowMapper<>(StatusDevice.class)).mapRow(resultSet, i);
                        ConditionDevice condition = (new BeanPropertyRowMapper<>(ConditionDevice.class)).mapRow(resultSet, i);
                        CategoryDevice category = (new BeanPropertyRowMapper<>(CategoryDevice.class)).mapRow(resultSet, i);
                        UnitDevice unit = (new BeanPropertyRowMapper<>(UnitDevice.class)).mapRow(resultSet, i);
                        BrandDevice brand = (new BeanPropertyRowMapper<>(BrandDevice.class)).mapRow(resultSet, i);
                        Device device = (new BeanPropertyRowMapper<>(Device.class)).mapRow(resultSet, i);
                        device.setColorDevice(color);
                        device.setBrandDevice(brand);
                        device.setStatusDevice(peminjaman);
                        device.setConditionDevice(condition);
                        device.setCategoryDevice(category);
                        device.setUnitDevice(unit);

                        return device;
                    }
                }
        );
    }

    @Override
    public Long datatables(Device param) {
        String baseQuery = "select count(device_id) as rows \n" +
                "from device\n" +
                "where 1 = 1 ";

        DeviceQueryCompare compare = new DeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class DeviceQueryCompare implements QueryComparator<Device> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        DeviceQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(Device param) {
            if (StringUtils.isNoneBlank(param.getDevice_id())) {
                query.append(" and lower(id) like :id ");
                parameterSource.addValue("id",
                        new StringBuilder("%")
                                .append(param.getDevice_id().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getDevice_name())) {
                query.append(" and lower(name) like :name ");
                parameterSource.addValue("name", new StringBuilder("%")
                        .append(param.getDevice_name().toLowerCase())
                        .append("%")
                        .toString());
            }

            return query;
        }

        @Override
        public MapSqlParameterSource getParameters() {
            return this.parameterSource;
        }
    }
}
