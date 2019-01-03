package ru.kc4kt4.data.distributor.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class UpdateBaseResponse implements Serializable {

    @ApiModelProperty(value = "Наименование БД")
    private String baseName;
    @ApiModelProperty(value = "Версия БД до выполнения запроса")
    private String version;
    @ApiModelProperty(value = "Актуальная версия БД")
    private String actualVersion;
    @ApiModelProperty(value = "Статус обновления версии БД")
    private String versionStatus;

    public UpdateBaseResponse(String version, String actualVersion) {
        this.version = version;
        this.actualVersion = actualVersion;
    }
}
