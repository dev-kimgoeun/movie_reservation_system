package com.zerok.reserv;

import java.io.IOException;
import java.util.ArrayList;

public class AdminMenu extends AbstractMenu {
    private static final AdminMenu instance = new AdminMenu(null);
    private static final String ADMIN_MENU_TEXT =
            """
                    1: 영화 등록하기
                    2: 예매 목록보기
                    3: 예매 삭제하기
                    4: 메인 메뉴로 이동
                                     
                    메뉴를 선택하세요:\s""";

    private AdminMenu(Menu prevMenu){
        super(ADMIN_MENU_TEXT, prevMenu);
    }

    public static AdminMenu getInstance(){
        return instance;
    }

    public Menu next(){
        switch (scanner.nextLine()){
            case "1":
                createMovie();
                return this;
            case "2":
                printAllMovies();
                return this;
            case "3":
                deleteMovie();
                return this;
            case "b": return prevMenu;
            default:return this;
        }
    }

    private void printAllMovies(){
        try{
            ArrayList<Movie> movies = Movie.findAll();
            System.out.println();
            for(int i=0; i<movies.size(); i++){
                System.out.printf("%s\n", movies.get(i).toString());
            }
        } catch (IOException e){
            System.out.println("데이터 접근에 실패하였습니다.");
        }
    }

    private void createMovie(){
        System.out.println("제목: ");
        String title = scanner.nextLine();
        System.out.println("장르: ");
        String genre = scanner.nextLine();
        Movie movie = new Movie(title, genre);
        try{
            movie.save();
            System.out.println(">> 등록되었습니다.");
        } catch (IOException e){
            System.out.println(">> 실패하였습니다.");
        }
    }

    private void deleteMovie(){
        printAllMovies();
        System.out.print("삭제할 영화를 선택하세요 : ");
        try{
            Movie.delete(scanner.nextLine());
            System.out.println(">> 삭제되었습니다.");
        } catch (IOException e){
            System.out.println(">> 삭제에 실패하였습니다.");
        }
    }
}
