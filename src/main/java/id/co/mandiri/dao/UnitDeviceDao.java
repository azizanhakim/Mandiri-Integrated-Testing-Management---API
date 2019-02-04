package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.UnitDevice;
import id.co.mandiri.repository.UnitDeviceRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UnitDeviceDao implements DaoCrudDataTablesPattern<UnitDevice, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private UnitDeviceRepository unitDeviceRepository;

    @Override
    public UnitDevice findId(String s) {
        return unitDeviceRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<UnitDevice> findAll() {
        return null;
    }

    @Override
    public UnitDevice save(UnitDevice unitDevice) {
        return unitDeviceRepository.save(unitDevice);
    }

    @Override
    public UnitDevice update(UnitDevice unitDevice) {
        return unitDeviceRepository.save(unitDevice);
    }

    @Override
    public boolean remove(UnitDevice unitDevice) {
        unitDeviceRepository.delete(unitDevice);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        unitDeviceRepository.delete(s);
        return true;
    }

    @Override
    public List<UnitDevice> datatables(DataTablesRequest<UnitDevice> params) {
        String baseQuery = "select unit_id, unit_name\n" +
                "from device_unit\n" +
                "where 1 = 1 ";

        UnitDevice param = params.getValue();

        UnitDeviceQueryCompare compare = new UnitDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        switch (params.getColOrder().intValue()) {
            case 0:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by unit_id asc ");
                else
                    query.append(" order by unit_id desc ");
                break;
            case 1:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by unit_name asc ");
                else
                    query.append(" order by unit_name desc ");
                break;
            default:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by unit_id asc ");
                else
                    query.append(" order by unit_id desc ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

        return this.jdbcTemplate.query(query.toString(), values, (resultSet, i) ->
                new UnitDevice(
                        resultSet.getString("unit_id"),
                        resultSet.getString("unit_name")
                ));
    }

    @Override
    public Long datatables(UnitDevice param) {
        String baseQuery = "select count(unit_id) as rows \n" +
                "from device_unit\n" +
                "where 1 = 1 ";

        UnitDeviceQueryCompare compare = new UnitDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class UnitDeviceQueryCompare implements QueryComparator<UnitDevice> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        UnitDeviceQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(UnitDevice param) {
            if (StringUtils.isNoneBlank(param.getUnit_id())) {
                query.append(" and lower(unit_id) like :unit_id ");
                parameterSource.addValue("unit_id",
                        new StringBuilder("%")
                                .append(param.getUnit_id().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getUnit_name())) {
                query.append(" and lower(unit_name) like :unit_name ");
                parameterSource.addValue("unit_name", new StringBuilder("%")
                        .append(param.getUnit_name().toLowerCase())
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
