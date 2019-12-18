package com.mp5a5.node;

import org.junit.Test;

public class NodeTest {


    @Test
    public void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(4);
        Node node3 = new Node(2);
        Node node4 = new Node(5);
        Node node5 = new Node(1);
        Node node6 = new Node(6);
        Node node7 = new Node(10);
        Node node8 = new Node(11);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        printNode(mergeSort(node1));
    }

    public void printNode(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

    public boolean isCycle(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }

    public Node getMidNode(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    public Node reverseByRecursion(Node head) {
        if (head == null || head.next == null) return head;
        Node newHead = reverseByRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public Node reverseByLoop(Node head) {
        if (head == null || head.next == null) return head;
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
        if (head == null || head.next == null) return head;
        Node mid = getMidNode(head);
        Node next = mid.next;
        mid.next = null;
        return sort(mergeSort(head), mergeSort(next));
    }

    private Node sort(Node front, Node after) {
        if (front == null) return after;
        if (after == null) return front;
        Node head = null;
        if (front.value <= after.value) {
            head = front;
            head.next = sort(front.next, after);
        } else {
            head = after;
            head.next = sort(front, after.next);
        }
        return head;
    }
}
