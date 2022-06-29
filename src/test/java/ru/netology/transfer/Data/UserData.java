package ru.netology.transfer.Data;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;


@Data
@RequiredArgsConstructor
public class UserData {

    private final String[] cards = {"5559 0000 0000 0001", "5559 0000 0000 0002", "2002 0000 0000 0002"};


    public String getCards(int index) {
        String card = cards[index];
        return card;
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }


}
