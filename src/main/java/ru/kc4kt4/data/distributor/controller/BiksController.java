package ru.kc4kt4.data.distributor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.kc4kt4.data.distributor.handler.BankStatusHandler;
import ru.kc4kt4.data.distributor.handler.UpdateBankDetailHandler;
import ru.kc4kt4.data.distributor.handler.VersionStatusHandler;
import ru.kc4kt4.data.distributor.response.BankStatusResponse;
import ru.kc4kt4.data.distributor.response.UpdateBaseResponse;

@RestController
@RequestMapping(value = "/biks", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BiksController {

    @Value("${distribution.bik.host}")
    private String host;

    @Autowired
    private UpdateBankDetailHandler updateBankDetailHandler;
    @Autowired
    private VersionStatusHandler versionStatusHandler;
    @Autowired
    private BankStatusHandler bankStatusHandler;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public UpdateBaseResponse updateData() {
        return updateBankDetailHandler.handleRequest();
    }

    @RequestMapping(value = "/{id}/version", method = RequestMethod.GET)
    public UpdateBaseResponse checkVersion(@PathVariable String id) {
        return versionStatusHandler.handleRequest(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BankStatusResponse isBankExist(@PathVariable String id) {
        return bankStatusHandler.handleRequest(id);
    }
}
