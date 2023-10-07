package kukathon.server.kukathon28be.service;

import kukathon.server.kukathon28be.dto.FindEmailDto;
import kukathon.server.kukathon28be.dto.FindRandomUserDto;
import kukathon.server.kukathon28be.entity.User;
import kukathon.server.kukathon28be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<FindRandomUserDto> findRandomUser() {
        return userRepository.findRandomUser()
                .map(FindRandomUserDto::of)
                .collect(Collectors.toList());
    }

    public FindEmailDto findUserByEmail(String email, int pageNum, int pageSize) {
        PageRequest request = PageRequest.of(pageNum, pageSize);
        Slice<User> users = userRepository.findUserByEmailContains(email, request);
        return new FindEmailDto(users);
    }

}
