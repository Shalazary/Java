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

    public List(int array[]) {
        size = array.length;
        for (int i = 0; i < size; i++) {
            insertBack(array[i]);
        }
    }

    public int lenght() {
        return size;
    }

    private ListNode getNodeAt(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");

        ListNode current = head;
        for (int i = 0; i < index; i++)
            current = current.getNext();

        return current;
    }

    public int getValueAt(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");

        return getNodeAt(index).getValue();
    }

    public void insertAt(int index, int value) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");

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

    public void removeAt(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");

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
            s += getValueAt(0);
            for (int i = 1; i < size; i++)
                s += " -> " + getValueAt(i);
        }
        return s;
    }

    public int[] toArray() {
        int array[] = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = getValueAt(i);
        }

        return array;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        List that = (List) obj;

        if (lenght() != that.lenght())
            return false;

        for (int i = 0; i < lenght(); i++)
            if (getValueAt(i) != that.getValueAt(i))
                return false;

        return true;
    }
}