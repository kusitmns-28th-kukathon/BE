package kukathon.server.kukathon28be.controller;


import kukathon.server.kukathon28be.config.security.CustomUser;
import kukathon.server.kukathon28be.dto.request.AddDiaryRequest;
import kukathon.server.kukathon28be.dto.response.DiaryRecordResponseDto;
import kukathon.server.kukathon28be.dto.response.ResponseDto;
import kukathon.server.kukathon28be.dto.response.TokenResponseDto;
import kukathon.server.kukathon28be.service.DiaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/diary")
public class DiaryController {

    private final Logger LOGGER = LoggerFactory.getLogger(DiaryController.class);

    private final DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService){
        this.diaryService = diaryService;

    }


    @PostMapping(value = "/add-diary")
    public ResponseDto addDiary(@RequestBody @Validated AddDiaryRequest addDiaryRequest, @AuthenticationPrincipal CustomUser customUser)  {

        ResponseDto responseDto = diaryService.addDiary(addDiaryRequest, customUser.getUserId());

        LOGGER.info("일기 추가 완료");

        return responseDto;
    }

    @GetMapping(value = "/diary-record/{date}")
    public DiaryRecordResponseDto diaryRecord(
            @PathVariable("date") String date,
            @AuthenticationPrincipal CustomUser customUser)  {

        DiaryRecordResponseDto diaryRecordResponseDto = diaryService.diaryRecord(date, customUser.getUserId());

        LOGGER.info("기록 조회 완료");

        return diaryRecordResponseDto;
    }




}
