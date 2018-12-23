package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    //hello("Настя");
    Square s = new Square(6);
    System.out.println("Площадь квадрата со стороной " + s.l + " =: " + s.area());

    Rectangle r = new Rectangle(4,6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " =: " + r.area());
  }

  public static void hello(String something) {
    System.out.println("Hello" + " " + something + " Have a nice day!");
  }


  public static double area(Rectangle r)
  {
    return r.a*r.b;
  }
}



