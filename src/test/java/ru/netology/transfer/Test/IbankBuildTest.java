package ru.netology.transfer.Test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.transfer.Data.UserData;
import ru.netology.transfer.Page.AuthorizationPage;
import ru.netology.transfer.Page.DashboardPage;
import ru.netology.transfer.Page.TransferPage;
import ru.netology.transfer.Page.VereficationPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IbankBuildTest {
    UserData userData;
    DashboardPage dashboardPage;


    @BeforeEach
    public void autotenification() {
        open("http://localhost:9999/");
        AuthorizationPage authorizationPage = new AuthorizationPage();
        userData = new UserData();
        VereficationPage vereficationPage = authorizationPage.login(userData);
        dashboardPage = vereficationPage.verefication(userData);
    }

    @Test
    public void transferByCardFirstToCardSecond() {
        int balanceFirstCard = dashboardPage.getCardBalance(0);
        int balanceSecondCard = dashboardPage.getCardBalance(1);
        TransferPage transferPage = dashboardPage.transfer(0);
        transferPage.transferMoney(userData, 1000, 1);
        dashboardPage.reload();
        dashboardPage.newBalance(0, balanceFirstCard + 1000);
        dashboardPage.newBalance(1, balanceSecondCard - 1000);

    }

    @Test
    public void transferByCardSecondToCardFirst() {
        int balanceFirstCard = dashboardPage.getCardBalance(0);
        int balanceSecondCard = dashboardPage.getCardBalance(1);
        TransferPage transferPage = dashboardPage.transfer(1);
        transferPage.transferMoney(userData, 1000, 0);
        dashboardPage.reload();
        dashboardPage.newBalance(0, balanceFirstCard - 1000);
        dashboardPage.newBalance(1, balanceSecondCard + 1000);
    }

    @Test
    public void transferUndeLimitFirstCard() {
        int balance = dashboardPage.getCardBalance(0);
        dashboardPage.transfer(0);
        TransferPage transferPage = new TransferPage();
        transferPage.transferMoney(userData, balance + 10000, 1);
        transferPage.getError().shouldBe(visible);
    }

    @Test
    public void transferUndeLimitSecondCard() {
        int balance = dashboardPage.getCardBalance(1);
        dashboardPage.transfer(1);
        TransferPage transferPage = new TransferPage();
        transferPage.transferMoney(userData, balance + 10000, 0);
        transferPage.getError().shouldBe(visible);

    }

    @Test
    public void noNumberCard() {
        int balance = dashboardPage.getCardBalance(0);
        dashboardPage.transfer(0);
        TransferPage transferPage = new TransferPage();
        transferPage.transferMoney(userData, balance + 10000, 2);
        transferPage.getError().shouldBe(visible);

    }

    @Test
    public void transferOneMilion() {
        int expected = dashboardPage.getCardBalance(1) + 1_000_000;
        dashboardPage.transfer(0);
        TransferPage transferPage = new TransferPage();
        transferPage.transferMoney(userData, 1_000_000, 0);
        dashboardPage.reload();
        dashboardPage.newBalance(1, expected);
    }
}
