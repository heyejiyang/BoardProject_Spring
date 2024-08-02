package com.heyeji.boot.file.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public void upload(@RequestPart("file") MultipartFile[] files, //매개변수로 파일 데이터 넘어옴, 어디 필드에서 넘어오는지 알려줘야함 -> RequestPart
                       @RequestParam(name = "gid", required = false)String gid, @RequestParam(name = "location", required = false) String location){

    }
}
