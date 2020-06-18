public class MyLinkedList {

    //单链表结点类
    public static class Node {
        public int val;
        public Node next;

        //实例构造器
        public Node(int item) {
            val = item;
        }
    }

    //头结点和数量
    public Node head;
    public int count;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        this.count = 0;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index >= count) return -1;//判断索引是否越界
        Node p = head;
        int num = 0;
        //遍历链表，找到索引指向的结点位置，返回该结点的值
        while (true) {
            if (num == index) return p.val;
            num++;
            p = p.next;
        }
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node p = new Node(val);//更新head
        p.next = head;
        head = p;
        count++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node p = new Node(val);
        //定义一个节点来代替头节点向后遍历，否则头节点会变化
        Node q = head;
        //遍历链表找到尾部，添加节点
        while (q.next != null) q = q.next;
        q.next = p;
        count++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
//        Node p = new Node(val);
//        Node q = head;
//        int num = 0;
//        while(true){
//            if(num == index){
//                p.next = q.next;
//                q.next = p;
//                count++;
//                return;
//            }
//            q = q.next;
//            num++;
//        }

        //索引为0直接执行在头结点前添加结点的方法
        if (index == 0) {
            addAtHead(val);
            return;
        }
        //索引和链表长度相直接执行在尾部添加结点的方法
        if (index == count) {
            addAtTail(val);
            return;
        }
        if (index >= count) return;//索引越界直接返回
        int num = 0;
        Node p = head;
        //循环遍历，在索引指向结点之前添加结点
        while (true) {
            num++;
            if (num == index) {
                Node q = new Node(val);
                q.next = p.next;
                p.next = q;
                count++;
                return;
            }
            p = p.next;
        }

    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        //删除头节点单独考虑
        if (index == 0) {
            Node p = head;
            p = p.next;
            head = p;
            return;
        }
        if (index >= count) return;//索引越界直接返回
        int num = 0;
        Node p = head;
        //遍历链表，删除索引指向的结点
        while (true) {
            num++;
            if (num == index) {
                p.next = p.next.next;
                count--;
                return;
            }
            p = p.next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2);   //链表变为1-> 2-> 3
        linkedList.get(1);            //返回2
        linkedList.deleteAtIndex(2);  //现在链表是1-> 3
        linkedList.deleteAtIndex(0);
        linkedList.get(0);            //返回3

        int num = 123;
        Node result = new Node(0);
        Node p = result;
        while (num > 0) {//记住这个规律     比如：每次用10取模会得到个位数  由于是int类型除以10只会去掉个位上的数
            int m = num % 10;
            while (p.next != null) {
                p = p.next;
            }
            p.next = new Node(m);
            System.out.println(m);
            num /= 10;
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

//
//class MyLinkedList {
//    private class Node{
//        public int val;
//        public Node next;
//
//        public Node(int val){
//            this.val = val;
//        }
//    }
//
//    private Node Head;
//    int size;
//
//    public MyLinkedList() {
//        Head = new Node(-1);
//        size = 0;
//    }
//
//    public int get(int index) {
//        if(index < 0 || index >= size)
//            return -1;
//        Node curr = Head.next;
//        for(int i = 0; i < index; i++)
//            curr = curr.next;
//        return curr.val;
//    }
//
//    public void addAtHead(int val) {
//        addAtIndex(0, val);
//    }
//
//    public void addAtTail(int val) {
//        addAtIndex(size, val);
//    }
//
//    public void addAtIndex(int index, int val) {
//        if(index > size)
//            return;
//        if(index < 0)
//            addAtHead(val);
//        Node prev = Head;
//        for(int i = 0; i < index; i++){
//            prev = prev.next;
//        }
//        Node node = new Node(val);
//        node.next = prev.next;
//        prev.next = node;
//        size++;
//    }
//
//    public void deleteAtIndex(int index) {
//        if(index < 0 || index >= size)
//            return;
//        Node prev = Head;
//        for(int i = 0; i < index; i++)
//            prev = prev.next;
//        Node reNode = prev.next;
//        prev.next = reNode.next;
//        reNode.next = null;
//        size--;
//    }
//}
//
///**
// * Your MyLinkedList object will be instantiated and called as such:
// * MyLinkedList obj = new MyLinkedList();
// * int param_1 = obj.get(index);
// * obj.addAtHead(val);
// * obj.addAtTail(val);
// * obj.addAtIndex(index,val);
// * obj.deleteAtIndex(index);
// */