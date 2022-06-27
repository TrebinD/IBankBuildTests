package ru.netology.transfer.Page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Integer.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DashboardPage {
    private ElementsCollection buttonTopUp = $$x("//button[@data-test-id=\"action-deposit\"]");
    private SelenideElement buttonReload = $x("//button[@data-test-id=\"action-reload\"]");
    private ElementsCollection cards = $$x("//li[@class=\"list__item\"]");


    public DashboardPage() {

    }

    public int getCardBalance(int index) {
        buttonReload.click();
        String[] card = cards.get(index).toString().split(" ");
        int balance = valueOf(card[6]);
        return balance;
    }

    public TransferPage transfer(int indexCardTo) {
        buttonTopUp.get(indexCardTo).click();
        return new TransferPage();
    }

    public void newBalance(int id, int expected) {
        int actualBalance = getCardBalance(id);
        assertEquals(expected, actualBalance);
    }

    public void reload() {
        buttonReload.click();
    }

}