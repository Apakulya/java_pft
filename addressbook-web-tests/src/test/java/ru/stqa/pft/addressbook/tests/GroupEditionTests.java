package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupEditionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("Тест1"));
    }
  }

  @Test
  public void testGroupEditionTests() throws Exception {
    Groups before = app.group().all();
    GroupData editedgroup = before.iterator().next();
    GroupData group = new GroupData().withId(editedgroup.getId()).withName("666").
            withFooter("666").withHeader("666");
    app.group().edit(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(editedgroup).withAdded(group)));
  }


}
