package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreationTests() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("Тест2");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1)); //хэширование - предварительная проверка при помощи более быстрой операции
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withAdded
            (group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }
  @Test
  public void testbadGroupCreationTests() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("Тест2'");
    app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));

  }
}
