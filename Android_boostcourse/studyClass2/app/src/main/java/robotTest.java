public class robotTest {
    public static void main(String[ ] args){
        robot r1=new robot();
        robot r2=new robot();

        r1.name="마징가";
        r1.moveR();
        r1.showMe();

        r2.name="짭가";
        r2.moveL();
        r2.showMe();
    }
}
