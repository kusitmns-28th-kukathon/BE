package kukathon.server.kukathon28be.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
    private List<DiaryDetail> contents = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User writer;

    @CreatedDate
    private LocalDate createDate;

    @LastModifiedDate
    private LocalDate modifiedDate;

}
