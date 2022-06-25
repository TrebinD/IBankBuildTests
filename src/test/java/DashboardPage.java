import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;


import static com.codeborne.selenide.Selenide.*;
import static java.lang.Integer.valueOf;

@Data
public class DashboardPage {
    private ElementsCollection buttonTopUp = $$x("//button[@data-test-id=\"action-deposit\"]");
    private SelenideElement buttonReload = $x("//button[@data-test-id=\"action-reload\"]");
    private ElementsCollection cards = $$x("//li[@class=\"list__item\"]");
    private SelenideElement getBalanceFirst = $x("//*[@id=\"root\"]/div/ul/li[1]/div/text()[3]");


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
}
