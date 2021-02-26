package MyContainer;

public class ListNode {
    private int value;
    private ListNode next;

    public ListNode getNext(){
        return next;
    }

    public int getValue(){
        return value;
    }

    public void setNext(ListNode next){
        this.next = next;
    }

    public void setValue(int value){
        this.value = value;
    }

    public ListNode(ListNode next, int value){
        this.value = value;
        this.next = next;
    }

    public ListNode(int value){
        this(null, value);
    }
}
