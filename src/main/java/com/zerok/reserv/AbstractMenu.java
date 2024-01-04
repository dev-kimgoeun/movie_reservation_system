package com.zerok.reserv;

import java.util.Scanner;

abstract class AbstractMenu implements Menu{
    protected String menuText;
    protected Menu preMenu;
    protected static final Scanner scanner = new Scanner(System.in);

    public AbstractMenu(String menuText, Menu preMenu){
        this.menuText = menuText;
        this.preMenu = preMenu;
    }

    public void print(){
        System.out.println("\n" + menuText); //메뉴출력
    }

    public void setPreMenu(Menu preMenu){
        this.preMenu = preMenu;
    }
}
