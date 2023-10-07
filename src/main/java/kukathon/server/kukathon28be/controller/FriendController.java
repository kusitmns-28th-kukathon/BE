package kukathon.server.kukathon28be.controller;

import kukathon.server.kukathon28be.dto.CreateFriendRequest;
import kukathon.server.kukathon28be.dto.CreateFriendResponse;
import kukathon.server.kukathon28be.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @PostMapping("/friends/request")
    public CreateFriendResponse createRequest(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CreateFriendRequest request) {
        Long requestId = friendService.createRequest(userDetails.getUsername(), request.getUserId());
        return new CreateFriendResponse(requestId);
    }

}
