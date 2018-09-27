package com.realiota.waste.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T> {

    private T response;

    private Integer appErrorCode;

    private String errorMessage;

    public Response(T response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Response{" + "response=" + response + ", appErrorCode=" + appErrorCode + ", errorMessage='"
                + errorMessage + '\'' + '}';
    }
}
