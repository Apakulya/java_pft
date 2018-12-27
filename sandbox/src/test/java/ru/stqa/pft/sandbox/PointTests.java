package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testPointCalculator()
        {
          Point p = new Point(1,2,3,4);
          Assert.assertEquals(p.distance(),2.8284271247461903);
        }
}
