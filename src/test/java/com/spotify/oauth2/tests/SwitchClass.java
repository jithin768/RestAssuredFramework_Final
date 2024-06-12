package com.spotify.oauth2.tests;

public class SwitchClass {
    enum mychar{
        A,
        B;
    }


    public static void main(String[] args) {
        SwitchClass switchClass=new SwitchClass();
        switchClass.switchCheck();

    }

    public void switchCheck(){

        mychar nowchar=mychar.A;

        switch (nowchar){
            case A:
                System.out.println(nowchar);
                break;
            case B:
                System.out.println(nowchar);
                break;
        }



    }




}
