package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.StatusDevice;
import id.co.mandiri.repository.StatusDeviceRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusDeviceDao implements DaoCrudDataTablesPattern<StatusDevice, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private StatusDeviceRepository statusDeviceRepository;

    @Override
    public StatusDevice findId(String s) {
        return statusDeviceRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<StatusDevice> findAll() {
        return null;
    }

    @Override
    public StatusDevice save(StatusDevice statusDevice) {
        return statusDeviceRepository.save(statusDevice);
    }

    @Override
    public StatusDevice update(StatusDevice statusDevice) {
        return statusDeviceRepository.save(statusDevice);
    }

    @Override
    public boolean remove(StatusDevice statusDevice) {
        statusDeviceRepository.delete(statusDevice);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        statusDeviceRepository.delete(s);
        return true;
    }

    @Override
    public List<StatusDevice> datatables(DataTablesRequest<StatusDevice> params) {
        String baseQuery = "select peminjaman_id, peminjaman_status\n" +
                "from device_peminjaman\n" +
                "where 1 = 1 ";

        StatusDevice param = params.getValue();

        StatusDeviceQueryCompare compare = new StatusDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        switch (params.getColOrder().intValue()) {
            case 0:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by peminjaman_id asc ");
                else
                    query.append(" order by peminjaman_id desc ");
                break;
            case 1:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by peminjaman_status asc ");
                else
                    query.append(" order by peminjaman_status desc ");
                break;
            default:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by peminjaman_id asc ");
                else
                    query.append(" order by peminjaman_id desc ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

        return this.jdbcTemplate.query(query.toString(), values, (resultSet, i) ->
                new StatusDevice(
                        resultSet.getString("peminjaman_id"),
                        resultSet.getString("peminjaman_status")
                ));
    }

    @Override
    public Long datatables(StatusDevice param) {
        String baseQuery = "select count(peminjaman_id) as rows \n" +
                "from device_peminjaman\n" +
                "where 1 = 1 ";

        StatusDeviceQueryCompare compare = new StatusDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class StatusDeviceQueryCompare implements QueryComparator<StatusDevice> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        StatusDeviceQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(StatusDevice param) {
            if (StringUtils.isNoneBlank(param.getPeminjaman_id())) {
                query.append(" and lower(peminjaman_id) like :peminjaman_id ");
                parameterSource.addValue("peminjaman_id",
                        new StringBuilder("%")
                                .append(param.getPeminjaman_id().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getPeminjaman_status())) {
                query.append(" and lower(peminjaman_status) like :peminjaman_status ");
                parameterSource.addValue("peminjaman_status", new StringBuilder("%")
                        .append(param.getPeminjaman_status().toLowerCase())
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
