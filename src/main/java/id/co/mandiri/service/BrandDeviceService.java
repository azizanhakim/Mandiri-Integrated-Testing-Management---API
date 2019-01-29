package id.co.mandiri.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.service.ServiceCrudDataTablesPattern;
import id.co.mandiri.dao.BrandDeviceDao;
import id.co.mandiri.entity.BrandDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BrandDeviceService implements ServiceCrudDataTablesPattern<BrandDevice, String> {

    @Autowired
    private BrandDeviceDao brandDao;

    @Override
    public BrandDevice findId(String s) {
        return brandDao.findId(s);
    }

    @Override
    public List<BrandDevice> findAll() {
        return null;
    }

    @Override
    @Transactional
    public BrandDevice save(BrandDevice value) {
        return brandDao.save(value);
    }

    @Override
    @Transactional
    public BrandDevice update(BrandDevice value) {
        return brandDao.update(value);
    }

    @Override
    @Transactional
    public boolean remove(BrandDevice value) {
        return brandDao.remove(value);
    }

    @Override
    @Transactional
    public boolean removeById(String s) {
        return brandDao.removeById(s);
    }

    @Override
    public DataTablesResponse<BrandDevice> datatables(DataTablesRequest<BrandDevice> params) {
        List<BrandDevice> values = brandDao.datatables(params);
        Long rowCount = brandDao.datatables(params.getValue());
        return new DataTablesResponse<>(values, params.getDraw(), rowCount, rowCount);
    }
}
