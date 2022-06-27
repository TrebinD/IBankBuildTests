package ru.netology.transfer.Page;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import ru.netology.transfer.Data.UserData;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$x;

@Data
public class TransferPage {
    private SelenideElement amountIn = $x("//span[@data-test-id=\"amount\"]//input");
    private SelenideElement input = $x("//span[@data-test-id=\"from\"]//input");
    private SelenideElement transfer = $x("//button[@data-test-id=\"action-transfer\"]");
    private SelenideElement backButton = $x("//button[@data-test-id=\"action-cancel\"]");
    private SelenideElement error = $x("//div[@data-test-id=\"error-notification\"]/button");

    public void transferMoney(UserData userData, int amount, int indexCard) {
        amountIn.val(String.valueOf(amount));
        input.val(userData.getCards(indexCard));
        transfer.click();
        error.should(hidden);
    }


}


