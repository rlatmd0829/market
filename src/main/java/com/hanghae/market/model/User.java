package com.hanghae.market.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanghae.market.dto.SignupReqeustDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Embedded
    private Adderss address;

    @Column
    private String myself;

    @Column(columnDefinition = "TEXT")
    private String profile_img;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Heart> hearts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Follow> requestUsers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "followUser")
    private List<Follow> responseUsers = new ArrayList<>();


    public User(SignupReqeustDto reqeustDto) {

        this.username = reqeustDto.getUsername();
        this.password = reqeustDto.getPassword();
        this.email = reqeustDto.getEmail();
        this.myself = reqeustDto.getMyself();
        this.address = new Adderss(reqeustDto.getCity(), reqeustDto.getStreet());
        this.role = UserRole.ROLE_USER;
    }
}
