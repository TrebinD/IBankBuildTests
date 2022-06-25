import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IbankBuildTest {
    DashboardPage dashboardPage = new DashboardPage();
    UserData userData = new UserData();
    VereficationPage vereficationPage = new VereficationPage();
    TransferPage transferPage = new TransferPage();


    @BeforeEach
    public void Autotenification() {
        open("http://localhost:9999/");
        AuthorizationPage authorizationPage = new AuthorizationPage();
        authorizationPage.login(userData);
        vereficationPage.verefication(userData);
    }

    @Test
    public void transferByCardFirstToCardSecond() {
        String expected = String.valueOf(dashboardPage.getCardBalance(0) - 1000);
        dashboardPage.transfer(1);
        transferPage.transferMoney(userData, 1000, 0);
        String actual = String.valueOf(dashboardPage.getCardBalance(0));
        assertEquals(expected, actual);
    }

    @Test
    public void transferByCardSecondToCardFirst() {
        String expected = String.valueOf(dashboardPage.getCardBalance(0) + 1000);
        dashboardPage.transfer(0);
        transferPage.transferMoney(userData, 1000, 1);
        String actual = String.valueOf(dashboardPage.getCardBalance(0));
        assertEquals(expected, actual);
    }

    @Test
    public void transferUndeLimitFirstCard() {
        int balance = dashboardPage.getCardBalance(0);
        dashboardPage.transfer(0);
        transferPage.transferMoney(userData, balance + 10000, 1);
        transferPage.getError().shouldBe(visible);
    }

    @Test
    public void transferUndeLimitSecondCard() {
        int balance = dashboardPage.getCardBalance(1);
        dashboardPage.transfer(1);
        transferPage.transferMoney(userData, balance + 10000, 0);
        transferPage.getError().shouldBe(visible);

    }

    @Test
    public void noNumberCard() {
        int balance = dashboardPage.getCardBalance(0);
        dashboardPage.transfer(0);
        transferPage.transferMoney(userData, balance + 10000, 2);
        transferPage.getError().shouldBe(visible);

    }

    @Test
    public void transferOneMilion() {
        String expected = String.valueOf(dashboardPage.getCardBalance(1) + 1_000_000);
        dashboardPage.transfer(0);
        transferPage.transferMoney(userData, 1_000_000, 0);
        String actual = String.valueOf(dashboardPage.getCardBalance(1));
        assertEquals(expected, actual);
    }


}
