package kukathon.server.kukathon28be.repository;

import kukathon.server.kukathon28be.entity.Diary;
import kukathon.server.kukathon28be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    Diary findByWriterAndCreateDate(User user, LocalDate localDate);

    List<Diary> findByWriter(User user);




}
