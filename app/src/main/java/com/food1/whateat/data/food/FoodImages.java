package com.food1.whateat.data.food;

import com.food1.whateat.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public enum FoodImages {


    GALBITANG("갈비탕", R.drawable.food_galbitang),
    GAMJATANG("감자탕", R.drawable.food_gamjatang),
    GUCKBAP("국밥", R.drawable.food_gukbap),
    GUCKSU("국수", R.drawable.food_guksu),
    KKANPUNGKI("깐풍기", R.drawable.food_kkanpunggi),
    KKOCCGE("꽃게", R.drawable.food_kkoccge),
    KKWOBAROU("꿔바로우", R.drawable.food_kkwobarou),
    NASIGORAENG("나시고랭", R.drawable.food_nasigoraeng),
    NAENGMYEON("냉면", R.drawable.food_naengmyeon),
    DEOPBAP("덮밥", R.drawable.food_deopbap),
    DONKACHEU("돈카츠", R.drawable.food_donkacheu),
    DONGPAYUK("동파육", R.drawable.food_dongpayuk),
    TTOYANGKKUNG("똠양꿍", R.drawable.food_ttomyangkkung),
    RAMEN("라멘", R.drawable.food_ramen),
    RAJANYA("라자냐", R.drawable.food_rajanya),
    RAKSA("락사", R.drawable.food_raksa),
    RIJOTTO("리조또", R.drawable.food_rijotto),
    MARATANG("마라탕", R.drawable.food_maratang),
    MAPADUBU("마파두부", R.drawable.food_mapadubu),
    BOSSAM("보쌈", R.drawable.food_bossam),
    BOKKEUMBAP("볶음밥", R.drawable.food_bokkeumbap),
    BUDAEJJIGAE("부대찌개", R.drawable.food_budaejjigae),
    BURITTO("부리또", R.drawable.food_buritto),
    BUNSIK("분식", R.drawable.food_bunsik),
    BULGOGI("불고기", R.drawable.food_bulgogi),
    BIBIMBAP("비빔밥", R.drawable.food_bibimbap),
    BIPEUSEUTYU("비프스튜", R.drawable.food_bipeuseutyu),
    SAMGYEOPSAL("삼겹살", R.drawable.food_samgyeopsal),
    SAMGYETANG("삼계탕", R.drawable.food_samgyetang),
    SAENDEUWICHI("샌드위치", R.drawable.food_saendeuwichi),
    SAELLEODEU("샐러드", R.drawable.food_saelleodeu),
    SAENGSEONGUI("생선구이", R.drawable.food_saengseongui),
    SYABEUSYABEU("샤브샤브", R.drawable.food_syabeusyabeu),
    SOBA("소바", R.drawable.food_soba),
    SOMTTAM("솜땀", R.drawable.food_somttam),
    SEUTEIKEU("스테이크", R.drawable.food_seuteikeu),
    SSALGUKSU("쌀국수", R.drawable.food_ssalguksu),
    YANGKKOCHI("양꼬치", R.drawable.food_yangkkochi),
    YANGJANGPI("양장피", R.drawable.food_yangjangpi),
    UDONG("우동", R.drawable.food_udong),
    WOLNAMSSAM("월남쌈", R.drawable.food_wolnamssam),
    YUKGAEJANG("육개장", R.drawable.food_yukgaejang),
    JEON("전", R.drawable.food_jeon),
    JEYUKBOKKEUM("제육볶음", R.drawable.food_jeyukbokkeum),
    JOKBAL("족발", R.drawable.food_jokbal),
    JUK("죽", R.drawable.food_juk),
    JJAJJANGMYEON("짜장면", R.drawable.food_jjajjangmyeon),
    JJAMPPONG("짬뽕", R.drawable.food_jjamppong),
    JJIMTALK("찜닭", R.drawable.food_jjimtalk),
    CHOBAP("초밥", R.drawable.food_chobap),
    CHIKIN("치킨", R.drawable.food_chikin),
    KARE("카레", R.drawable.food_kare),
    KEBAP("케밥", R.drawable.food_kebap),
    TAKOYAKKI("타코야끼", R.drawable.food_takoyakki),
    TOSEUTEU("토스트", R.drawable.food_toseuteu),
    PASEUTA("파스타", R.drawable.food_paseuta),
    PASTAI("팟타이", R.drawable.food_pastai),
    PIJA("피자", R.drawable.food_pija),
    PILLAPEU("필라프", R.drawable.food_pillapeu),
    HAEMBEOGEO("햄버거", R.drawable.food_haembeogeo),
    HOE("회", R.drawable.food_hoe),


    ;


    private final FoodImage foodImage;

    FoodImages(FoodImage foodImage) {
        this.foodImage = foodImage;
    }

    FoodImages(String name, int id) {
        this.foodImage = new FoodImage(name, id);
    }

    public FoodImage getFoodImage() {
        return foodImage;
    }

    public static List<FoodImage> getRandomFoodImages(int amount) {
        List<FoodImage> allFoodImages = new ArrayList<>();
        for (FoodImages foodEnum : values()) {
            allFoodImages.add(foodEnum.getFoodImage());
        }
        Collections.shuffle(allFoodImages);
        return allFoodImages.subList(0, amount);
    }
}
