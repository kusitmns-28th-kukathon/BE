package kukathon.server.kukathon28be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindDiaryResponse {

    List<DiaryDto> posts;
}
