
package main;

public class Quiz04 {
 
    public static int calculateSum(int[] array) {
       
        if (array.length < 3) {
            return -999;
        }

        int sum = 0;
        // 배열의 모든 요소를 더함
        for (int num : array) {
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
      
        int[] array1 = {2, 4, 6, 8, 10};
       
        int[] array2 = {2, 4};


        int result1 = calculateSum(array1);
        System.out.println("첫 번째 배열의 합계는: " + result1);

        int result2 = calculateSum(array2);
        System.out.println("두 번째 배열의 합계는: " + result2);
    }
}
