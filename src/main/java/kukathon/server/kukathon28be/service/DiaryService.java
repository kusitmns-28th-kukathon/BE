package kukathon.server.kukathon28be.service;


import kukathon.server.kukathon28be.config.security.JwtTokenProvider;
import kukathon.server.kukathon28be.dto.request.AddDiaryRequest;
import kukathon.server.kukathon28be.dto.response.DiaryRecordResponseDto;
import kukathon.server.kukathon28be.dto.response.MainResponseDto;
import kukathon.server.kukathon28be.dto.response.ResponseDto;
import kukathon.server.kukathon28be.entity.Diary;
import kukathon.server.kukathon28be.entity.DiaryDetail;
import kukathon.server.kukathon28be.entity.User;
import kukathon.server.kukathon28be.repository.DiaryDetailRepository;
import kukathon.server.kukathon28be.repository.DiaryRepository;
import kukathon.server.kukathon28be.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DiaryService {

    public JwtTokenProvider jwtTokenProvider;

    public DiaryRepository diaryRepository;

    public DiaryDetailRepository diaryDetailRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(DiaryService.class);
    @Autowired
    public DiaryService(DiaryRepository diaryRepository, DiaryDetailRepository diaryDetailRepository) {

        this.diaryRepository = diaryRepository;
        this.diaryDetailRepository = diaryDetailRepository;
    }


    public ResponseDto addDiary(AddDiaryRequest addDiaryRequest, Long userId){

        ZoneId seoulTimeZone = ZoneId.of("Asia/Seoul");
        LocalDate currentDateInSeoul = LocalDate.now(seoulTimeZone);
        User user = User.builder().id(userId).build();
        LOGGER.info(String.valueOf(currentDateInSeoul));

        Diary diary = diaryRepository.findByWriterAndCreateDate(user, currentDateInSeoul);


        if(diary == null){         // 값이 없으면

            LOGGER.info("1");

            diary = Diary.builder()
                    .writer(user)
                    .good(0)
                    .build();
            Diary userDiary = diaryRepository.save(diary);

            DiaryDetail diaryDetail = DiaryDetail.builder()
                    .diary(userDiary)
                    .content(addDiaryRequest.getContents())
                    .build();

            diaryDetailRepository.save(diaryDetail);
        }else {

            LOGGER.info("2");

            // 값이 있으면

            DiaryDetail diaryDetail = DiaryDetail.builder()
                    .diary(diary)
                    .content(addDiaryRequest.getContents())
                    .build();

            diaryDetailRepository.save(diaryDetail);

        }

        ResponseDto responseDto = ResponseDto.builder()
                .code(200)
                .message("OK")
                .build();
        return responseDto;
    }


    public DiaryRecordResponseDto diaryRecord(String date, Long userId){

        User user = User.builder().id(userId).build();

        Diary diary = diaryRepository.findByWriterAndCreateDate(user, LocalDate.parse(date));

        List<DiaryDetail> diaryDetailList =diaryDetailRepository.findByDiary(diary);

        LOGGER.info(diaryDetailList.get(0).getContent().toString());

        List<String> contentList = diaryDetailList.stream()
                .map(DiaryDetail::getContent)
                .collect(Collectors.toList());

        DiaryRecordResponseDto diaryRecordResponseDto = DiaryRecordResponseDto.builder()
                .record(contentList)
                .message("OK")
                .code(200)
                .build();

        return diaryRecordResponseDto;

    }



    public MainResponseDto mainData(Long userId) {
        User user = User.builder().id(userId).build();

        MainResponseDto mainResponseDto = new MainResponseDto();
        mainResponseDto.setCode(200);
        mainResponseDto.setMessage("OK");

        List<Diary> diaryList = diaryRepository.findByWriter(user);

        List<List<MainResponseDto.UserData>> userDataLists = new ArrayList<>();
        List<MainResponseDto.UserData> userDataList = new ArrayList<>();

        for (int i = 0; i < diaryList.size(); i++) {
            List<DiaryDetail> diaryDetailList = diaryDetailRepository.findByDiary(diaryList.get(i));
            MainResponseDto.UserData userData = new MainResponseDto.UserData();

            List<String> contentList = diaryDetailList.stream()
                    .map(DiaryDetail::getContent)
                    .collect(Collectors.toList());

            userData.setGood(diaryList.get(i).getGood());
            userData.setContents(contentList);

            userDataList.add(userData);

            if (userDataList.size() == 7 || i == diaryList.size() - 1) {
                userDataLists.add(userDataList);
                userDataList = new ArrayList<>();
            }
        }

        mainResponseDto.setData(userDataLists);

        return mainResponseDto;
    }


    public MainResponseDto friendMainData() {

        Long userId = 27L;

        User user = User.builder().id(userId).build();



        MainResponseDto mainResponseDto = new MainResponseDto();
        mainResponseDto.setCode(200);
        mainResponseDto.setMessage("OK");

        List<Diary> diaryList = diaryRepository.findByWriter(user);

        List<List<MainResponseDto.UserData>> userDataLists = new ArrayList<>();
        List<MainResponseDto.UserData> userDataList = new ArrayList<>();

        for (int i = 0; i < diaryList.size(); i++) {
            List<DiaryDetail> diaryDetailList = diaryDetailRepository.findByDiary(diaryList.get(i));
            MainResponseDto.UserData userData = new MainResponseDto.UserData();

            List<String> contentList = diaryDetailList.stream()
                    .map(DiaryDetail::getContent)
                    .collect(Collectors.toList());

            userData.setGood(diaryList.get(i).getGood());
            userData.setContents(contentList);

            userDataList.add(userData);

            if (userDataList.size() == 7 || i == diaryList.size() - 1) {
                userDataLists.add(userDataList);
                userDataList = new ArrayList<>();
            }
        }

        mainResponseDto.setData(userDataLists);

        return mainResponseDto;
    }
}
