


package main;

public class Quiz03 {
    public static void main(String[] args) {
        int sum = 0;
        int lastNumber = 0;


        for (int i = 1; i <= 100; i++) {
      
            if (sum + i > 300) {
                break; 
            }

            sum += i;       
            lastNumber = i; 
        }

        // 결과 출력
        System.out.println("i: " + lastNumber);
        System.out.println("sum: " + sum);
    }
}

