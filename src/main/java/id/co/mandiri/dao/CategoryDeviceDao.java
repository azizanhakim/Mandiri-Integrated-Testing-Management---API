package id.co.mandiri.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.dao.DaoCrudDataTablesPattern;
import id.co.mandiri.entity.CategoryDevice;
import id.co.mandiri.repository.CategoryDeviceRepository;
import id.co.mandiri.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDeviceDao implements DaoCrudDataTablesPattern<CategoryDevice, String> {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private CategoryDeviceRepository categoryDeviceRepository;

    @Override
    public CategoryDevice findId(String s) {
        return categoryDeviceRepository.findOne(s);
    }

    @Override
    @Deprecated
    public List<CategoryDevice> findAll() {
        return null;
    }

    @Override
    public CategoryDevice save(CategoryDevice categoryDevice) {
        return categoryDeviceRepository.save(categoryDevice);
    }

    @Override
    public CategoryDevice update(CategoryDevice categoryDevice) {
        return categoryDeviceRepository.save(categoryDevice);
    }

    @Override
    public boolean remove(CategoryDevice categoryDevice) {
        categoryDeviceRepository.delete(categoryDevice);
        return true;
    }

    @Override
    public boolean removeById(String s) {
        categoryDeviceRepository.delete(s);
        return true;
    }

    @Override
    public List<CategoryDevice> datatables(DataTablesRequest<CategoryDevice> params) {
        String baseQuery = "select category_id, category_name, category_description\n" +
                "from device_category\n" +
                "where 1 = 1 ";

        CategoryDevice param = params.getValue();

        CategoryDeviceQueryCompare compare = new CategoryDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        switch (params.getColOrder().intValue()) {
            case 0:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by category_id asc ");
                else
                    query.append(" order by category_id desc ");
                break;
            case 1:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by category_name asc ");
                else
                    query.append(" order by category_name desc ");
                break;
            case 2:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by category_description asc ");
                else
                    query.append(" order by category_description desc ");
                break;
            default:
                if (StringUtils.equalsIgnoreCase(params.getColDir(), "asc"))
                    query.append(" order by category_id asc ");
                else
                    query.append(" order by category_id desc ");
                break;
        }

        query.append("limit :limit offset :offset");
        values.addValue("offset", params.getStart());
        values.addValue("limit", params.getLength());

        return this.jdbcTemplate.query(query.toString(), values, (resultSet, i) ->
                new CategoryDevice(
                        resultSet.getString("category_id"),
                        resultSet.getString("category_name"),
                        resultSet.getString("category_description")
                ));
    }

    @Override
    public Long datatables(CategoryDevice param) {
        String baseQuery = "select count(category_id) as rows \n" +
                "from device_category\n" +
                "where 1 = 1 ";

        CategoryDeviceQueryCompare compare = new CategoryDeviceQueryCompare(baseQuery);
        StringBuilder query = compare.getQuery(param);
        MapSqlParameterSource values = compare.getParameters();

        return this.jdbcTemplate.queryForObject(
                query.toString(),
                values,
                (resultSet, i) -> resultSet.getLong("rows")
        );

    }

    private class CategoryDeviceQueryCompare implements QueryComparator<CategoryDevice> {

        private MapSqlParameterSource parameterSource;
        private StringBuilder query;

        CategoryDeviceQueryCompare(String query) {
            this.parameterSource = new MapSqlParameterSource();
            this.query = new StringBuilder(query);
        }


        @Override
        public StringBuilder getQuery(CategoryDevice param) {
            if (StringUtils.isNoneBlank(param.getCategory_id())) {
                query.append(" and lower(category_id) like :category_id ");
                parameterSource.addValue("category_id",
                        new StringBuilder("%")
                                .append(param.getCategory_id().toLowerCase())
                                .append("%")
                                .toString());
            }

            if (StringUtils.isNoneBlank(param.getCategory_name())) {
                query.append(" and lower(category_name) like :category_name ");
                parameterSource.addValue("category_name", new StringBuilder("%")
                        .append(param.getCategory_name().toLowerCase())
                        .append("%")
                        .toString());
            }

            if (StringUtils.isNoneBlank(param.getCategory_description())) {
                query.append(" and lower(category_description) like :category_description ");
                parameterSource.addValue("category_description", new StringBuilder("%")
                        .append(param.getCategory_description().toLowerCase())
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
