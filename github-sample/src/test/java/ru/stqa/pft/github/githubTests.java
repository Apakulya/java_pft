package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class githubTests {
  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("dc9f6e389d71b2584ba15e34311796ebad9a6814");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("Apakulya", "java_pft")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String,String>().build())){
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
