package com.mp5a5.node;

public class NodeTest {

    public boolean isCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (slow.next != null && slow.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public void printNode(Node head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    public Node getMid(Node head) {
        Node slow = head;
        Node fast = head;
        while (slow.next != null && slow.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node reverseByRecursion(Node head) {
        if (head == null) return head;
        Node newHead = reverseByRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public Node reverseByLoop(Node head) {
        if (head == null) return head;
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public Node mergeSort(Node head) {
        if (head == null) return head;
        Node mid = getMid(head);
        mid.next = null;
        return merge(mergeSort(head), mergeSort(mid));
    }

    private Node merge(Node front, Node after) {
        if (front == null) return after;
        if (after == null) return front;
        Node head = null;
        if (front.value <= after.value) {
            head = front;
            head.next = merge(front.next, after);
        } else {
            head = after;
            head.next = merge(front, after.next);
        }
        return head;
    }

    public Node removeMidNode(Node head) {
        if (head == null || head.next == null) return head;
        if (head.next.next == null) return head.next;
        Node fast = head.next.next;
        Node slow = head.next;//这个很关键
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public Node removeLastKthNode(Node head, int k) {
        if (head == null || k < 1) return head;
        Node current = head;
        int num = 0;
        while (current != null) {
            num++;
            current = current.next;
        }

        if (num == k) {
            return head.next;
        }

        if (num > k) {
            current = head;
            while (num - k != 0) {
                current = current.next;
                num--;
            }
            current.next = current.next.next;
        }
        return head;
    }
}
