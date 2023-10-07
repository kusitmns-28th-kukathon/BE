package kukathon.server.kukathon28be.repository;

import kukathon.server.kukathon28be.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



    Optional<User> findByNum(String userId);

    Optional<User> findByEmail(String userId);

    @Query(nativeQuery = true,
            value = "select * from tb_user order by rand() limit 10")
    Stream<User> findRandomUser();

    Slice<User> findUserByEmailContains(String email, Pageable pageable);

}

