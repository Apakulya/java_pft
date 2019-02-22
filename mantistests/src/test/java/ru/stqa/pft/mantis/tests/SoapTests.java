package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {
  private Issue Issue;
  @BeforeTest
  public void verifyOpenIssues() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.Soap().getProjects();
    Set<Issue> issues = app.Soap().getIssues(projects.iterator().next().getId());
    for (Issue issue : issues) {
     skipIfNotFixed(issue.getId());
   }
  }
  @Test
  public void testGetProjects() throws MalformedURLException, javax.xml.rpc.ServiceException, RemoteException {
    Set<Project> projects = app.Soap().getProjects();
    Set<Issue> issues = app.Soap().getIssues(projects.iterator().next().getId());
    for (Issue issue : issues) {
      skipIfNotFixed(issue.getId());
    }
    System.out.println(projects.size());
    for (Project project : projects){
      System.out.println(project.getName());
    }
  }
  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.Soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue").
            withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.Soap().addIssue(issue);
    Assert.assertEquals(issue.getSummary(), created.getSummary());
  }
}
