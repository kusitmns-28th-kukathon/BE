package kukathon.server.kukathon28be.controller;

import kukathon.server.kukathon28be.dto.FindRandomResponse;
import kukathon.server.kukathon28be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/friends")
    public FindRandomResponse findRandomUser() {
        return new FindRandomResponse(userService.findRandomUser());
    }

}
