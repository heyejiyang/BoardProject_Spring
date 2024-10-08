package com.heyeji.boot.file.entities;

import com.heyeji.boot.global.entities.BaseMemberEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class FileInfo extends BaseMemberEntity {
    @Id @GeneratedValue
    private Long seq; //파일명, 서버에 업로드 될 파일 이름 - 'seq.확장자'

    @Column(length = 45,nullable = false)
    private String gid = UUID.randomUUID().toString(); //그룹 ID , 설정하지 않았을 경우 gid 랜덤하게 생성
    @Column(length = 45)
    private String location; //그룹 안에 세부 위치

    @Column(length = 80,nullable = false)
    private String fileName;
    @Column(length = 80)
    private String contentType;

    @Column(length = 30) //확장자 없는 경우도 있어서 nullable이 true일수도
    private String extension;// 파일 확장자

    private boolean done; // 그룹 작업 완료 여부

    @Transient //엔티티 내부에서만 사용할 목적으로 정의한 필드
    private String fileUrl; //파일 접근 URL
    @Transient
    private String filePath; //파일 업로드 경로
}
