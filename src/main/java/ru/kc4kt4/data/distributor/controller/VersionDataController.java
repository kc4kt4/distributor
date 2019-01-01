package ru.kc4kt4.data.distributor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kc4kt4.data.distributor.annotations.JsonRestController;
import ru.kc4kt4.data.distributor.handler.GetVersionDataHandler;
import ru.kc4kt4.data.distributor.response.UpdateBaseResponse;

@JsonRestController
@RequestMapping(value = "/version", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class VersionDataController {

    @Autowired
    private GetVersionDataHandler getVersionDataHandler;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UpdateBaseResponse checkVersion(@PathVariable String id) {
        return getVersionDataHandler.handleRequest(id);
    }
}
