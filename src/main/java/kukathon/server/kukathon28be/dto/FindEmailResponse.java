package kukathon.server.kukathon28be.dto;

import lombok.Data;

import java.util.List;

@Data
public class FindEmailResponse {

    private List<FindRandomUserDto> users;

    public FindEmailResponse(List<FindRandomUserDto> users) {
        this.users = users;
    }

}
