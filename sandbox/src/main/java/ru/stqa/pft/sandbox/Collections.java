package ru.stqa.pft.sandbox;

import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Phyton", "PHP"};

    List<String> languages = Arrays.asList("Java", "C#", "Phyton", "PHP");

    for (String l : langs) {
      System.out.println("Я хочу выучить " + l);
    }
  }
}