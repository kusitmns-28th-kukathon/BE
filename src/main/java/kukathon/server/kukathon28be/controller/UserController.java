package kukathon.server.kukathon28be.controller;

import kukathon.server.kukathon28be.config.security.CustomUser;
import kukathon.server.kukathon28be.dto.FindEmailDto;
import kukathon.server.kukathon28be.dto.FindRandomResponse;
import kukathon.server.kukathon28be.dto.response.ResponseDto;
import kukathon.server.kukathon28be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);



    @GetMapping("/friends")
    public FindRandomResponse findRandomUser() {
        return new FindRandomResponse(userService.findRandomUser());
    }

    @GetMapping("/friends/search")
    public FindEmailDto findUserByEmail(@RequestParam String email, @RequestParam int pageNum, @RequestParam int pageSize) {
        return userService.findUserByEmail(email, pageNum, pageSize);
    }


    @PostMapping("/send-alarm")
    public ResponseDto sendAlarm(@AuthenticationPrincipal CustomUser customUser) {

        ResponseDto responseDto = userService.sendAlarm(customUser.getUserId());

        LOGGER.info("알람 보내기 완료");

        return responseDto;
    }

}
