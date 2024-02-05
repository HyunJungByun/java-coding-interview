package com.coding.study.linkedlist;

public class LinkedListExamTest {

    void examTest() {
        //        Node test1 = new Node(10);
////        Node test2 = new Node(10);
//        test1.appendToTail(9);
//        test1.appendToTail(8);
//        test1.appendToTail(7);
////        test2.appendToTail(6);
//
//        test1.deleteNode(test1, 7);

        LinkedListNode node3 = new LinkedListNode(3);
        LinkedListNode node5 = new LinkedListNode(5);
        LinkedListNode node8 = new LinkedListNode(8);
        LinkedListNode node5_ = new LinkedListNode(5);
        LinkedListNode node10 = new LinkedListNode(10);
        LinkedListNode node2 = new LinkedListNode(2);
        LinkedListNode node1 = new LinkedListNode(1);
        node3.setNext(node5);
        node5.setNext(node8);
        node8.setNext(node5_);
        node5_.setNext(node10);
        node10.setNext(node2);
        node2.setNext(node1);

        LinkedListNode testNode = node3;

        while (testNode != null) {
            System.out.print(testNode.data);
            testNode = testNode.next;
            if (testNode != null) {
                System.out.print("->");
            } else {
                System.out.println();
            }
        }
        LinkedListNode testNode1 = node3;
        LinkedListNode testNode2 = node3;
        LinkedListExam test = new LinkedListExam();


        /* 문제 4-1*/
        LinkedListNode test4_1 = test.partition1(testNode1, 5);

        while (test4_1 != null) {
            System.out.print(test4_1.data);
            test4_1 = test4_1.next;
            if (test4_1 != null) {
                System.out.print("->");
            } else {
                System.out.println();
            }
        }

        /* 문제 4-2*/
        LinkedListNode test4_2 = test.partition2(testNode2, 3);

        while (test4_2 != null) {
            System.out.print(test4_2.data);
            test4_2 = test4_2.next;
            if (test4_2 != null) {
                System.out.print("->");
            } else {
                System.out.println();
            }
        }

        /* */
        LinkedListNode l1 = new LinkedListNode(7);
        LinkedListNode l1_2 = new LinkedListNode(1);
        LinkedListNode l1_3 = new LinkedListNode(6);
        l1.setNext(l1_2);
        l1_2.setNext(l1_3);

        LinkedListNode l2 = new LinkedListNode(5);
        LinkedListNode l2_2 = new LinkedListNode(9);
        LinkedListNode l2_3 = new LinkedListNode(2);
        l2.setNext(l2_2);
        l2_2.setNext(l2_3);

        /* 문제5-1 */
        LinkedListNode result = test.addLists(l1, l2, 0);
        while (result != null) {
            System.out.print(result.data);
            if (result.next != null) {
                System.out.print("->");
            } else {
                System.out.println();
            }
            result = result.next;
        }

        LinkedListNode l3 = new LinkedListNode(6);
        LinkedListNode l3_2 = new LinkedListNode(1);
        LinkedListNode l3_3 = new LinkedListNode(7);
        l3.setNext(l3_2);
        l3_2.setNext(l3_3);

        LinkedListNode l4 = new LinkedListNode(2);
        LinkedListNode l4_2 = new LinkedListNode(9);
        LinkedListNode l4_3 = new LinkedListNode(5);
        l4.setNext(l4_2);
        l4_2.setNext(l4_3);

        /* 문제5-2 */
        LinkedListNode result2 = test.addLists2(l3, l4);
        while (result2 != null) {
            System.out.print(result2.data);
            if (result2.next != null) {
                System.out.print("->");
            } else {
                System.out.println();
            }
            result2 = result2.next;
        }

        LinkedListNode l5 = new LinkedListNode(0);
        LinkedListNode l5_2 = new LinkedListNode(1);
        LinkedListNode l5_3 = new LinkedListNode(2);
        LinkedListNode l5_4 = new LinkedListNode(1);
        LinkedListNode l5_5 = new LinkedListNode(0);
        l5.setNext(l5_2);
        l5_2.setNext(l5_3);
        l5_3.setNext(l5_4);
        l5_4.setNext(l5_5);

        /* 문제6-1 */
        boolean result3 = test.isPalindrome(l5);
        System.out.println(result3);

        /* 문제6-2 */
        boolean result4 = test.isPalindrome2(l5);
        System.out.println(result4);

        /* 문제6-3 */
        boolean result5 = test.isPalindrome3(l5);
        System.out.println(result5);

        LinkedListNode l6_1 = new LinkedListNode(3);
        LinkedListNode l6_2 = new LinkedListNode(1);
        LinkedListNode l6_3 = new LinkedListNode(5);
        LinkedListNode l6_4 = new LinkedListNode(9);
        LinkedListNode l6_5 = new LinkedListNode(7);
        LinkedListNode l6_6 = new LinkedListNode(2);
        LinkedListNode l6_7 = new LinkedListNode(1);
        l6_1.setNext(l6_2);
        l6_2.setNext(l6_3);
        l6_3.setNext(l6_4);
        l6_4.setNext(l6_5);
        l6_5.setNext(l6_6);
        l6_6.setNext(l6_7);

        LinkedListNode l7_1 = new LinkedListNode(4);
        LinkedListNode l7_2 = new LinkedListNode(6);
        l7_1.setNext(l7_2);
        l7_2.setNext(l6_5);

        LinkedListNode l8_1 = new LinkedListNode(4);
        LinkedListNode l8_2 = new LinkedListNode(6);
        LinkedListNode l8_3 = new LinkedListNode(7);
        LinkedListNode l8_4 = new LinkedListNode(2);
        LinkedListNode l8_5 = new LinkedListNode(1);

        /*문제7 교집합 있음*/
        LinkedListNode intersection = test.findIntersection(l6_1, l7_1);
        while (intersection != null) {
            System.out.print(intersection.data);
            if (intersection.next != null) {
                System.out.print("->");
            } else {
                System.out.println();
            }
            intersection = intersection.next;
        }
        /*문제7 교집합 없음*/
        LinkedListNode notIntersection = test.findIntersection(l6_1, l8_1);
        System.out.println("notIntersection:::" + notIntersection);

    }

}


class Node {
    //단방향 연결리스트 구현
    Node next = null;
    int data;

    public Node(int d) {
        data = d;
    }

    void appendToTail(int d) {
        Node end = new Node(d);
        Node n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    Node deleteNode(Node head, int d) {
        Node n = head;
        if (n.data == d) {
            return head.next;   /* head를 움직인다 */
        }

        while (n.next != null) {
            if (n.next.data == d) {
                n.next = n.next.next;
                return head;    /* head는 변하지 않는다. */
            }
            n = n.next;
        }
        return head;
    }
}
