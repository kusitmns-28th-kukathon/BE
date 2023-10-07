package kukathon.server.kukathon28be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FindSentResponse {

    List<FindSentRequestDto> requests;

}
