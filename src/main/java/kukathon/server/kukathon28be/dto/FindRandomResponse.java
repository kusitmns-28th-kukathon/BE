package kukathon.server.kukathon28be.dto;

import lombok.Data;

import java.util.List;

@Data
public class FindRandomResponse {

    private List<FindRandomUserDto> users;

    public FindRandomResponse(List<FindRandomUserDto> users) {
        this.users = users;
    }
}
