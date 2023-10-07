package kukathon.server.kukathon28be.repository;

import kukathon.server.kukathon28be.entity.Diary;
import kukathon.server.kukathon28be.entity.DiaryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryDetailRepository extends JpaRepository<DiaryDetail, Long> {

    List<DiaryDetail> findByDiary(Diary diary);




}
