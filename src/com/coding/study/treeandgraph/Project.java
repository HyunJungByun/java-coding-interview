package com.coding.study.treeandgraph;

import java.util.ArrayList;
import java.util.HashMap;

public class Project {
    private final ArrayList<Project> children = new ArrayList<>();
    private final HashMap<String, Project> map = new HashMap<>();
    private final String name;
    private int dependencies = 0;

    public Project(String name) {
        this.name = name;
    }

    public void addNeighbor(Project node) {
        if (!map.containsKey(node.getName())) {
            children.add(node);
            map.put(node.getName(), node);
            node.incrementDependencies();
        }
    }

    public void incrementDependencies() {
        this.dependencies++;
    }

    public void decrementDependencies() {
        this.dependencies--;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Project> getChildren() {
        return this.children;
    }

    public int getNumberDependencies() {
        return this.dependencies;
    }
}
