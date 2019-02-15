package ru.stqa.pft.mantis.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class ChangePasswordByAdmin extends TestBase {
  @BeforeMethod
  public void StartMailServer() {
    app.mail().start();
  }

  @Test
  public void changePassword() throws IOException, MessagingException {
    Users users = app.db().users();
    UserData resetPasswordUser = users.iterator().next();
    String username = resetPasswordUser.getName();
    String email = resetPasswordUser.getEmail();
    String newpassword = "666";
    app.login().start("administrator","root");
    app.manage().startResetUserPassword(username);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String ConfirmationLink = findConfirmationLink(mailMessages,email);
    app.manage().stopResetUserPassword(ConfirmationLink, newpassword);
    app.newSession().login(username,newpassword);
    assertTrue(app.newSession().login(username,newpassword));
  }
  private String findConfirmationLink(List<MailMessage> mailMessages, String email){
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
  @AfterMethod(alwaysRun = true)
  public void StopMailServer() {
    app.mail().stop();
  }
}
