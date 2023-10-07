package kukathon.server.kukathon28be.entity;


import kukathon.server.kukathon28be.config.auditing.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "tb_add_friend")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AddFriend extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_friend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id")
    private User to;

    private boolean agree;

    @Builder
    public AddFriend(User from, User to, boolean agree) {
        this.from = from;
        this.to = to;
        this.agree = agree;
    }

    public void accept() {
        this.agree = true;
    }

    public void setFrom(User user) {
        this.from = user;
    }

    public void setTo(User user) {
        this.to = user;
    }

}
