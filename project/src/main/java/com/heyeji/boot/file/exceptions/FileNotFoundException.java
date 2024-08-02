package com.heyeji.boot.file.exceptions;

import com.heyeji.boot.global.exceptions.script.AlertBackException;
import org.springframework.http.HttpStatus;

public class FileNotFoundException extends AlertBackException {
    public FileNotFoundException(){
        super("NotFound.file", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
