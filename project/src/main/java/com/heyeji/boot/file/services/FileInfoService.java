package com.heyeji.boot.file.services;

import com.heyeji.boot.file.constants.FileStatus;
import com.heyeji.boot.file.entities.FileInfo;
import com.heyeji.boot.file.exceptions.FileNotFoundException;
import com.heyeji.boot.file.repositories.FileInfoRepository;
import com.heyeji.boot.global.configs.FileProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(FileProperties.class)
public class FileInfoService {
    private final FileInfoRepository infoRepository;
    private final FileProperties properties;
    private final HttpServletRequest request;
    /**
     * 파일 1개 조회
     * @param seq : 파일 등록 번호
     * @return
     */
    public FileInfo get(Long seq){
        FileInfo item = infoRepository.findById(seq).orElseThrow(FileNotFoundException::new);

        /**
         * 2차 가공
         * 1. 파일을 접근할 수 있는 URL - 보여주기 위한 목적
         * 2. 파일을 접근할 수 있는 PATH - 파일 삭제, 다운로드 등등
         */

        addFileInfo(item);

        return item;
    }

    /**
     * 파일 목록 조회
     * @param gid
     * @param location
     * @param status - ALL: 완료+미완료, DONE: 완료, UNDONE: 미완료
     * @return
     */
    public List<FileInfo> getList(String gid, String location, FileStatus status){
        // 완료된 파일과 완료되지 않은 파일 모두 보고싶을 수 도있음 - 상수정의

        return null;
    }

    /**
     * 파일 정보 추가 처리
     * - fileUrl, filePath
     * @param item
     */
    public void addFileInfo(FileInfo item){
        String fileUrl = getFileUrl(item);
        String filePath = getFilePath(item);

        item.setFileUrl(fileUrl);
        item.setFilePath(filePath);
    }

    //브라우저 접근 주소
    public String getFileUrl(FileInfo item){
        return request.getContextPath() + properties.getUrl() + "/" + getFolder(item.getSeq()) + "/" + getFileName(item);
    }

    //서버 업로드 경로
    public String getFilePath(FileInfo item){
        return properties.getPath()+ "/" + getFolder(item.getSeq()) + "/" + getFileName(item);
    }

    public String getFolder(long seq){
        return String.valueOf(seq % 10L);
    }

    public String getFileName(FileInfo item){
        String fileName = item.getSeq() + Objects.requireNonNullElse(item.getExtension(),"");
        return fileName;
    }
}
