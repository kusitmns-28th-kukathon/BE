package kukathon.server.kukathon28be.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class MainResponseDto extends ResponseDto{

    @JsonProperty("data")
    private List<List<UserData>> data;


    @Setter
    @Getter
    public static class UserData {
        @JsonProperty("good")
        private int good;

        @JsonProperty("contents")
        private List<String> contents;
    }


}
