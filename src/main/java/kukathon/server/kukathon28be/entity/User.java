package kukathon.server.kukathon28be.entity;

import kukathon.server.kukathon28be.config.auditing.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Builder
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@AllArgsConstructor
@Table(name = "tb_user")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "user_role")
    private String userRole;



}