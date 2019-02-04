package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.BrandDevice;
import id.co.mandiri.repository.BrandDeviceRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandDeviceDao implements DaoCrudDataTablesPattern<BrandDevice, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private BrandDeviceRepository brandDeviceRepository;

    @Override
    public BrandDevice findId(String s) {
        return brandDeviceRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<BrandDevice> findAll() {
        return null;
    }

    @Override
    public BrandDevice save(BrandDevice brandDevice) {
        return brandDeviceRepository.save(brandDevice);
    }

    @Override
    public BrandDevice update(BrandDevice brandDevice) {
        return brandDeviceRepository.save(brandDevice);
    }

    @Override
    public boolean remove(BrandDevice brandDevice) {
        brandDeviceRepository.delete(brandDevice);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        brandDeviceRepository.delete(s);
        return true;
    }

    @Override
    public List<BrandDevice> datatables(DataTablesRequest<BrandDevice> params) {
        String baseQuery = "select brand_id, brand_name, brand_description\n" +
                "from device_brand\n" +
                "where 1 = 1 ";

        BrandDevice param = params.getValue();

        BrandDeviceQueryCompare compare = new BrandDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        switch (params.getColOrder().intValue()) {
            case 0:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by brand_id asc ");
                else
                    query.append(" order by brand_id desc ");
                break;
            case 1:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by brand_name asc ");
                else
                    query.append(" order by brand_name desc ");
                break;
            case 2:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by brand_description asc ");
                else
                    query.append(" order by brand_description desc ");
                break;
            default:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by brand_id asc ");
                else
                    query.append(" order by brand_id desc ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

        return this.jdbcTemplate.query(query.toString(), values, (resultSet, i) ->
                new BrandDevice(
                        resultSet.getString("brand_id"),
                        resultSet.getString("brand_name"),
                        resultSet.getString("brand_description")
                ));
    }

    @Override
    public Long datatables(BrandDevice param) {
        String baseQuery = "select count(brand_id) as rows \n" +
                "from device_brand\n" +
                "where 1 = 1 ";

        BrandDeviceQueryCompare compare = new BrandDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class BrandDeviceQueryCompare implements QueryComparator<BrandDevice> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        BrandDeviceQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(BrandDevice param) {
            if (StringUtils.isNoneBlank(param.getBrand_id())) {
                query.append(" and lower(brand_id) like :brand_id ");
                parameterSource.addValue("brand_id",
                        new StringBuilder("%")
                                .append(param.getBrand_id().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getBrand_name())) {
                query.append(" and lower(brand_name) like :brand_name ");
                parameterSource.addValue("brand_name", new StringBuilder("%")
                        .append(param.getBrand_name().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getBrand_description())) {
                query.append(" and lower(brand_description) like :brand_description ");
                parameterSource.addValue("brand_description", new StringBuilder("%")
                        .append(param.getBrand_description().toLowerCase())
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
