package mini13;

import java.util.Random;
import java.util.Scanner;

public class HangmanTest {
    private static String[] words = {"java", "count", "school", "book", "student", "programmer"};
    private static int index = (new Random()).nextInt(words.length);
    private static String solution = words[index];
    private static StringBuffer answer = new StringBuffer(solution.length());
    private static Scanner sc = new Scanner(System.in);

    public static void printState() {
        System.out.print("현재의 상태: ");

        for (int i = 0; i < answer.length(); i++){
            String solutionPiece = solution.substring(i, i+1);
            String answerPiece = answer.substring(i, i+1);
            if(solutionPiece.equals(answerPiece)) {
                System.out.print(solutionPiece);
            } else {
                answer.setLength(answer.length() - 1);
                i--;
            }
        }

        for (int i = 0; i < solution.length() - answer.length(); i++){
            System.out.print("_");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("행맨 게임에 오신 것을 환영합니다.");


        while (true) {
            printState();
            if (solution.equals(answer.toString())){
                System.out.println("##############");
                System.out.println("# 정답입니다~! #");
                System.out.println("##############");
                break;
            }

            System.out.print("글자를 추측하시오: ");
            answer.append(sc.nextLine());
            System.out.println();

        }
    }
}
