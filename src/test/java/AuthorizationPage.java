import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$x;

@Data
public class AuthorizationPage {
   private SelenideElement loginInput = $x("//span[@data-test-id=\"login\"]//input");
   private SelenideElement passwordInput = $x("//span[@data-test-id=\"password\"]//input");
   private SelenideElement input = $x("//button[@data-test-id=\"action-login\"]");

   public VereficationPage login (UserData userData){
       loginInput.val(userData.getLogin());
       passwordInput.val(userData.getPassword());
       input.click();
       return new VereficationPage();
   }
}
