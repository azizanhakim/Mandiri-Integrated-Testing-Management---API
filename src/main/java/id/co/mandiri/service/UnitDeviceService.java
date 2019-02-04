package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.UnitDeviceDao;
import id.co.mandiri.entity.UnitDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UnitDeviceService implements ServiceCrudDataTablesPattern<UnitDevice, String> {

    @Autowired
    private UnitDeviceDao unitDao;

    @Override
    public UnitDevice findId(String s) {
        return unitDao.findId(s);
    }

    @Override
    public List<UnitDevice> findAll() {
        return null;
    }

    @Override
    @Transactional
    public UnitDevice save(UnitDevice value) {
        return unitDao.save(value);
    }

    @Override
    @Transactional
    public UnitDevice update(UnitDevice value) {
        return unitDao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(UnitDevice value) {
        return unitDao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return unitDao.removeById(s);
    }

    @Override
    public DataTablesResponse<UnitDevice> datatables(DataTablesRequest<UnitDevice> params) {
        List<UnitDevice> values = unitDao.datatables(params);
        Long rowCount = unitDao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
