package kukathon.server.kukathon28be.repository;

import kukathon.server.kukathon28be.entity.AddFriend;
import kukathon.server.kukathon28be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<AddFriend, Long> {

    List<AddFriend> findByFromAndAgree(User user, boolean agree);

}
