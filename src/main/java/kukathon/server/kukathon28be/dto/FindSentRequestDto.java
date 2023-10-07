package kukathon.server.kukathon28be.dto;

import kukathon.server.kukathon28be.entity.AddFriend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FindSentRequestDto {

    private Long userId;
    private String nickname;
    private boolean isAccepted;

    public static FindSentRequestDto of(AddFriend request) {
        return new FindSentRequestDto(request.getTo().getId(), request.getTo().getNickname(), request.isAgree());
    }

    public static FindSentRequestDto from(AddFriend request) {
        return new FindSentRequestDto(request.getFrom().getId(), request.getFrom().getNickname(), request.isAgree());
    }

}
