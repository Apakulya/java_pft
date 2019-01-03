package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupEditionTests extends TestBase {
  @Test
  public void testGroupEditionTests() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereGroup()) {
      app.getGroupHelper().CreateGroup(new GroupData("test1", "test2", "test3"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().editGroup();
    app.getGroupHelper().fillGroupForm(new GroupData("666", "666", "666"));
    app.getGroupHelper().submitGroupEdition();
    app.getGroupHelper().returntoGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());
  }
}
