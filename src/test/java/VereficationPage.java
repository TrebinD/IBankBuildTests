import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$x;

@Data
public class VereficationPage {
    private SelenideElement verCod = $x("//span[@data-test-id=\"code\"]//input");
    private SelenideElement buttonVerification = $x("//button[@data-test-id=\"action-verify\"]");
    private SelenideElement error = $x("//div[@class='notification__content']");

    public DashboardPage verefication(UserData userData) {
        verCod.val(userData.getVerificationCode());
        buttonVerification.click();
        error.shouldBe(hidden);

        return new DashboardPage();

    }

}
