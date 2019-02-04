package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.ColorDevice;
import id.co.mandiri.repository.ColorDeviceRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ColorDeviceDao implements DaoCrudDataTablesPattern<ColorDevice, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private ColorDeviceRepository colorDeviceRepository;

    @Override
    public ColorDevice findId(String s) {
        return colorDeviceRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<ColorDevice> findAll() {
        return null;
    }

    @Override
    public ColorDevice save(ColorDevice colorDevice) {
        return colorDeviceRepository.save(colorDevice);
    }

    @Override
    public ColorDevice update(ColorDevice colorDevice) {
        return colorDeviceRepository.save(colorDevice);
    }

    @Override
    public boolean remove(ColorDevice colorDevice) {
        colorDeviceRepository.delete(colorDevice);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        colorDeviceRepository.delete(s);
        return true;
    }

    @Override
    public List<ColorDevice> datatables(DataTablesRequest<ColorDevice> params) {
        String baseQuery = "select color_id, color_name, color_code, color_description\n" +
                "from device_color\n" +
                "where 1 = 1 ";

        ColorDevice param = params.getValue();

        ColorDeviceQueryCompare compare = new ColorDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        switch (params.getColOrder().intValue()) {
            case 0:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by color_id asc ");
                else
                    query.append(" order by color_id desc ");
                break;
            case 1:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by color_name asc ");
                else
                    query.append(" order by color_name desc ");
                break;
            case 2:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by color_description asc ");
                else
                    query.append(" order by color_description desc ");
                break;
            default:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by color_id asc ");
                else
                    query.append(" order by color_id desc ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

        return this.jdbcTemplate.query(query.toString(), values, (resultSet, i) ->
                new ColorDevice(
                        resultSet.getString("color_id"),
                        resultSet.getString("color_name"),
                        resultSet.getString("color_code"),
                        resultSet.getString("color_description")
                ));
    }

    @Override
    public Long datatables(ColorDevice param) {
        String baseQuery = "select count(color_id) as rows \n" +
                "from device_color\n" +
                "where 1 = 1 ";

        ColorDeviceQueryCompare compare = new ColorDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class ColorDeviceQueryCompare implements QueryComparator<ColorDevice> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        ColorDeviceQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(ColorDevice param) {
            if (StringUtils.isNoneBlank(param.getColor_id())) {
                query.append(" and lower(color_id) like :color_id ");
                parameterSource.addValue("color_id",
                        new StringBuilder("%")
                                .append(param.getColor_id().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getColor_name())) {
                query.append(" and lower(color_name) like :color_name ");
                parameterSource.addValue("color_name", new StringBuilder("%")
                        .append(param.getColor_name().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getColor_description())) {
                query.append(" and lower(color_description) like :color_description ");
                parameterSource.addValue("color_description", new StringBuilder("%")
                        .append(param.getColor_description().toLowerCase())
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
