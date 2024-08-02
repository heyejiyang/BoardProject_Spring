package com.heyeji.boot.file.services;

import com.heyeji.boot.file.entities.FileInfo;
import com.heyeji.boot.file.repositories.FileInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService{

    private final FileInfoRepository fileInfoRepository;

    public void upload(MultipartFile[] files, String gid, String location){
        /**
         * 1. 파일 정보 저장
         * 2. 파일을 서버로 이동
         * 3. 이미지 이면 썸네일 생성
         * 4. 업로드한 파일 목록 반환
         */
        gid = StringUtils.hasText(gid) ? gid : UUID.randomUUID().toString();

        //1. 파일 정보 저장
        for(MultipartFile file : files){
            String fileName = file.getOriginalFilename(); //실제 업로드한 파일 이름
            String contentType = file.getContentType(); //파일 형식

            //확장자 자르기
            String extension = fileName.substring(fileName.lastIndexOf(".")); //.부터 끝까지

            //DB에 저장
            FileInfo fileInfo = FileInfo.builder()
                    .gid(gid)
                    .location(location)
                    .fileName(fileName)
                    .contentType(contentType)
                    .extension(extension)
                    .build();

            fileInfoRepository.saveAndFlush(fileInfo);
        }
    }
}
