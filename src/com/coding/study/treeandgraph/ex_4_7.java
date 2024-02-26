package com.coding.study.treeandgraph;

import java.util.ArrayList;

/**
 * 4.7
 * 순서 정하기 : 프로젝트의 리스트와 프로젝트들 간의 종속 관계
 * (즉， 프로젝트 쌍이 리스트로 주어지면 각 프로젝트 쌍에서 두번째 프로젝트가 첫 번째 프로젝트에 종속되어 있다는 뜻)가 주어졌을 때，
 * 프로젝트를 수행해 나가는 순서를 찾으라. 유효한 순서가 존재하지 않으면 에러를 반환한다.
 */
public class ex_4_7 {

    Project[] findBuildOrder(String[] projects, String[][] dependencies) {
        Graph4_7 graph = buildGraph(projects, dependencies);
        return orderProjects(graph.getNodes());
    }

    Graph4_7 buildGraph(String[] projects, String[][] dependencies) {
        Graph4_7 graph = new Graph4_7();
        for (String project : projects) {
            graph.getOrCreateNode(project);
        }

        for (String[] dependency : dependencies) {
            String first = dependency[0];
            String second = dependency[1];
            graph.addEdge(first, second);
        }
        return graph;
    }

    Project[] orderProjects(ArrayList<Project> projects) {
        Project[] order = new Project[projects.size()];

        int endOfList = addNonDependent(order, projects, 0);

        int toBeProcessed = 0;
        while (toBeProcessed < order.length) {
            Project current = order[toBeProcessed];

            if (current == null) {
                return null;
            }

            ArrayList<Project> children = current.getChildren();
            for (Project child : children) {
                child.decrementDependencies();
            }

            endOfList = addNonDependent(order, children, endOfList);
            toBeProcessed++;
        }
        return order;
    }

    int addNonDependent(Project[] order, ArrayList<Project> projects, int offset) {
        for (Project project : projects) {
            if (project.getNumberDependencies() == 0) {
                order[offset] = project;
                offset++;
            }
        }
        return offset;
    }


}
