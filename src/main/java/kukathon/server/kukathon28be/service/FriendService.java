package kukathon.server.kukathon28be.service;

import kukathon.server.kukathon28be.entity.AddFriend;
import kukathon.server.kukathon28be.entity.User;
import kukathon.server.kukathon28be.repository.FriendRepository;
import kukathon.server.kukathon28be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    
    public Long createRequest(String username, Long userId) {
        User fromUser = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("존재하지 않는 엔티티"));
        User toUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("존재하지 않는 엔티티"));

        AddFriend request = AddFriend.builder()
                .from(fromUser)
                .to(toUser)
                .agree(false)
                .build();

        friendRepository.save(request);

        return request.getId();
    }

}
