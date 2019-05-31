public class robot {
    int x,y;
    int power,price;
    String name;

    void moveR(){x+=5;}
    void moveL(){x-=5;}
    void showMe(){
        System.out.println("나의 이름은"+name+"입니다.");
        System.out.println(x);
    }
}
