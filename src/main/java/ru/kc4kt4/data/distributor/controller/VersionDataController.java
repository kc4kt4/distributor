package ru.kc4kt4.data.distributor.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kc4kt4.data.distributor.annotations.JsonRestController;
import ru.kc4kt4.data.distributor.handler.GetVersionBaseByIdHandler;
import ru.kc4kt4.data.distributor.response.UpdateBaseResponse;

@Api("Api: Базы данных")
@JsonRestController
@RequestMapping(value = "/version", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class VersionDataController {

    @Autowired
    private GetVersionBaseByIdHandler getVersionBaseByIdHandler;

    @ApiOperation(value = "Проверить текущую версию базы данных по названию базы")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Content-Type",
                    value = "Content-Type",
                    paramType = "header",
                    defaultValue = MediaType.APPLICATION_JSON_UTF8_VALUE)
    })
    public UpdateBaseResponse checkVersion(@ApiParam(value = "Наименование базы данных", required = true)
                                               @PathVariable String id) {
        return getVersionBaseByIdHandler.handleRequest(id);
    }
}
