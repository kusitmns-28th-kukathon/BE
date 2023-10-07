package kukathon.server.kukathon28be.repository;

import kukathon.server.kukathon28be.entity.Alarm;
import kukathon.server.kukathon28be.entity.DiaryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
