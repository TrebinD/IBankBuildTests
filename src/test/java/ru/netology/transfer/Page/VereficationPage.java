package ru.netology.transfer.Page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.transfer.Data.UserData;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$x;


public class VereficationPage {
    private SelenideElement verCod = $x("//span[@data-test-id=\"code\"]//input");
    private SelenideElement buttonVerification = $x("//button[@data-test-id=\"action-verify\"]");
    private SelenideElement error = $x("//div[@class='notification__content']");

    public DashboardPage verefication(UserData userData) {
        verCod.val(userData.getVerificationCodeFor(UserData.getAuthInfo()).getCode());
        buttonVerification.click();
        error.shouldBe(hidden);

        return new DashboardPage();

    }
}
