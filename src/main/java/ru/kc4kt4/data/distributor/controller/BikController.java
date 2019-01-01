package ru.kc4kt4.data.distributor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.kc4kt4.data.distributor.annotations.JsonRestController;
import ru.kc4kt4.data.distributor.handler.BankInfoHandler;
import ru.kc4kt4.data.distributor.handler.BankStatusHandler;
import ru.kc4kt4.data.distributor.handler.BankUpdateDataHandler;
import ru.kc4kt4.data.distributor.response.BankInfoResponse;
import ru.kc4kt4.data.distributor.response.BankStatusResponse;
import ru.kc4kt4.data.distributor.response.UpdateBaseResponse;

@JsonRestController
@RequestMapping(value = "/biks", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BikController {

    @Value("${distribution.bik.host}")
    private String host;

    @Autowired
    private BankUpdateDataHandler bankUpdateDataHandler;
    @Autowired
    private BankInfoHandler bankInfoHandler;
    @Autowired
    private BankStatusHandler bankStatusHandler;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public UpdateBaseResponse updateData() {
        return bankUpdateDataHandler.handleRequest();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BankInfoResponse getBankById(@PathVariable String id) {
        return bankInfoHandler.handleRequest(id);
    }

    @RequestMapping(value = "/existence/{id}", method = RequestMethod.GET)
    public BankStatusResponse isBankExist(@PathVariable String id) {
        return bankStatusHandler.handleRequest(id);
    }
}
