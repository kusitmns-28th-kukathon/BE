package kukathon.server.kukathon28be.entity;


import kukathon.server.kukathon28be.config.auditing.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "tb_add_friend")
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

}
