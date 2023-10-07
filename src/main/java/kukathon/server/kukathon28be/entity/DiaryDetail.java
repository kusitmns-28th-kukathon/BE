package kukathon.server.kukathon28be.entity;

import kukathon.server.kukathon28be.config.auditing.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "tb_diary_detail")
public class DiaryDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;


    private String content;
}
