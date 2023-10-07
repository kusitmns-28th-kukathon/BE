package kukathon.server.kukathon28be.service;

import kukathon.server.kukathon28be.controller.UserController;
import kukathon.server.kukathon28be.dto.FindEmailDto;
import kukathon.server.kukathon28be.dto.FindRandomUserDto;
import kukathon.server.kukathon28be.dto.response.ResponseDto;
import kukathon.server.kukathon28be.entity.AddFriend;
import kukathon.server.kukathon28be.entity.Alarm;
import kukathon.server.kukathon28be.entity.User;
import kukathon.server.kukathon28be.repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final AlarmRepository alarmRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    @Autowired
    public UserService(UserRepository userRepository, FriendRepository friendRepository,
                       AlarmRepository alarmRepository
    ) {
        this.alarmRepository = alarmRepository;
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }


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


    @Transactional
    public ResponseDto sendAlarm(Long userId){

        User user = User.builder().id(userId).build();

        List<AddFriend> addFriendList = friendRepository.findByFromAndAgree(user, true);

        LOGGER.info(String.valueOf(addFriendList.get(0).getTo()));




        if(addFriendList.size() != 0){

            for(int i = 0; i < addFriendList.size(); i++){

                Alarm alarm = Alarm.builder()
                        .from(user)
                        .to(addFriendList.get(i).getTo())
                        .build();

                alarmRepository.save(alarm);
            }
        }

        ResponseDto responseDto = ResponseDto.builder()
                .message("OK")
                .code(200)
                .build();

        return responseDto;

    }
}
