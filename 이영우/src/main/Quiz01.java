package main;

public class Quiz01 {
    public static void main(String[] args) {
       
        int mathScore = 90;
        int englishScore = 70;
        int koreanScore = 100;

       
        int totalScore = mathScore + englishScore + koreanScore;

      
        double averageScore = totalScore / 3.0;

        System.out.println("총점: " + totalScore);
        System.out.println("평균: " + averageScore);
    }
}
