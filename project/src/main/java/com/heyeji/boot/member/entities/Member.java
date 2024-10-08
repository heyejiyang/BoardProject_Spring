package com.heyeji.boot.member.entities;

import com.heyeji.boot.global.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Member extends BaseEntity {
    @Id @GeneratedValue
    private Long seq;
    @Column(length =65,unique = true, nullable = false)
    private String email;
    @Column(length = 65, nullable = false)
    private String password;
    @Column(length = 40, nullable = false)
    private String userName;
    @Column(length = 15, nullable = false)
    private String mobile;

    @ToString.Exclude
    @OneToMany(mappedBy = "member")
    private List<Authorities> authorities;
}
