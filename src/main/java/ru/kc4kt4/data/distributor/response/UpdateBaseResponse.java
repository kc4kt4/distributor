package ru.kc4kt4.data.distributor.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class UpdateBaseResponse implements Serializable {

    private String baseName;
    private String version;
    private String actualVersion;
    private String versionStatus;

    public UpdateBaseResponse(String version) {
        this.version = version;
    }

    public UpdateBaseResponse(String version, String actualVersion) {
        this.version = version;
        this.actualVersion = actualVersion;
    }
}
