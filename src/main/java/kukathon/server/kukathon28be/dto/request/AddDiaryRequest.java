package kukathon.server.kukathon28be.dto.request;


import lombok.*;

import java.util.ArrayList;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddDiaryRequest {

    private String contents;

}
