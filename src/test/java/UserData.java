import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class UserData {
    private final String login = "vasya";
    private final String password = "qwerty123";
    private final String verificationCode = "12345";
    private final String[] cards = {"5559 0000 0000 0001", "5559 0000 0000 0002", "2002 0000 0000 0002"};


    public String getCards(int index) {

        String card = cards[index];
        return card;
    }


}
