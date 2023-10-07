package kukathon.server.kukathon28be.dto;

import lombok.Data;

import java.util.List;

@Data
public class FindRandomResponse {

    private List<FindRandomUserDto> users;
    private int page;
    private boolean hasNextPage;

    public FindRandomResponse(List<FindRandomUserDto> users) {
        this.users = users;
    }
}
