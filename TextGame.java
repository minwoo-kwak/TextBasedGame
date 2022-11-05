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

        // ȭ�� ������ �� ������
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
        int past_x = x;     // ���� ��ǥ ����
        int past_y = y;

        // �ݹ� Ȯ���� x��ǥ Ȥ�� y��ǥ �̵�
        if((Math.random() - 0.5) >= 0){
            x += (Math.random() - 0.5) > 0 ? 1 : -1;
        } else {
            y += (Math.random() - 0.5) > 0 ? 1 : -1;
        }

        // ����� ������ �� ������ ��
        if(x < 1) ++x;
        else if(17 < x) --x;
        if(y < 1) ++y;
        else if(8 < y) --y;

        // Monster�� Gold�� �� �Ե��� ��
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
    // Character�� Monster�� ��ġ�� ��ġ�� �ʵ��� ����.
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

    // �� ����
    static void printMap(Character c, Monster m, Gold g) {

        int victory = 0;    // �¸� ����. 1�� �¸�, -1�� �й�

        // �� �׵θ�
        printLine();

        // �� �׵θ� �� Sprite ����
        // (x, y) == (i, k)
        for(int i=1; i<=8; i++){
            System.out.print("#");
            for(int k=1; k<=17; k++){
                // ���� ��ǥ�� Character ����
                if((c.x == k) & (c.y == i) & !((c.x == m.x) & (c.y == m.y))){
                    // Character�� Gold�� ������ �¸�
                    if((c.x == g.x) & (c.y == g.y) & (victory != 1)){
                        System.out.print("��");
                        victory = 1;
                        continue;
                    } // Gold�� ������ Character �׳� ǥ��
                    System.out.print("@");
                } // ���� ��ǥ�� Gold ǥ��
                else if((g.x == k) & (g.y == i)) {
                    System.out.print("G");
                } // ���� ��ǥ�� Monster ǥ��
                else if((m.x == k) & (m.y == i)) {
                    // Character�� Monster�� ������ �й�
                    if((c.x == m.x) & (c.y == m.y) & (victory != -1)){
                        System.out.print("X");
                        victory = -1;
                        continue;
                    } // ����ġ�� ������ Monster �׳� ǥ��
                    System.out.print("M");
                } // �ƹ��͵� �ƴϸ� ���� ǥ��
                else {
                    System.out.print(" ");
                }
            } // �׵θ� �ݾ��ֱ�
            System.out.println("#");
        }

        // �Ʒ� �׵θ�
        printLine();

        // �¸����� �Ǵ�
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
            // �ʺ��� ǥ��
            TextGame.printMap(c, m, g);

            // �÷��̾� ���� �Է� �ޱ�
            char input = 'c';
            while (input != 'h' & input != 'j' & input != 'k' & input != 'l'){
                System.out.print("����(h), ����(j), �Ʒ���(k), ������(l): ");
                input = sc.next().charAt(0);
            }
            c.move(input);
            m.move(g);

        }


    }
}
