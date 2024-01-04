package com.zerok.reserv;

class MainMenu extends AbstractMenu{
    private static final MainMenu instance = new MainMenu(null);
    private static final String MAIN_MENU_TEXT =
            "1: 영화예매하기\n" +
            "2: 예매 확인하기\n" +
            "3: 예매 취소하기\n" +
            "4: 관리자 메뉴로 이동\n" +
            "q: 종료\n\n" +
            "메뉴를 선택하세요: ";

    private MainMenu(Menu PreMenu){
        super(MAIN_MENU_TEXT, PreMenu);
    }
    public static MainMenu getInstance(){
        return instance;
    }

    public Menu next(){
        switch (scanner.nextLine()){
            case "q" : return preMenu;
            default:return this;
        }
    }
}
