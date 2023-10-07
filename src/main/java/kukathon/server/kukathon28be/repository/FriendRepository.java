package kukathon.server.kukathon28be.repository;

import kukathon.server.kukathon28be.entity.AddFriend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<AddFriend, Long> {

}
