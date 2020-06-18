class MyLinkedList1 {

    private int size;
    private MyLinkedList1 next;
    private int val;

    /** Initialize your data structure here. */
    public MyLinkedList1() {
        this.size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= this.size) {
            return -1;
        }
        MyLinkedList1 temp = this;
        for (int i=0; i<index; i++) {
            temp = temp.next;
        }
        return temp.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        if (this.size == 0) {
            this.val = val;
            this.size++;
        } else {
            MyLinkedList1 current = new MyLinkedList1();
            current.next = this.next;
            current.val = this.val;
            this.size++;
            this.next = current;
            this.val = val;
        }
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        MyLinkedList1 first = new MyLinkedList1();
        first.val = this.val;
        first.next = this.next;
        for (int i=0; i<this.size; i++) {
            if (this.size == 1) {
                MyLinkedList1 last = new MyLinkedList1();
                last.val = val;
                this.size++;
                this.next = last;
                break;
            } else if (i == this.size - 1) {
                MyLinkedList1 last = new MyLinkedList1();
                last.val = val;
                this.size++;
                first.next = last;
                break;
            }
            first = first.next;
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
        } else if (index == this.size) {
            addAtTail(val);
        } else if (index < this.size) {
            MyLinkedList1 temp = this;
            MyLinkedList1 prev = null;
            for (int i=0; i<index; i++) {
                if (i == index - 1) {
                    prev = temp;
                }
                temp = temp.next;
            }
            MyLinkedList1 current = new MyLinkedList1();
            current.val = val;
            this.size++;
            current.next = temp;
            prev.next = current;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < this.size) {
            MyLinkedList1 temp = this;
            MyLinkedList1 prev = null;
            for (int i=0; i<=index; i++) {
                if (i == index - 1) {
                    prev = temp;
                }
                temp = temp.next;
            }
            if (prev == null) {
                if (temp != null) {
                    this.val = temp.val;
                    this.next = temp.next;
                } else {
                    this.val = 0;
                    this.next = null;
                }
            } else {
                prev.next = temp;
            }
            this.size--;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */