package kukathon.server.kukathon28be.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_diary")
@EntityListeners(AuditingEntityListener.class)
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @Column(name = "goods")
    private int good;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
    private List<DiaryDetail> contents = new ArrayList<>();



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User writer;


    @CreationTimestamp
    private LocalDate createDate;

    @UpdateTimestamp
    private LocalDate modifiedDate;


}
