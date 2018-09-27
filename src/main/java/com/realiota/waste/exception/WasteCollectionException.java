package com.realiota.waste.exception;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WasteCollectionException extends RuntimeException {

    private Integer appErrorCode;

    private String appErrorMessage;

    public WasteCollectionException(Integer appErrorCode, String appErrorMessage) {
        super(appErrorMessage);
        this.appErrorCode = appErrorCode;
        this.appErrorMessage = appErrorMessage;
    }

    public WasteCollectionException(String appErrorMessage) {
        super(appErrorMessage);
        this.appErrorMessage = appErrorMessage;
    }

}
