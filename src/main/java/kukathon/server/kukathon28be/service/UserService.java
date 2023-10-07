package kukathon.server.kukathon28be.service;

import kukathon.server.kukathon28be.dto.FindRandomUserDto;
import kukathon.server.kukathon28be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

}
