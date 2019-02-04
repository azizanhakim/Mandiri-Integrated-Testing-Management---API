package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.StatusDeviceDao;
import id.co.mandiri.entity.StatusDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StatusDeviceService implements ServiceCrudDataTablesPattern<StatusDevice, String> {

    @Autowired
    private StatusDeviceDao statusDao;

    @Override
    public StatusDevice findId(String s) {
        return statusDao.findId(s);
    }

    @Override
    public List<StatusDevice> findAll() {
        return null;
    }

    @Override
    @Transactional
    public StatusDevice save(StatusDevice value) {
        return statusDao.save(value);
    }

    @Override
    @Transactional
    public StatusDevice update(StatusDevice value) {
        return statusDao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(StatusDevice value) {
        return statusDao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return statusDao.removeById(s);
    }

    @Override
    public DataTablesResponse<StatusDevice> datatables(DataTablesRequest<StatusDevice> params) {
        List<StatusDevice> values = statusDao.datatables(params);
        Long rowCount = statusDao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
