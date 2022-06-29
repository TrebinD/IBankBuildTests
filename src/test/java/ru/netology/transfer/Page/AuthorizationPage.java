package ru.netology.transfer.Page;


import com.codeborne.selenide.SelenideElement;
import ru.netology.transfer.Data.UserData;

import static com.codeborne.selenide.Selenide.$x;

public class AuthorizationPage {
    private SelenideElement loginInput = $x("//span[@data-test-id=\"login\"]//input");
    private SelenideElement passwordInput = $x("//span[@data-test-id=\"password\"]//input");
    private SelenideElement input = $x("//button[@data-test-id=\"action-login\"]");

    public VereficationPage login(UserData userData) {
        loginInput.val(UserData.getAuthInfo().getLogin());
        passwordInput.val(UserData.getAuthInfo().getPassword());
        input.click();
        return new VereficationPage();
    }
}
