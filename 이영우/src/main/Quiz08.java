package main;


public class Quiz08 {


 interface Calc {
     int add(int a, int b);       
     int substract(int a, int b); 
     int times(int a, int b);     
     int divide(int a, int b);    
 }


 static class Calculator implements Calc {
     private static final int ERROR_CODE = -9999; 
     
     @Override
     public int add(int a, int b) {
         return a + b;
     }

     @Override
     public int substract(int a, int b) {
         return a - b;
     }

     @Override
     public int times(int a, int b) {
         if (b <= 0) {
             return ERROR_CODE; 
         }
         return a * b;
     }

     @Override
     public int divide(int a, int b) {
         if (b == 0 || a < b) {
             return ERROR_CODE; 
         }
         return a / b;
     }
 }


 public static void main(String[] args) {
   
     Calc calc = new Calculator();


     System.out.println(calc.add(10, 5));       
     System.out.println(calc.substract(10, 5)); 
     System.out.println(calc.times(10, 5));     
     System.out.println(calc.divide(10, 5));    

   
     System.out.println(calc.times(10, 0));     
     System.out.println(calc.divide(10, 20));   
 }
}

