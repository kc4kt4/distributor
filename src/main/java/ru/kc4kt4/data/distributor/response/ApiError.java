package ru.kc4kt4.data.distributor.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ApiError implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String title;

    public ApiError(int code, String title) {
        this.code = String.valueOf(code);
        this.title = title;
    }
}
