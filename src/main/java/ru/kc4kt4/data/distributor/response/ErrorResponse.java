package ru.kc4kt4.data.distributor.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private String status = "fail";
    private ApiError[] errors;

    public ErrorResponse(ApiError... errors) {
        this.errors = errors;
    }

    public ErrorResponse(Integer code, String title) {
        this(new ApiError(code, title));
    }
}