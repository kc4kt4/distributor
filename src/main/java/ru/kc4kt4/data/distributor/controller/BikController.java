package ru.kc4kt4.data.distributor.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kc4kt4.data.distributor.annotations.JsonRestController;
import ru.kc4kt4.data.distributor.handler.BankInfoHandler;
import ru.kc4kt4.data.distributor.handler.BankStatusHandler;
import ru.kc4kt4.data.distributor.handler.BankUpdateDataHandler;
import ru.kc4kt4.data.distributor.response.BankInfoResponse;
import ru.kc4kt4.data.distributor.response.BankStatusResponse;
import ru.kc4kt4.data.distributor.response.UpdateBaseResponse;

import javax.validation.constraints.Pattern;

@Api("Api: Банки")
@JsonRestController
@RequestMapping(value = "/biks", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class BikController {

    @Value("${distribution.bik.host}")
    private String host;

    @Autowired
    private BankUpdateDataHandler bankUpdateDataHandler;
    @Autowired
    private BankInfoHandler bankInfoHandler;
    @Autowired
    private BankStatusHandler bankStatusHandler;

    @ApiOperation(value = "Обновить данные по БИКам в базе")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Content-Type",
                    value = "Content-Type",
                    paramType = "header",
                    defaultValue = MediaType.APPLICATION_JSON_UTF8_VALUE)
    })
    public UpdateBaseResponse updateData() {
        return bankUpdateDataHandler.handleRequest();
    }

    @ApiOperation(value = "Получить данные по банку по БИКу")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Content-Type",
                    value = "Content-Type",
                    paramType = "header",
                    defaultValue = MediaType.APPLICATION_JSON_UTF8_VALUE)
    })
    public BankInfoResponse getBankById(@ApiParam(value = "Бик банка", required = true)
                                        @PathVariable @Pattern(regexp = "\\d{9}") String id) {
        return bankInfoHandler.handleRequest(id);
    }

    @ApiOperation(value = "Проверить, существует ли банк в базе с указанным БИКом")
    @RequestMapping(value = "/existence/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Content-Type",
                    value = "Content-Type",
                    paramType = "header",
                    defaultValue = MediaType.APPLICATION_JSON_UTF8_VALUE)
    })
    public BankStatusResponse isBankExist(@ApiParam(value = "Бик банка", required = true)
                                          @PathVariable @Pattern(regexp = "\\d{9}") String id) {
        return bankStatusHandler.handleRequest(id);
    }
}
