package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
  private ApplicationManager app;
  private IssueData IssueData;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects() throws MalformedURLException, RemoteException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
    return Arrays.asList(projects).stream().map((p) -> new Project().
            withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
  }
  public Set<Issue> getIssues(int projectId) throws MalformedURLException, RemoteException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
   IssueData[] issues = mc.mc_project_get_issues("administrator", "root",BigInteger.valueOf(projectId),BigInteger.valueOf(-1),BigInteger.valueOf(-1));
    return Arrays.asList(issues).stream().map((i) -> new Issue().
            withId(i.getId().intValue()).withResolution(i.getResolution().getName()).withSummary(i.getSummary())).collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("mantisConnectPort.URL")));
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueID = mc.mc_issue_add("administrator", "root", issueData);
    IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueID);
    return new Issue().withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary())
                    .withDescription(createdIssueData.getDescription())
                    .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                            .withName(createdIssueData.getProject().getName()));

  }

  public Issue getIssueByID(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    IssueData issue = mc.mc_issue_get("administrator", "root",BigInteger.valueOf(issueId));
    return  new Issue().withId(issue.getId().intValue()).withSummary(issue.getSummary())
            .withResolution(issue.getResolution().getName());
  }
}
