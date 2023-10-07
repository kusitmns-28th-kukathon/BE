package kukathon.server.kukathon28be.dto.response;

import kukathon.server.kukathon28be.entity.DiaryDetail;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class DiaryRecordResponseDto extends ResponseDto{

//    private int code;
//    private String message;
    private List<String> record;
}
