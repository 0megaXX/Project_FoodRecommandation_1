package com.food1.whateat.presentation.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodData {
    public static Map<String, List<String>> getFoodMap() {
        Map<String, List<String>> foodMap = new HashMap<>();

        foodMap.put("감자탕", new ArrayList<String>() {{
            add("짠맛"); add("매운맛"); add("담백한");add("구수한"); add("시원한"); add("뜨거운"); add("국물"); add("돼지"); add("한식");
        }});
        foodMap.put("비빔밥", new ArrayList<String>() {{
            add("밥"); add("짠맛"); add("뜨거운"); add("한식");
        }});
        foodMap.put("물냉면", new ArrayList<String>() {{
            add("면"); add("순한맛"); add("차가운"); add("국물"); add("한식");
        }});
        foodMap.put("비빔냉면", new ArrayList<String>() {{
            add("면"); add("단맛"); add("매운맛"); add("차가운"); add("한식");
        }});
        foodMap.put("떡볶이", new ArrayList<String>() {{
            add("단맛"); add("매운맛"); add("뜨거운"); add("볶은"); add("한식");
        }});
        foodMap.put("로제떡볶이", new ArrayList<String>() {{
            add("단맛"); add("순한맛"); add("담백한"); add("뜨거운"); add("볶은"); add("한식");
        }});
        foodMap.put("김치전", new ArrayList<String>() {{
             add("짠맛"); add("매운맛"); add("뜨거운"); add("구운"); add("기름진"); add("한식");
        }});
        foodMap.put("전복죽", new ArrayList<String>() {{
            add("밥"); add("짠맛"); add("순한맛"); add("담백한"); add("뜨거운"); add("한식"); add("해산물");
        }});
        foodMap.put("호박죽", new ArrayList<String>() {{
            add("밥"); add("단맛"); add("담백한"); add("뜨거운"); add("한식");
        }});
        foodMap.put("단팥죽", new ArrayList<String>() {{
            add("밥"); add("단맛"); add("순한맛"); add("느끼한"); add("뜨거운"); add("한식");
        }});
        foodMap.put("잔치국수", new ArrayList<String>() {{
            add("면"); add("순한맛"); add("담백한"); add("뜨거운"); add("국물"); add("한식");
        }});
        foodMap.put("비빔국수", new ArrayList<String>() {{
            add("면"); add("짠맛"); add("매운맛"); add("차가운"); add("한식");
        }});
        foodMap.put("갈비탕", new ArrayList<String>() {{
             add("순한맛"); add("담백한"); add("구수한"); add("뜨거운"); add("기름진"); add("국물"); add("돼지"); add("소"); add("한식");
        }});
        foodMap.put("삼겹살", new ArrayList<String>() {{
            add("뜨거운"); add("느끼한"); add("구운"); add("기름진"); add("돼지"); add("한식");
        }});
        foodMap.put("삼계탕", new ArrayList<String>() {{
             add("담백한"); add("뜨거운"); add("기름진");add("시원한"); add("국물"); add("닭"); add("한식");
        }});
        foodMap.put("보쌈", new ArrayList<String>() {{
            add("담백한"); add("뜨거운"); add("기름진"); add("돼지"); add("한식");
        }});
        foodMap.put("족발", new ArrayList<String>() {{
             add("짠맛"); add("기름진"); add("돼지"); add("한식");
        }});
        foodMap.put("찜닭", new ArrayList<String>() {{
            add("짠맛");add("단맛"); add("매운맛"); add("담백한"); add("뜨거운"); add("찐"); add("기름진"); add("국물"); add("닭"); add("한식");
        }});
        foodMap.put("부대찌개", new ArrayList<String>() {{
            add("짠맛");add("단맛"); add("담백한"); add("뜨거운"); add("국물"); add("돼지"); add("한식");
        }});
        foodMap.put("돼지불백", new ArrayList<String>() {{
            add("밥");add("짠맛");add("단맛");add("순한맛"); add("뜨거운"); add("볶은"); add("기름진"); add("돼지"); add("한식");
        }});
        foodMap.put("갈비", new ArrayList<String>() {{
            add("짠맛");add("단맛"); add("담백한"); add("뜨거운"); add("구운"); add("기름진"); add("돼지"); add("한식");
        }});
        foodMap.put("소불고기", new ArrayList<String>() {{
            add("짠맛");add("단맛"); add("뜨거운"); add("구운"); add("소"); add("한식");
        }});
        foodMap.put("한우", new ArrayList<String>() {{
            add("느끼한");add("담백한"); add("뜨거운"); add("구운"); add("기름진");add("소"); add("한식");
        }});
        foodMap.put("돼지국밥", new ArrayList<String>() {{
            add("밥");  add("담백한"); add("구수한"); add("뜨거운"); add("국물"); add("돼지"); add("한식");
        }});
        foodMap.put("소머리국밥", new ArrayList<String>() {{
            add("밥"); add("담백한"); add("구수한"); add("뜨거운"); add("국물"); add("소"); add("한식");
        }});
        foodMap.put("순대국밥", new ArrayList<String>() {{
            add("밥");  add("담백한"); add("구수한");add("뜨거운"); add("국물"); add("돼지"); add("한식");
        }});
        foodMap.put("제육볶음", new ArrayList<String>() {{
             add("짠맛"); add("단맛");add("매운맛"); add("뜨거운"); add("볶은"); add("기름진"); add("돼지"); add("한식");
        }});
        foodMap.put("육개장", new ArrayList<String>() {{
             add("짠맛"); add("매운맛"); add("구수한"); add("시원한"); add("뜨거운"); add("기름진"); add("국물"); add("소"); add("한식");
        }});
        foodMap.put("누룽지탕", new ArrayList<String>() {{
             add("밥");add("순한맛");add("담백한"); add("구수한"); add("뜨거운"); add("국물"); add("한식");
        }});
        foodMap.put("김치찌개", new ArrayList<String>() {{
           add("짠맛"); add("매운맛"); add("시원한"); add("뜨거운"); add("국물"); add("돼지");add("한식");
        }});
        foodMap.put("된장찌개", new ArrayList<String>() {{
            add("짠맛"); add("매운맛"); add("구수한"); add("시원한"); add("뜨거운"); add("국물"); add("한식");
        }});
        foodMap.put("닭도리탕", new ArrayList<String>() {{
             add("짠맛"); add("매운맛"); add("시원한"); add("뜨거운"); add("기름진"); add("국물"); add("닭"); add("한식");
        }});
        foodMap.put("설렁탕", new ArrayList<String>() {{
             add("짠맛"); add("순한맛"); add("담백한"); add("뜨거운"); add("국물"); add("소"); add("한식");
        }});
        foodMap.put("간장게장", new ArrayList<String>() {{
            add("짠맛"); add("단맛"); add("차가운"); add("한식"); add("해산물");
        }});
        foodMap.put("양념게장", new ArrayList<String>() {{
             add("짠맛"); add("단맛");add("매운맛"); add("차가운"); add("한식"); add("해산물");
        }});
        foodMap.put("꽃게탕", new ArrayList<String>() {{
             add("매운맛"); add("시원한"); add("뜨거운"); add("국물"); add("한식"); add("해산물");
        }});
        foodMap.put("조개탕", new ArrayList<String>() {{
             add("순한맛"); add("담백한"); add("시원한"); add("뜨거운"); add("국물"); add("한식"); add("해산물");
        }});
        foodMap.put("조개구이", new ArrayList<String>() {{
            add("순한맛"); add("뜨거운"); add("구운"); add("한식"); add("해산물");
        }});
        foodMap.put("장어구이", new ArrayList<String>() {{
             add("짠맛"); add("순한맛"); add("뜨거운"); add("구운"); add("기름진"); add("생선"); add("한식");
        }});
        foodMap.put("바지락칼국수", new ArrayList<String>() {{
            add("면"); add("순한맛"); add("담백한"); add("시원한"); add("뜨거운"); add("국물"); add("한식"); add("해산물");
        }});
        foodMap.put("라면", new ArrayList<String>() {{
            add("면"); add("짠맛"); add("매운맛"); add("시원한"); add("뜨거운"); add("튀긴"); add("국물");
        }});
        foodMap.put("짜장면", new ArrayList<String>() {{
            add("면"); add("짠맛"); add("단맛"); add("느끼한"); add("뜨거운"); add("볶은"); add("기름진"); add("중식");
        }});
        foodMap.put("간짜장", new ArrayList<String>() {{
            add("면"); add("짠맛"); add("단맛");add("느끼한"); add("뜨거운"); add("볶은"); add("기름진"); add("중식");
        }});
        foodMap.put("사천짜장", new ArrayList<String>() {{
            add("면"); add("짠맛"); add("단맛"); add("매운맛"); add("느끼한"); add("뜨거운"); add("볶은"); add("기름진"); add("중식");add("해산물");
        }});
        foodMap.put("해물짬뽕", new ArrayList<String>() {{
            add("면"); add("매운맛"); add("느끼한"); add("시원한"); add("뜨거운"); add("국물"); add("중식"); add("해산물");
        }});
        foodMap.put("고기짬뽕", new ArrayList<String>() {{
            add("면"); add("매운맛"); add("느끼한"); add("시원한"); add("뜨거운"); add("국물"); add("중식"); add("돼지");
        }});
        foodMap.put("탕수육", new ArrayList<String>() {{
            add("느끼한");add("담백한"); add("뜨거운"); add("튀긴"); add("기름진"); add("돼지"); add("중식");
        }});
        foodMap.put("볶음밥", new ArrayList<String>() {{
            add("밥"); add("짠맛"); add("순한맛"); add("뜨거운"); add("볶은"); add("기름진"); add("중식");
        }});
        foodMap.put("김치볶음밥", new ArrayList<String>() {{
            add("밥"); add("매운맛"); add("뜨거운"); add("볶은"); add("기름진"); add("한식");
        }});
        foodMap.put("깐풍기", new ArrayList<String>() {{
            add("짠맛");add("단맛");  add("매운맛"); add("느끼한"); add("뜨거운"); add("튀긴"); add("볶은"); add("기름진"); add("닭"); add("중식");
        }});
        foodMap.put("깐쇼새우", new ArrayList<String>() {{
             add("짠맛"); add("매운맛"); add("느끼한"); add("뜨거운"); add("튀긴"); add("볶은"); add("기름진"); add("중식"); add("일식"); add("해산물");
        }});
        foodMap.put("마라탕", new ArrayList<String>() {{
            add("면"); add("짠맛"); add("매운맛"); add("느끼한"); add("시원한"); add("뜨거운"); add("기름진"); add("국물"); add("소"); add("중식"); add("해산물");
        }});
        foodMap.put("마라샹궈", new ArrayList<String>() {{
            add("짠맛"); add("매운맛"); add("순한맛"); add("느끼한"); add("담백한"); add("구수한"); add("시원한"); add("뜨거운"); add("기름진"); add("국물"); add("소"); add("중식"); add("해산물");
        }});
        foodMap.put("샤브샤브", new ArrayList<String>() {{
             add("순한맛"); add("담백한"); add("뜨거운"); add("국물"); add("소"); add("일식");
        }});
        foodMap.put("양꼬치", new ArrayList<String>() {{
             add("순한맛"); add("느끼한"); add("뜨거운"); add("구운"); add("기름진"); add("중식");
        }});
        foodMap.put("회", new ArrayList<String>() {{
              add("차가운"); add("생선"); add("일식"); add("해산물");
        }});
        foodMap.put("초밥/스시", new ArrayList<String>() {{
            add("밥"); add("차가운"); add("생선"); add("일식"); add("해산물");
        }});
        foodMap.put("제육덮밥", new ArrayList<String>() {{
            add("밥"); add("짠맛"); add("매운맛"); add("뜨거운"); add("볶은"); add("기름진"); add("돼지"); add("한식");
        }});
        foodMap.put("오징어덮밥", new ArrayList<String>() {{
            add("밥"); add("짠맛"); add("매운맛"); add("뜨거운"); add("볶은"); add("기름진"); add("한식");
        }});
        foodMap.put("카츠동", new ArrayList<String>() {{
            add("밥"); add("짠맛"); add("순한맛"); add("느끼한"); add("뜨거운"); add("튀긴"); add("기름진"); add("돼지"); add("일식");
        }});
        foodMap.put("라멘", new ArrayList<String>() {{
            add("면"); add("짠맛"); add("순한맛"); add("느끼한"); add("담백한"); add("뜨거운"); add("기름진"); add("국물"); add("돼지"); add("일식");
        }});
        foodMap.put("냉모밀", new ArrayList<String>() {{
            add("면"); add("순한맛"); add("차가운"); add("국물"); add("일식");
        }});
        foodMap.put("온모밀", new ArrayList<String>() {{
            add("면"); add("순한맛"); add("뜨거운"); add("국물"); add("일식");
        }});
        foodMap.put("야키소바", new ArrayList<String>() {{
            add("면"); add("짠맛"); add("순한맛"); add("느끼한"); add("뜨거운"); add("볶은"); add("기름진"); add("돼지"); add("일식");
        }});
        foodMap.put("우동", new ArrayList<String>() {{
            add("면"); add("짠맛"); add("순한맛"); add("느끼한"); add("시원한"); add("뜨거운"); add("기름진"); add("국물"); add("일식");
        }});
        foodMap.put("토미토스파게티", new ArrayList<String>() {{
            add("면"); add("단맛"); add("순한맛"); add("뜨거운"); add("볶은"); add("기름진"); add("양식");
        }});
        foodMap.put("까르보나라", new ArrayList<String>() {{
            add("면"); add("단맛"); add("순한맛"); add("느끼한"); add("뜨거운"); add("볶은"); add("기름진"); add("양식");
        }});
        foodMap.put("크림스파게티", new ArrayList<String>() {{
            add("면"); add("단맛"); add("순한맛"); add("느끼한"); add("뜨거운"); add("볶은"); add("기름진"); add("양식");
        }});
        foodMap.put("로제스파게티", new ArrayList<String>() {{
            add("면"); add("단맛"); add("순한맛"); add("느끼한"); add("뜨거운"); add("볶은"); add("기름진"); add("양식"); add("해산물");
        }});
        foodMap.put("해물스파게티", new ArrayList<String>() {{
            add("면"); add("단맛"); add("순한맛"); add("뜨거운"); add("볶은"); add("기름진"); add("양식"); add("해산물");
        }});
        foodMap.put("알리오 에 올리오", new ArrayList<String>() {{
            add("면"); add("순한맛"); add("뜨거운"); add("볶은"); add("기름진"); add("양식");
        }});
        foodMap.put("스테이크", new ArrayList<String>() {{
           add("짠맛"); add("순한맛"); add("뜨거운"); add("구운"); add("기름진"); add("소"); add("양식");
        }});
        foodMap.put("필라프", new ArrayList<String>() {{
            add("밥"); add("짠맛"); add("순한맛"); add("느끼한"); add("뜨거운"); add("볶은"); add("기름진"); add("닭"); add("양식"); add("아시안"); add("해산물");
        }});
        foodMap.put("리소토", new ArrayList<String>() {{
            add("밥"); add("짠맛"); add("순한맛"); add("느끼한"); add("뜨거운"); add("볶은"); add("기름진"); add("양식"); add("해산물");
        }});
        foodMap.put("샐러드", new ArrayList<String>() {{
           add("단맛"); add("순한맛"); add("차가운"); add("닭"); add("양식");
        }});
        foodMap.put("샌드위치", new ArrayList<String>() {{
            add("빵"); add("순한맛"); add("뜨거운"); add("구운"); add("기름진"); add("돼지"); add("양식");
        }});
        foodMap.put("토스트", new ArrayList<String>() {{
            add("빵"); add("단맛"); add("순한맛"); add("뜨거운"); add("양식");
        }});
        foodMap.put("멘보샤", new ArrayList<String>() {{
            add("빵"); add("짠맛"); add("뜨거운"); add("튀긴"); add("기름진"); add("중식"); add("해산물");
        }});
        foodMap.put("후라이드치킨", new ArrayList<String>() {{
             add("순한맛"); add("뜨거운"); add("튀긴"); add("기름진"); add("닭"); add("패스트푸드");
        }});
        foodMap.put("양념치킨", new ArrayList<String>() {{
             add("단맛"); add("매운맛"); add("뜨거운"); add("튀긴"); add("기름진"); add("닭"); add("패스트푸드");
        }});
        foodMap.put("간장치킨", new ArrayList<String>() {{
            add("짠맛"); add("단맛");  add("뜨거운"); add("튀긴"); add("기름진"); add("닭"); add("패스트푸드");
        }});
        foodMap.put("치즈피자", new ArrayList<String>() {{
            add("빵"); add("짠맛"); add("느끼한"); add("뜨거운"); add("구운"); add("기름진"); add("패스트푸드");
        }});
        foodMap.put("불고기피자", new ArrayList<String>() {{
            add("빵"); add("짠맛"); add("단맛"); add("느끼한"); add("뜨거운"); add("구운"); add("기름진"); add("소"); add("패스트푸드");
        }});
        foodMap.put("치킨피자", new ArrayList<String>() {{
            add("빵"); add("짠맛"); add("매운맛"); add("느끼한"); add("뜨거운"); add("구운"); add("기름진"); add("닭"); add("패스트푸드");
        }});
        foodMap.put("쉬림프피자", new ArrayList<String>() {{
            add("빵"); add("짠맛"); add("느끼한"); add("뜨거운"); add("구운"); add("기름진"); add("패스트푸드"); add("해산물");
        }});
        foodMap.put("불고기버거", new ArrayList<String>() {{
            add("빵"); add("단맛");  add("뜨거운"); add("구운"); add("기름진"); add("소"); add("패스트푸드");
        }});
        foodMap.put("새우버거", new ArrayList<String>() {{
            add("빵"); add("짠맛"); add("뜨거운"); add("튀긴"); add("기름진"); add("패스트푸드"); add("해산물");
        }});
        foodMap.put("치킨버거", new ArrayList<String>() {{
            add("빵"); add("짠맛"); add("뜨거운"); add("튀긴"); add("기름진"); add("닭"); add("패스트푸드");
        }});
        foodMap.put("치즈버거", new ArrayList<String>() {{
            add("빵"); add("짠맛"); add("느끼한"); add("담백한"); add("뜨거운"); add("구운"); add("기름진"); add("소"); add("패스트푸드");
        }});
        foodMap.put("카레", new ArrayList<String>() {{
            add("밥"); add("짠맛"); add("매운맛"); add("뜨거운"); add("닭"); add("일식");add("아시안");
        }});
        foodMap.put("쌀국수", new ArrayList<String>() {{
            add("면"); add("순한맛"); add("담백한"); add("뜨거운"); add("소"); add("아시안");
        }});
        foodMap.put("팟타이", new ArrayList<String>() {{
            add("면"); add("느끼한"); add("뜨거운"); add("볶은"); add("기름진"); add("아시안"); add("해산물");
        }});
        foodMap.put("나시고렝", new ArrayList<String>() {{
            add("밥"); add("짠맛"); add("매운맛"); add("느끼한"); add("뜨거운"); add("볶은"); add("기름진"); add("아시안");
        }});
        foodMap.put("타코", new ArrayList<String>() {{
            add("빵"); add("짠맛"); add("순한맛"); add("뜨거운"); add("구운"); add("기름진"); add("생선"); add("양식");
        }});
        foodMap.put("부리토", new ArrayList<String>() {{
            add("빵"); add("짠맛"); add("순한맛"); add("뜨거운");  add("닭"); add("소"); add("양식");
        }});
        foodMap.put("짜장밥", new ArrayList<String>() {{
            add("밥"); add("단맛"); add("순한맛"); add("느끼한"); add("뜨거운"); add("볶은"); add("기름진"); add("중식");
        }});
        foodMap.put("짬뽕밥", new ArrayList<String>() {{
            add("밥"); add("짠맛"); add("매운맛"); add("느끼한"); add("시원한"); add("뜨거운"); add("기름진"); add("국물"); add("중식"); add("해산물");
        }});
        foodMap.put("만두", new ArrayList<String>() {{
            add("순한맛"); add("뜨거운"); add("찐"); add("돼지"); add("중식");
        }});
        foodMap.put("소갈비찜", new ArrayList<String>() {{
             add("짠맛"); add("단맛"); add("뜨거운"); add("찐"); add("기름진"); add("국물"); add("소"); add("한식");
        }});
        foodMap.put("대게찜", new ArrayList<String>() {{
            add("순한맛"); add("느끼한"); add("뜨거운"); add("찐"); add("한식"); add("해산물");
        }});
        foodMap.put("아귀찜", new ArrayList<String>() {{
             add("짠맛"); add("매운맛"); add("시원한"); add("뜨거운"); add("찐"); add("생선"); add("한식"); add("해산물");
        }});
        foodMap.put("돈까스", new ArrayList<String>() {{
             add("느끼한"); add("뜨거운"); add("튀긴"); add("기름진"); add("돼지"); add("일식");
        }});
        foodMap.put("닭갈비", new ArrayList<String>() {{
             add("매운맛"); add("담백한"); add("뜨거운"); add("닭"); add("한식");
        }});

        // 여기에 더 많은 음식을 추가할 수 있습니다.

        return foodMap;

    }
}

