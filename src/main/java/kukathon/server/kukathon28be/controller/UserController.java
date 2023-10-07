package kukathon.server.kukathon28be.controller;

import kukathon.server.kukathon28be.dto.FindEmailDto;
import kukathon.server.kukathon28be.dto.FindRandomResponse;
import kukathon.server.kukathon28be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/friends")
    public FindRandomResponse findRandomUser() {
        return new FindRandomResponse(userService.findRandomUser());
    }

    @GetMapping("/friends/search")
    public FindEmailDto findUserByEmail(@RequestParam String email, @RequestParam int pageNum, @RequestParam int pageSize) {
        return userService.findUserByEmail(email, pageNum, pageSize);
    }

}
