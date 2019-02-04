package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.ConditionDevice;
import id.co.mandiri.repository.ConditionDeviceRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConditionDeviceDao implements DaoCrudDataTablesPattern<ConditionDevice, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private ConditionDeviceRepository conditionDeviceRepository;

    @Override
    public ConditionDevice findId(String s) {
        return conditionDeviceRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<ConditionDevice> findAll() {
        return null;
    }

    @Override
    public ConditionDevice save(ConditionDevice conditionDevice) {
        return conditionDeviceRepository.save(conditionDevice);
    }

    @Override
    public ConditionDevice update(ConditionDevice conditionDevice) {
        return conditionDeviceRepository.save(conditionDevice);
    }

    @Override
    public boolean remove(ConditionDevice conditionDevice) {
        conditionDeviceRepository.delete(conditionDevice);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        conditionDeviceRepository.delete(s);
        return true;
    }

    @Override
    public List<ConditionDevice> datatables(DataTablesRequest<ConditionDevice> params) {
        String baseQuery = "select condition_id, condition_status\n" +
                "from device_condition\n" +
                "where 1 = 1 ";

        ConditionDevice param = params.getValue();

        ConditionDeviceQueryCompare compare = new ConditionDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        switch (params.getColOrder().intValue()) {
            case 0:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by condition_id asc ");
                else
                    query.append(" order by id desc ");
                break;
            case 1:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by condition_status asc ");
                else
                    query.append(" order by condition_status desc ");
                break;
            default:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by condition_id asc ");
                else
                    query.append(" order by condition_id desc ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

        return this.jdbcTemplate.query(query.toString(), values, (resultSet, i) ->
                new ConditionDevice(
                        resultSet.getString("condition_id"),
                        resultSet.getString("condition_status")
                ));
    }

    @Override
    public Long datatables(ConditionDevice param) {
        String baseQuery = "select count(condition_id) as rows \n" +
                "from device_condition\n" +
                "where 1 = 1 ";

        ConditionDeviceQueryCompare compare = new ConditionDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class ConditionDeviceQueryCompare implements QueryComparator<ConditionDevice> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        ConditionDeviceQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(ConditionDevice param) {
            if (StringUtils.isNoneBlank(param.getCondition_id())) {
                query.append(" and lower(condition_id) like :condition_id ");
                parameterSource.addValue("condition_id",
                        new StringBuilder("%")
                                .append(param.getCondition_id().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getCondition_status())) {
                query.append(" and lower(condition_status) like :condition_status ");
                parameterSource.addValue("condition_status", new StringBuilder("%")
                        .append(param.getCondition_status().toLowerCase())
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
