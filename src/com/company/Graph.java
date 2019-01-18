package com.company;
import java.util.*;

public class Graph {
// список смежности
    private Map<String, ArrayList<String>> details;
    //конструктор
    public Graph() {
        this.details = new HashMap<String, ArrayList<String>>();
    }

    // добавляет вержину в список смежности
    public Graph add(String vertex) {
        if(!this.details.containsKey(vertex)) {
            this.details.put(vertex, new ArrayList<String>());
        }
        return this;
    }

    // список смежных вершин для указанной вершины
    public ArrayList<String> get(String vertex) {
        return this.details.get(vertex);
    }

    public Set<String> getKeys() {
        return this.details.keySet();
    }

    //добавляем ребро
    public Graph addEdge(String u, String v) {
        if(!this.details.containsKey(u)) {
            this.add(u);
        }
        if(!this.details.get(u).contains(v)) {
            this.details
                    .get(u)
                    .add(v)
            ;
        }

        if(!this.details.containsKey(v)) {
            this.add(v);
        }
        if(!this.details.get(v).contains(u)) {
            this.details
                    .get(v)
                    .add(u)
            ;
        }
        return this;
    }

    public Graph deleteVertex(String u){
        this.details.remove(u);
        return this;
    }
    // удаляем ребро
    public Graph deleteEdge(String u, String v) {
        this.details
                .get(u)
                .remove(v)
        ;

        this.details
                .get(v)
                .remove(u)
        ;

        return this;
    }

    //проверка на путоту
    public boolean isEmpty() {
        return this.details.isEmpty();
    }

    // список смежных вершин для указанной вершины
    public ArrayList<String> getAdjacent(String v) {
        return this.details.get(v);
    }

    // список с кол-вом смежных вершин для каждой вершины
    public Map<String, Integer> getLengths() {
        Map<String, Integer> lengthes = new HashMap<String, Integer>();
        Set<String> keys = this.details.keySet();
        for(String key : keys) {
            lengthes.put(key, this.details.get(key).size());
        }

        return lengthes;
    }
}