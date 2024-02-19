package com.coding.study.treeandgraph;

import java.util.LinkedList;

/* 4.1
   노드 사이의 경로: 방향 그래프가 주어졌을 때 두 노드 사이에 경로가 존재하는지 확인하는 알고리즘을 작성하라.
 */
public class ex_4_1 {
    /*
    - 그래프 탐색 : 너비 우선 탐색(breadth-first search), 깊이 우선 탐색(depth-first search)
    - 두 노드 가운데 하나를 고른 뒤 탐색 도중 다른 노드가 발견되는지 검사
    - 중복되는 일을 피하기 위해 방문한 노드 '이미 방문했음' 으로 반드시 표시 => 무한루프 빠질 위험 방지
     */

    /*
    - 깊이 우선 탐색(DFS)는 그래프에서 모든 노드를 방문하고자 할 때 더 선호
    - 너비 우선 탐색(BFS)는 두 노드 사이의 최단 경로 혹은 임의의 경로를 찾고 싶을 때 더 낫다
     */

    //     너비 우선 탐색 기법을 순환적 방법으로 구현
    boolean search(Graph graph, Node start, Node end) {
        if (start == end) return true;

        // BFS는 재귀적으로 동작하지 않는다. Queue 를 사용한다.
        // Queue처럼 동작한다.
        LinkedList likeQueue = new LinkedList();

        for (Node u : graph.getNodes()) {
            u.state = State.Unvisited;
        }
        // 두 노드 사이의 '시작' 지점 노드의 state를 visiting 으로 set
        start.state = State.Visiting;
        likeQueue.add(start);
        Node u;
        while (!likeQueue.isEmpty()) {
            u = (Node) likeQueue.removeFirst();    //  dequeue()와 같다
            if (u != null) {
                for (Node v : u.getAdjacent()) {    // 탐색하려는 노드의 인접한 노드들 가져오기
                    if (v.state == State.Unvisited) {
                        if (v == end) { // 두 노드 중 '종료' 지점 노드와 같은 노드
                            return true;    // 경로 존재. true
                        } else {
                            v.state = State.Visiting;
                            likeQueue.add(v);
                        }
                    }
                }
                u.state = State.Visited;    // 무한루프 방지를 위해 '이미 방문했음' 표시
            }
        }
        return false;   // 경로 미존재. false
    }

}
