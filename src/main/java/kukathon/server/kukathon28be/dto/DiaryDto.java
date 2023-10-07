package kukathon.server.kukathon28be.dto;

import kukathon.server.kukathon28be.entity.Diary;
import kukathon.server.kukathon28be.entity.DiaryDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDto {

    private Long id;
    private List<String> contents;
    private LocalDate date;

    public static DiaryDto of(Diary diary) {
        return new DiaryDto(diary.getId(),
                diary.getContents().stream()
                .map(DiaryDetail::getContent)
                        .collect(Collectors.toList()),
                diary.getCreateDate());
    }

}
