package ru.stqa.pft.sandbox;

public class PointCalculator {
  public static void main(String[] args)
  {
    //Point p1 = new Point(1,2);
    //Point p2 = new Point (3,4);
    //System.out.println("Расстояние между точками: " + distance(p1,p2));
    Point p = new Point(1,2,3,4);
    System.out.println("Расстояние между точками: " + p.distance());
  }
  //public static double distance(Point p1, Point p2)
  // {
  //  return Math.sqrt(Math.pow((p2.x-p1.x),2)+Math.pow((p2.y-p1.y),2));
  //}
}
