package ru.kc4kt4.data.distributor.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kc4kt4.data.distributor.annotations.JsonRestController;
import ru.kc4kt4.data.distributor.handler.GetVersionDataHandler;
import ru.kc4kt4.data.distributor.response.UpdateBaseResponse;

@Api("Api: Базы данных")
@JsonRestController
@RequestMapping(value = "/version", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class VersionDataController {

    @Autowired
    private GetVersionDataHandler getVersionDataHandler;

    @ApiOperation(value = "Проверить текущую версию базы данных по названию базы")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UpdateBaseResponse checkVersion(@ApiParam(value = "Наименование базы данных", required = true)
                                               @PathVariable String id) {
        return getVersionDataHandler.handleRequest(id);
    }
}
