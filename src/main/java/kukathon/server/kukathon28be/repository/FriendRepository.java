package kukathon.server.kukathon28be.repository;

import kukathon.server.kukathon28be.entity.AddFriend;
import kukathon.server.kukathon28be.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<AddFriend, Long> {

    Slice<AddFriend> findAllByFrom(User user, Pageable pageable);

}
