package com.zerok.reserv;

import java.io.IOException;

class MainMenu extends AbstractMenu{
    private static final MainMenu instance = new MainMenu(null);
    private static final String MAIN_MENU_TEXT =
            """
                    1: 영화예매하기
                    2: 예매 확인하기
                    3: 예매 취소하기
                    4: 관리자 메뉴로 이동
                    q: 종료

                    메뉴를 선택하세요:\s""";

    private MainMenu(Menu PrevMenu){
        super(MAIN_MENU_TEXT, PrevMenu);
    }
    public static MainMenu getInstance(){
        return instance;
    }

    public Menu next(){
        switch (scanner.nextLine()){
            case "2":
                checkReservation();
                return this;
            case "4":
                if(! checkAdminPassword()){
                    System.out.println(">> 비밀번호가 틀렸습니다.");
                    return this;
                }
                AdminMenu adminMenu = AdminMenu.getInstance();
                adminMenu.setPrevMenu(this);
                return adminMenu;
            case "q" : return prevMenu;
            default:return this;
        }
    }

    private boolean checkAdminPassword(){
        System.out.println("관리자 비밀번호를 입력하세요 : ");
        return "admin1234".equals(scanner.nextLine());
    }

    private void checkReservation(){
        System.out.println("발급번호를 입력하세요: ");
        try{
            Reservation r = Reservation.findById(scanner.nextLine());
            if(r != null){
                System.out.printf(">> [확인완료] %s\n", r.toString());
            } else {
                System.out.println(">> 예매내역이 없습니다.");
            }
        } catch (IOException e){
            System.out.println(">> 파일 입출력에 문제가 생겼습니다.");
        }
    }
}
