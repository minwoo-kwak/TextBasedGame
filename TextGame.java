package week10;

import java.util.Scanner;
import static java.lang.System.exit;

class Sprite {
    int x, y;   //  x-coordinate,  y-coordinate
}

class Character extends Sprite {
    public Character() {
        x = y = 3;
    }

    void move(char c){
        if( c == 'h') --x;          // Left
        else if ( c == 'j') --y;    // Up
        else if ( c == 'k') ++y;    // Down
        else if ( c == 'l') ++x;    // Right

        // 화면 밖으로 못 나가게
        if(x < 1) ++x;
        else if(17 < x) --x;
        if(y < 1) ++y;
        else if(8 < y) --y;
    }
}
class Monster extends Sprite {
    public Monster() {
        x = y = 7;
    }
    void move(Gold g){
        int past_x = x;     // 이전 좌표 저장
        int past_y = y;

        // 반반 확률로 x좌표 혹은 y좌표 이동
        if((Math.random() - 0.5) >= 0){
            x += (Math.random() - 0.5) > 0 ? 1 : -1;
        } else {
            y += (Math.random() - 0.5) > 0 ? 1 : -1;
        }

        // 경기장 밖으로 못 나가게 함
        if(x < 1) ++x;
        else if(17 < x) --x;
        if(y < 1) ++y;
        else if(8 < y) --y;

        // Monster가 Gold를 못 먹도록 함
        if(g.x == x & g.y == y) {
            if(past_x == g.x){
                y += past_y > g.y ? 1 : -1;
            } else if(past_y == g.y){
                x += past_x > g.x ? 1 : -1;
            }
        }
    }
}
class Gold extends Sprite {
    // Character와 Monster의 위치와 겹치지 않도록 랜덤.
    public Gold() {
        do {
            x = (int)(Math.random() * 17 + 1);
            y = (int)(Math.random() * 8 + 1);
        }while((x == 3 & y == 3) | (x == 7 & y ==7));

    }
}

public class TextGame {
    static void printLine() {
        for(int i=0; i<19; i++) System.out.print("#");
        System.out.println();
    }

    // 맵 생성
    static void printMap(Character c, Monster m, Gold g) {

        int victory = 0;    // 승리 여부. 1은 승리, -1은 패배

        // 윗 테두리
        printLine();

        // 옆 테두리 및 Sprite 생성
        // (x, y) == (i, k)
        for(int i=1; i<=8; i++){
            System.out.print("#");
            for(int k=1; k<=17; k++){
                // 현재 좌표에 Character 생성
                if((c.x == k) & (c.y == i) & !((c.x == m.x) & (c.y == m.y))){
                    // Character가 Gold를 만나면 승리
                    if((c.x == g.x) & (c.y == g.y) & (victory != 1)){
                        System.out.print("★");
                        victory = 1;
                        continue;
                    } // Gold가 없으면 Character 그냥 표시
                    System.out.print("@");
                } // 현재 좌표에 Gold 표시
                else if((g.x == k) & (g.y == i)) {
                    System.out.print("G");
                } // 현재 좌표에 Monster 표시
                else if((m.x == k) & (m.y == i)) {
                    // Character와 Monster가 만나면 패배
                    if((c.x == m.x) & (c.y == m.y) & (victory != -1)){
                        System.out.print("X");
                        victory = -1;
                        continue;
                    } // 마주치지 않으면 Monster 그냥 표시
                    System.out.print("M");
                } // 아무것도 아니면 공백 표시
                else {
                    System.out.print(" ");
                }
            } // 테두리 닫아주기
            System.out.println("#");
        }

        // 아래 테두리
        printLine();

        // 승리여부 판단
        if(victory == -1 ){
            System.out.println("You Lose...");
            exit(0);
        } else if (victory == 1){
            System.out.println("You Win !");
            exit(0);
        }
    }
    // Main class
    public static void main(String[] args){
        // Generate Sprites
        Character c = new Character();
        Monster m = new Monster();
        Gold g = new Gold();
        Scanner sc = new Scanner(System.in);

        while(true){
            // 맵부터 표시
            TextGame.printMap(c, m, g);

            // 플레이어 방향 입력 받기
            char input = 'c';
            while (input != 'h' & input != 'j' & input != 'k' & input != 'l'){
                System.out.print("왼쪽(h), 위쪽(j), 아래쪽(k), 오른쪽(l): ");
                input = sc.next().charAt(0);
            }
            c.move(input);
            m.move(g);

        }


    }
}
