package kukathon.server.kukathon28be.dto;

import kukathon.server.kukathon28be.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class FindRandomUserDto {

    private Long userId;

    private String nickname;

    private String userProfile;

    public static FindRandomUserDto of(User user) {
        return FindRandomUserDto.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .userProfile(user.getUserProfile())
                .build();
    }
}
