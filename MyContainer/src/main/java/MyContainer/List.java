package MyContainer;

public class List {

    class ListNode {
        private int value;
        private ListNode next;

        public ListNode getNext() {
            return next;
        }

        public int getValue() {
            return value;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public ListNode(ListNode next, int value) {
            this.value = value;
            this.next = next;
        }

        public ListNode(int value) {
            this(null, value);
        }
    }

    private int size;
    private ListNode head;
    private ListNode tail;

    public List() {
        size = 0;
        head = null;
        tail = null;
    }

    public int lenght() {
        return size;
    }

    private ListNode getNodeAt(int index) {
        // if(index < 0 || index >= size)
        ListNode current = head;
        for (int i = 0; i < index; i++)
            current = current.getNext();
        return current;
    }

    public int getValueAt(int index) {
        // if(index < 0 || index >= size)
        return getNodeAt(index).getValue();
    }

    public void insertAt(int index, int value) {
        // if(index < 0 || index >= size)
        if (size == 0 || index == size - 1) {
            insertBack(value);
        } else if (index == 0) {
            head = new ListNode(head, value);
        } else {
            ListNode current = getNodeAt(index - 1);
            current.setNext(new ListNode(current.getNext(), value));
            size++;
        }
    }

    public void insertBack(int value) {
        ListNode node = new ListNode(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    public void insertFront(int value) {
        ListNode node = new ListNode(head, value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head = node;
        }
        size++;
    }

    public void removeAt(int index) {
        // if(index < 0 || index >= size)
        if (index == 0) {
            head = head.getNext();
        } else {
            ListNode prev = getNodeAt(index - 1);
            prev.setNext(prev.getNext().getNext());
            if (index == size - 1)
                tail = prev;
        }
        size--;
    }

    @Override
    public String toString() {
        String s = new String();
        if (size > 0) {
            for (int i = 0; i < size - 1; i++) {
                s += getValueAt(i) + " -> ";
            }
            s += tail.getValue();
        }
        return s;
    }
}