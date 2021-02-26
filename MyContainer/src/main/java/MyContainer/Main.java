package MyContainer;

public class Main{
    public static void main(String[] args) {
        List l = new List();
        for(int i = 0; i < 10; i++){
            l.insertBack(i);
        }

        for(int i = 0; i < 10; i++){
            l.insertFront(i);
        }

        System.out.println(l);

        l.removeAt(10);
        System.out.println(l);
        l.removeAt(10);
        System.out.println(l);
        l.removeAt(0);
        System.out.println(l);
        l.removeAt(16);
        System.out.println(l);
    }
}