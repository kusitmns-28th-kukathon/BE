package kukathon.server.kukathon28be.service;

import kukathon.server.kukathon28be.dto.AcceptFriendRequest;
import kukathon.server.kukathon28be.dto.FindSentRequestDto;
import kukathon.server.kukathon28be.entity.AddFriend;
import kukathon.server.kukathon28be.entity.User;
import kukathon.server.kukathon28be.repository.FriendRepository;
import kukathon.server.kukathon28be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    
    public Long createRequest(Long fromUserID, Long toUserId) {
        User fromUser = userRepository.findById(fromUserID).orElseThrow(() -> new RuntimeException("존재하지 않는 엔티티"));
        User toUser = userRepository.findById(toUserId).orElseThrow(() -> new RuntimeException("존재하지 않는 엔티티"));

        AddFriend request = AddFriend.builder()
                .from(fromUser)
                .to(toUser)
                .agree(false)
                .build();

        friendRepository.save(request);
        fromUser.addSendReqeust(request);
        toUser.addReceivedRequest(request);

        return request.getId();
    }

    public void acceptRequest(Long toUserId, AcceptFriendRequest request) throws RuntimeException {
        User toUser = userRepository.findById(toUserId).orElseThrow(() -> new RuntimeException("존재하지 않는 엔티티"));
        AddFriend pendingRequest = friendRepository.findById(request.getRequestId()).orElseThrow(() -> new RuntimeException("존재하지 않는 엔티티"));

        if (pendingRequest.isAgree()) {
            throw new RuntimeException("이미 처리된 요청");
        }

        if (!pendingRequest.getTo().equals(toUser)) {
            throw new RuntimeException("권한이 없습니다.");
        }

        pendingRequest.accept();
    }

    public List<FindSentRequestDto> findSentRequest(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(""));
        return user.getSendRequests().stream()
                .map(FindSentRequestDto::of)
                .collect(Collectors.toList());
    }

    public List<FindSentRequestDto> findReceivedRequest(Long userId) {
        User toUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("존재하지 않는 엔티티"));
        return toUser.getReceivedRequests().stream()
                .map(FindSentRequestDto::of)
                .collect(Collectors.toList());
    }
}
