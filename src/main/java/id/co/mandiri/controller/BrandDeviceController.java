package id.co.mandiri.controller;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import id.co.mandiri.dto.BrandDeviceDTO;
import id.co.mandiri.dto.BrandDeviceMapperRequestNew;
import id.co.mandiri.dto.BrandDeviceMapperRequestUpdate;
import id.co.mandiri.entity.BrandDevice;
import id.co.mandiri.service.BrandDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/master/brand-device")
public class BrandDeviceController {

    @Autowired
    private BrandDeviceService service;

    @PostMapping("/datatables")
    public DataTablesResponse<BrandDevice> datatables(
            @RequestParam(required = false, value = "draw", defaultValue = "0") Long draw,
            @RequestParam(required = false, value = "start", defaultValue = "0") Long start,
            @RequestParam(required = false, value = "length", defaultValue = "10") Long length,
            @RequestParam(required = false, value = "order[0][column]", defaultValue = "0") Long iSortCol0,
            @RequestParam(required = false, value = "order[0][dir]", defaultValue = "asc") String sSortDir0,
            @RequestBody(required = false) BrandDevice params) {

        if (params == null) params = new BrandDevice();
        log.info("draw: {}, start: {}, length: {}, type: {}", draw, start, length, params);
        return service.datatables(
                new DataTablesRequest(draw, length, start, sSortDir0, iSortCol0, params)
        );
    }

    @GetMapping("/list")
    public ResponseEntity<List<BrandDevice>> list() {
        List<BrandDevice> list = service.findAll();
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDevice> findById(@PathVariable("id") String id) {
        BrandDevice params = service.findId(id);
        if (params != null) {
            return new ResponseEntity<>(params, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new BrandDevice(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<BrandDevice> save(@Valid @RequestBody BrandDeviceDTO.BrandDeviceRequestNewDTO params) {
        BrandDevice value = BrandDeviceMapperRequestNew.converter.convertToEntity(params);
        value = service.save(value);
        return new ResponseEntity<>(value, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<BrandDevice> update(@Valid @RequestBody BrandDeviceDTO.BrandDeviceRequestUpdateDTO params) {
        BrandDevice value = BrandDeviceMapperRequestUpdate.converter.convertToEntity(params);
        value = service.save(value);
        return new ResponseEntity<>(value, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BrandDevice> delete(@PathVariable("id") String id) {
        boolean deleted = service.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
