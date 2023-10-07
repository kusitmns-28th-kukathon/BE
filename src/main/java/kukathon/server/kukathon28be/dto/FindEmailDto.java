package kukathon.server.kukathon28be.dto;

import kukathon.server.kukathon28be.entity.User;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
public class FindEmailDto {

    List<FindRandomUserDto> users;

    boolean hasNextPage;

    int page;

    public FindEmailDto(List<FindRandomUserDto> users) {
        this.users = users;
    }

    public FindEmailDto(Slice<User> users) {
        this.users = users.getContent().stream()
                .map(FindRandomUserDto::of)
                .collect(Collectors.toList());
        this.hasNextPage = users.hasNext();
        this.page = users.getNumber();
    }

}
