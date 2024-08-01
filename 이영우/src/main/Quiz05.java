
package main;
import java.util.ArrayList;
import java.util.List;

public class Quiz05 {
    
    // Student 클래스 정의
    static class Student {
        private int id;
        private String name;

        // 모든 멤버 변수를 초기화하는 생성자
        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        // 학생의 정보를 반환하는 toString 메소드 오버라이드
        @Override
        public String toString() {
            return "Student [studentID=" + id + ", studentName=" + name + "]";
        }
    }

    public static void main(String[] args) {
        // Student 객체를 저장할 리스트 생성
        List<Student> studentList = new ArrayList<>();

        // 학생 객체 생성 및 리스트에 추가
        studentList.add(new Student(1001, "둘리"));
        studentList.add(new Student(1002, "또치"));
        studentList.add(new Student(1003, "도우너"));

        // 리스트에 저장된 모든 학생 정보 출력
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
	 





