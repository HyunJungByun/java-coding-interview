package com.coding.study.linkedlist;

import java.util.HashSet;
import java.util.Stack;

public class LinkedListExam {

    // 문제1
    // 임시 버퍼 사용
    // O(N) 수행시간이 걸리는 알고리즘 - N 은 연결리스트 길이
    public void deleteDups1(LinkedListNode n) {
        HashSet set = new HashSet();
        LinkedListNode previous = null;
        while (n != null) {
            if (set.contains(n.data)) {
                previous.next = n.next;
            } else {
                set.add(n.data);
                previous = n;
            }
            n = n.next;
        }
    }

    // 문제1
    // 임시 버퍼를 사용할 수 없을 때
    // O(1) 공간을 사용하지만, 수행 시간은 O(N^2)
    public void deleteDups2(LinkedListNode head) {
        LinkedListNode current = head;
        while (current != null) {
            /* 값이 같은 다음 노드들을 모두 제거한다 */
            LinkedListNode runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    // 문제2
    // 맨 뒤에서 K번째 원소의 값을 '출력'
    // 재귀 호출 방법 1
    // O(n) 의 공간 사용
    public int printKthToLast(LinkedListNode head, int k) {
        if (head == null) {
            return 0;
        }
        int index = printKthToLast(head.next, k) + 1;
        if (index == k) {
            System.out.println(k + "th to last node is " + head.data);
        }
        return index;
    }

    // 문제2
    // 재귀 호출 방법 2
    // O(n) 의 공간 사용
    LinkedListNode kthToLast(LinkedListNode head, int k) {
        Index idx = new Index();
        return kthToLast(head, k, idx);
    }

    LinkedListNode kthToLast(LinkedListNode head, int k, Index idx) {
        if (head == null) {
            return null;
        }
        LinkedListNode node = kthToLast(head.next, k, idx);
        idx.value = idx.value + 1;
        if (idx.value == k) {
            return head;
        }
        return node;
    }

    // 문제2
    // iterative 방법
    // O(n) 수행 시간 소요, O(1) 공간 사용
    // 두 개의 포인터 p1, p2 사용
    // p2는 연결리스트의 시작 노드를 가리키고, p1은 k노드만큼 움직여서 p1과 p2가 k 노드만큼 떨어져 있도록 만듬
    // p1과 p2를 함께 이동시키면 p1 은 LENGTH-k번 후에 연결리스트 맨 마지막 노드에 도달
    // p2는 LENGTH-k 번 노드, 뒤에서부터 k번째 노드를 가리키게 됨.
    LinkedListNode nthToLast(LinkedListNode head, int k) {
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;

        /* p1을 k노드만큼 이동시킨다. */
        for (int i = 0; i < k; i++) {
            if (p1 == null) return null;    // Out of bounds
            p1 = p1.next;
        }

        /* p1과 p2를 함께 움직인다. p1이 끝에 도달하면, p2는 LENGTH-k 번째 원소를 가리키게 된다. */
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    // 문제3
    // 연결리스트의 헤드에 접근할 수 없음.
    // 삭제할 노드만 접근 할 수 있음.
    // 다음 노드의 데이터를 현재 노드에 복사한 다음, 다음 노드를 지우면 해결
    // 삭제할 노드가 리스트의 마지막 노드인 경우에는 풀 수 없음
    boolean deleteNode(LinkedListNode n) {
        if (n == null || n.next == null) {
            return false;   // 실패
        }
        LinkedListNode next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }

    // 문제4
    // 리스트의 원소 순서를 안정적으로 유지
    /* 연결리스트의 헤드와 분할할 값을 인자로 넘겨준다. */
    LinkedListNode partition1(LinkedListNode node, int x) {
        LinkedListNode beforeStart = null;
        LinkedListNode beforeEnd = null;
        LinkedListNode afterStart = null;
        LinkedListNode afterEnd = null;

        /* 리스트를 분할한다 */
        while (node != null) {
            LinkedListNode next = node.next;
            node.next = null;
            if (node.data < x) {
                /* before 리스트의 끝에 원소를 삽입한다. */
                if (beforeStart == null) {
                    beforeStart = node;
                    beforeEnd = beforeStart;
                } else {
                    beforeEnd.next = node;
                    beforeEnd = node;
                }
            } else {
                /* after 리스트의 끝에 원소를 삽입니다. */
                if (afterStart == null) {
                    afterStart = node;
                    afterEnd = afterStart;
                } else {
                    afterEnd.next = node;
                    afterEnd = node;
                }
            }
            node = next;
        }

        if (beforeStart == null) {
            return afterStart;
        }
        /* before와 after를 병합한다. */
        beforeEnd.next = afterStart;
        return beforeStart;
    }

    // 문제4
    // 리스트의 원소 순서를 안정적으로 유지할 필요 없는 방식
    // 두번째 인자로 받는 값보다 작은 값을 가진 연결리스트만 return
    LinkedListNode partition2(LinkedListNode node, int x) {
        LinkedListNode head = null;
        LinkedListNode tail = null;

        while (node != null) {
            LinkedListNode next = node.next;
            if (node.data < x) {
                /* head 에 노드를 삽입한다. */
                node.next = head;
                head = node;
            } else {
                /* tail 에 노드를 삽입한다 */
                node.next = tail;
                tail = node;
            }
            node = next;
        }
        tail.next = null;

        // head 가 바뀌었기 때문에 새로운 head를 반환해야 한다.
        return head;
    }

    // 문제5
    LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        LinkedListNode result = new LinkedListNode();
        int value = carry;
        if (l1 != null) {
            value += l1.data;
        }
        if (l2 != null) {
            value += l2.data;
        }

        result.data = value % 10;

        if (l1 != null || l2 != null) {
            LinkedListNode more = addLists(l1 == null ? null : l1.next, l2 == null ? null : l2.next, value >= 10 ? 1 : 0);
            result.setNext(more);
        }
        return result;
    }

    // 문제5
    LinkedListNode addLists2(LinkedListNode l1, LinkedListNode l2) {
        int len1 = length(l1);
        int len2 = length(l2);

        if (len1 < len2) {
            l1 = padList(l1, len2 - len1);
        } else {
            l2 = padList(l2, len1 - len2);
        }

        PartialSum sum = addListsHelper(l1, l2);

        if (sum.carry == 0) {
            return sum.sum;
        } else {
            LinkedListNode result = insertBefore(sum.sum, sum.carry);
            return result;
        }
    }

    PartialSum addListsHelper(LinkedListNode l1, LinkedListNode l2) {
        if (l1 == null && l2 == null) {
            PartialSum sum = new PartialSum();
            return sum;
        }

        PartialSum sum = addListsHelper(l1.next, l2.next);

        int val = sum.carry + l1.data + l2.data;

        LinkedListNode full_result = insertBefore(sum.sum, val % 10);

        sum.sum = full_result;
        sum.carry = val / 10;
        return sum;
    }

    LinkedListNode insertBefore(LinkedListNode list, int data) {
        LinkedListNode node = new LinkedListNode(data);
        if (list != null) {
            node.next = list;
        }
        return node;
    }

    int length(LinkedListNode n) {
        int length = 0;
        while (n != null) {
            length++;
            n = n.next;
        }
        return length;
    }

    /* 리스트 앞에 0을 추가 */
    LinkedListNode padList(LinkedListNode l, int padding) {
        LinkedListNode head = l;
        for (int i = 0; i < padding; i++) {
            head = insertBefore(head, 0);
        }
        return head;
    }

    //문제6
    boolean isPalindrome(LinkedListNode head) {
        LinkedListNode reversed = reverseAndClone(head);
        return isEqual(head, reversed);
    }

    LinkedListNode reverseAndClone(LinkedListNode node) {
        LinkedListNode head = null;
        while (node != null) {
            LinkedListNode n = new LinkedListNode(node.data);   // 복사
            n.next = head;
            head = n;
            node = node.next;
        }
        return head;
    }

    boolean isEqual(LinkedListNode one, LinkedListNode two) {
        while (one != null && two != null) {
            if (one.data != two.data) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return one == null && two == null;
    }

    // 문제6
    boolean isPalindrome2(LinkedListNode head) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;

        Stack<Integer> stack = new Stack();
        /* 연결리스트의 앞 절반을 스택에 쌓는다. fast runner가(2배 빠른)
         *  연결리스트의 끝에 도달하면, slow runner 가 중간에 도달했다는 사실을 알 수 있다. */
        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }
        /* 원소의 개수가 홀수 개라면 가운데 원소는 넘긴다. */
        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            int top = stack.pop().intValue();
            /* 값이 다르면 회문이 될 수 없다. */
            if (top != slow.data) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    // 문제6
    boolean isPalindrome3(LinkedListNode head) {
        int length = lengthOfList(head);
        PalindromeResult result = isPalindromeRecurse(head, length);
        return result.isPalindrome;
    }

    PalindromeResult isPalindromeRecurse(LinkedListNode head, int length) {
        if (head == null || length <= 0) {  // 노드의 개수가 짝수일 때
            return new PalindromeResult(head, true);
        } else if (length == 1) {   // 노드의 개수가 홀수일 때
            return new PalindromeResult(head.next, true);
        }
        /* 부분 리스트를 재귀적으로 호출 */
        PalindromeResult res = isPalindromeRecurse(head.next, length - 2);
        /* 아래 호출 결과 회문이 아니라는 사실이 밝혀지면, 그 결과값을 반환 */
        if (!res.isPalindrome || res.node == null) {
            return res;
        }
        /* 두 노드의 값이 같은지 확인 */
        res.isPalindrome = (head.data == res.node.data);
        /* 그 다음에 비교할 노드를 반환 */
        res.node = res.node.next;

        return res;
    }

    int lengthOfList(LinkedListNode n) {
        int size = 0;
        while (n != null) {
            size++;
            n = n.next;
        }
        return size;
    }

    // 문제7
    LinkedListNode findIntersection(LinkedListNode list1, LinkedListNode list2) {
        if (list1 == null || list2 == null) return null;

        /* 마지막 노드와 길이를 구한다. */
        FindIntersectionResult result1 = getTailAndSize(list1);
        FindIntersectionResult result2 = getTailAndSize(list2);

        /* 마지막 노드가 다르면 교집합이 없다는 뜻 */
        if (result1.tail != result2.tail) {
            return null;
        }

        /* 각 연결리스트의 시작점에 포인터를 세팅 */
        LinkedListNode shorter = result1.size < result2.size ? list1 : list2;
        LinkedListNode longer = result1.size < result2.size ? list2 : list1;
        /* 길이가 긴 연결리스트의 포인터를 길이의 차이만큼 앞으로 움직인다. */
        longer = getKthNode(longer, Math.abs(result1.size - result2.size));

        /* 두 포인터가 만날 때까지 함께 앞으로 움직인다. */
        while (shorter != longer) {
            shorter = shorter.next;
            longer = longer.next;
        }

        /* 둘 중 하나를 반환 */
        return longer;
    }

    FindIntersectionResult getTailAndSize(LinkedListNode list) {
        if (list == null) return null;

        int size = 1;
        LinkedListNode current = list;
        while (current.next != null) {
            size++;
            current = current.next;
        }
        return new FindIntersectionResult(current, size);
    }

    LinkedListNode getKthNode(LinkedListNode head, int k) {
        LinkedListNode current = head;
        while (k > 0 && current != null) {
            current = current.next;
            k--;
        }
        return current;
    }


}

class FindIntersectionResult {
    public LinkedListNode tail;
    public int size;

    public FindIntersectionResult(LinkedListNode tail, int size) {
        this.tail = tail;
        this.size = size;
    }
}

class PalindromeResult {
    public LinkedListNode node;
    public boolean isPalindrome;

    PalindromeResult(LinkedListNode n, boolean b) {
        this.node = n;
        this.isPalindrome = b;
    }
}

class PartialSum {
    public LinkedListNode sum = null;
    public int carry = 0;
}

class Index {
    public int value = 0;
}