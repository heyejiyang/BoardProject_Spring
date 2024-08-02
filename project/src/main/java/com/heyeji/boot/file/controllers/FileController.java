package com.heyeji.boot.file.controllers;

import com.heyeji.boot.file.entities.FileInfo;
import com.heyeji.boot.file.services.FileUploadService;
import com.heyeji.boot.global.exceptions.RestExceptionProcessor;
import com.heyeji.boot.global.rests.JSONData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController implements RestExceptionProcessor {

    private final FileUploadService uploadService;

    @PostMapping("/upload")
    public ResponseEntity<JSONData> upload(@RequestPart("file") MultipartFile[] files, //매개변수로 파일 데이터 넘어옴, 어디 필드에서 넘어오는지 알려줘야함 -> RequestPart
                                           @RequestParam(name = "gid", required = false)String gid, @RequestParam(name = "location", required = false) String location){
        List<FileInfo> items = uploadService.upload(files,gid,location);

        HttpStatus status = HttpStatus.CREATED;
        JSONData data = new JSONData(items);
        data.setStatus(status);

        return ResponseEntity.status(HttpStatus.CREATED).body(data);

    }
}
