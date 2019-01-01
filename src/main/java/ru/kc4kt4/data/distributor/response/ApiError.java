package ru.kc4kt4.data.distributor.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ApiError implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String title;

    @Setter
    private String detail;

    @Setter
    private String source;

    public ApiError(int code, String title) {
        this.code = String.valueOf(code);
        this.title = title;
    }
}
