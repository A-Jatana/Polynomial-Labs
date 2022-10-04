package lab2;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Driver { 
    public static void main(String [] args) throws IOException  {  
          
          double [] c1 = {6,-2,5};
          int [] e1 = {0,1,3}; 
          Polynomial p1 = new Polynomial(c1, e1);
          double [] c2 = {5,-3,7}; 
          int [] e2 = {0,2,8};
          Polynomial p2 = new Polynomial(c2, e2); 
          double [] c3 = {4,2}; 
          int [] e3 = {1,2};
          Polynomial p3 = new Polynomial(c3, e3); 
          double [] c4 = {8,2}; 
          int [] e4 = {0,1};
          Polynomial p4 = new Polynomial(c4, e4); 
          double [] c5 = {1, 1};
          int [] e5 = {1, 0};
          Polynomial p5 = new Polynomial(c5, e5);
          double [] c6 = {1, -1};
          int [] e6 = {1, 0};
          Polynomial p6 = new Polynomial(c6, e6);
          double [] c7 = {5,2,1};
          int [] e7 = {0,1,2}; 
          Polynomial p7 = new Polynomial(c7, e7);
          double [] c8 = {2,3,4};
          int [] e8 = {0,2,4}; 
          Polynomial p8 = new Polynomial(c8, e8);
          File expression = new File("C:\\Users\\amito\\expression.txt");
          Polynomial p9 = new Polynomial(expression);


          Polynomial s = p1.add(p2); 
          Polynomial t = p1.multiply(p2);

          System.out.println(Arrays.toString(t.poly)); 
          System.out.println(Arrays.toString(t.expo));
          System.out.println(p1.evaluate(3)); 
          System.out.println(Arrays.toString(p9.poly)); 
          System.out.println(Arrays.toString(p9.expo));
          p5.saveToFile("check.txt");
          if(p4.hasRoot(-4)) 
           System.out.println("This is a root of s"); 
          else 
          System.out.println("This is not a root of s"); 
         } 
    }
