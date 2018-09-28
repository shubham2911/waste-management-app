package com.realiota.waste.exception;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WasteManagementException extends RuntimeException {

    private Integer appErrorCode;

    private String appErrorMessage;

    public WasteManagementException(Integer appErrorCode, String appErrorMessage) {
        super(appErrorMessage);
        this.appErrorCode = appErrorCode;
        this.appErrorMessage = appErrorMessage;
    }

    public WasteManagementException(String appErrorMessage) {
        super(appErrorMessage);
        this.appErrorMessage = appErrorMessage;
    }

}
