package mini13;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

class Country {
    String country; // 나라
    String capital;   /// 수도

    public Country(String country, String capital) {
        this.country = country;
        this.capital = capital;
    }
}

public class WorldQuiz {
    public static void main(String[] args){
        Vector<Country> country = new Vector<Country>(8);
        Scanner sc = new Scanner(System.in);

        country.add(new Country("스위스", "베른"));
        country.add(new Country("태국", "방콕"));
        country.add(new Country("네덜란드", "암스테르담"));
        country.add(new Country("러시아", "모스크바"));
        country.add(new Country("몽골", "올란바토르"));
        country.add(new Country("그리스", "아테네"));
        country.add(new Country("베트남", "하노이"));
        country.add(new Country("대한민국", "서울"));

        // 게임 시작
        System.out.println("\"수도맞추기\" 게임을 시작합니다. -1을 입력하면 종료합니다.");
        System.out.println("현재 8개의 수도가 들어 있습니다.");

        int userGuess; // 유저에게 받는 값

        while (true) {
            // 문제 낼 수도 선택하기
            int countryIndex = (int) (Math.random() * 8);    // 인덱스
            String ansCountry = country.get(countryIndex).country;  // 정답 나라
            String ansCity = country.get(countryIndex).capital;     // 정답 수도

            // 선택지에 넣을 수도 인덱스 뽑기 (중복되지 않게)
            Vector<Integer> choicesIndexs = new Vector<Integer>(4); // 선다 인덱스
            choicesIndexs.add(countryIndex); // 정답 인덱스 먼저 넣기
            while (choicesIndexs.size() < 4) {
                int index = (int) (Math.random() * 8);
                if (choicesIndexs.contains(index))
                    continue; // 미리 들어간 값이랑 겹치지 않게
                choicesIndexs.add(index);
            }
            Collections.shuffle(choicesIndexs); // 그리고 선택지 인덱스 한 번 섞어주기

            // 문제 출력
            System.out.println(ansCountry + "?");
            int i = 1;
            for (int index : choicesIndexs) {
                System.out.printf("(%d)%s ", i++, country.get(index).capital);
            }
            System.out.print(":>");

            // 유저 추측값 받기
            int chosenIndex;
            try {
                userGuess = sc.nextInt();
                if (userGuess == -1) {
                    System.out.println("\"수도맞추기\"를 종료합니다...");
                    return; // 값이 -1일 경우 종료
                }
                // 해당 선택지의 인덱스
                chosenIndex = choicesIndexs.get(userGuess - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("제대로 된 값을 사용해주세요!");
                continue;
            }


            // 고른 수도명
            String chosenCity = country.get(chosenIndex).capital;

            // 정답 확인
            if (chosenCity.equals(ansCity)) System.out.println("정답입니다!");
            else System.out.println("오답입니다!");

        }



    }

}
