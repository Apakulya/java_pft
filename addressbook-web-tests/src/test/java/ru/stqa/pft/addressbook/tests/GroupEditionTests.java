package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupEditionTests extends TestBase {
  @Test
  public void testGroupEditionTests() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().editGroup();
    app.getGroupHelper().fillGroupForm(new GroupData("666", "666", "666"));
    app.getGroupHelper().submitGroupEdition();
    app.getGroupHelper().returntoGroupPage();
  }
}
