package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreationTests() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().CreateGroup(new GroupData("test1", null,null));
  }

}
