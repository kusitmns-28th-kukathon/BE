package kukathon.server.kukathon28be.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KakaoUserResponse {

    private long id;
//    private String profile_nickname;
//    private String profile_image;
//    private String account_email;
    private UserProfileDTO  kakao_account;


    @Setter
    @Getter
    public class UserProfileDTO {
        private boolean profileNicknameNeedsAgreement;
        private boolean profileImageNeedsAgreement;
        private ProfileData profile;
        private boolean hasEmail;
        private boolean emailNeedsAgreement;
        private boolean isEmailValid;
        private boolean isEmailVerified;
        private String email;




        @Getter
        @Setter
        public class ProfileData {
            private String nickname;
            private String thumbnailImageUrl;
            private String profileImageUrl;
            private boolean isDefaultImage;
        }
    }






}