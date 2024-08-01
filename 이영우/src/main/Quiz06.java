package main;

public class Quiz06 {

 
    static class Pen {
        protected int thickness;    
        protected int remainingInk; 
        
        // 기본 생성자
        public Pen(int thickness, int remainingInk) {
            this.thickness = thickness;
            this.remainingInk = remainingInk;
        }
        
     
        @Override
        public String toString() {
            return "펜의 굵기: " + thickness + ", 남은 양: " + remainingInk + "%";
        }
    }

  
    static class BallpointPen extends Pen {
        private String color; // 볼펜의 색상

       
        public BallpointPen(int thickness, int remainingInk, String color) {
            super(thickness, remainingInk);
            this.color = color;
        }
        
       
        @Override
        public String toString() {
            return "볼펜 [색상: " + color + ", " + super.toString() + "]";
        }
    }


    static class FountainPen extends Pen {
        private String brand; 

     
        public FountainPen(int thickness, int remainingInk, String brand) {
            super(thickness, remainingInk);
            this.brand = brand;
        }
        
     
        @Override
        public String toString() {
            return "만년필 [브랜드: " + brand + ", " + super.toString() + "]";
        }
    }

    public static void main(String[] args) {
    
        BallpointPen ballpointPen = new BallpointPen(2, 100, "파랑");

     
        FountainPen fountainPen = new FountainPen(3, 80, "몽블랑");

       
        System.out.println(ballpointPen);
        System.out.println(fountainPen);
    }
}
