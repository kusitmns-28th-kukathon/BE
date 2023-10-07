package kukathon.server.kukathon28be.dto.response;


import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class ResponseDto {
    private int code;
    private String message;
}
