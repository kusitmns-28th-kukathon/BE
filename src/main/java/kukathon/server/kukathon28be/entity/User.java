package kukathon.server.kukathon28be.entity;

import kukathon.server.kukathon28be.config.auditing.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Cascade;

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
@ToString(of = {"id", "email"})
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "nums")
    private String num;

    @Column(name = "user_profile")
    private String userProfile;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.REMOVE)
    private List<Diary> diaries = new ArrayList<>();

    @OneToMany(mappedBy = "to")
    private List<AddFriend> sendRequests = new ArrayList<>();

    @OneToMany(mappedBy = "from")
    private List<AddFriend> receivedRequests = new ArrayList<>();

    @OneToMany(mappedBy = "to")
    private List<Alarm> alarms = new ArrayList<>();

}