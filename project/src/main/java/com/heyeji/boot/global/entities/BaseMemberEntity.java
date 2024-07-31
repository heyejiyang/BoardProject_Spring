package com.heyeji.boot.global.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 회원 정보가 필요한 공통 속성화
 */
@MappedSuperclass
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseMemberEntity extends BaseEntity{
    @CreatedBy
    @Column(length = 65, updatable = false)
    private String createdBy; //처음 추가할때만 생성

    @LastModifiedBy
    @Column(length = 65, insertable = false)
    private String modifiedBy; //수정될때 생성
}
