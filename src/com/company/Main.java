package com.company;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static Graph friendallgraph = new Graph();
    public static final int maxrec = 10;
    public static void main(String[] args) {
        Graphics g = new Graphics();
        g.setVisible(true);
        Graph friendallgraph = readStartState("Friends.txt");
    }
    private static ArrayList<String> getTopUsers(Graph friendallgraph) {
        Map<String, Integer> lengths = friendallgraph.getLengths();
        List<Integer> sorted_lengths = new ArrayList<Integer>();

        for(Integer length: lengths.values()) {
            if(!sorted_lengths.contains(length)) {
                sorted_lengths.add(length);
            }
        }
        Collections.sort(sorted_lengths, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        ArrayList<String> ret = new ArrayList<String>();
        for (Integer max: sorted_lengths) {
            for (String name: lengths.keySet()) {
                if(lengths.get(name).equals(max)) {
                    ret.add(name);
                }
                if(ret.size() == 5) {
                    break;
                }
            }
            if(ret.size() == 5) {
                break;
            }
        }
        return ret;
    }
    //считывает из файла начальное состояние
    private static Graph readStartState(String fileName) {
        try {
            String s = new String(Files.readAllBytes(Paths.get(fileName)));
            String[] lines = s.split("\n");
            for (String line: lines) {
                if(line.length() > 0) {
                    String[] names = line.trim().split(" ");

                    if(names.length == 1) {
//нет друзей
                        friendallgraph.add(names[0]);
                    } else {
//есть дррузья
                        String user_name = names[0].substring(0, names[0].length() - 1);
                        String[] friendallgraph_names = Arrays.copyOfRange(names, 1, names.length);
                        for (String name: friendallgraph_names) {
                            friendallgraph.addEdge(user_name, name);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return friendallgraph;
    }
    //сохраняет текущее состояние в файл
    private static void save(Graph friendallgraph, String fileName) {
        try(FileWriter w = new FileWriter(fileName, false)) {
            for (String name: friendallgraph.getKeys()) {
                w.append(name);

                if(friendallgraph.getAdjacent(name).size() > 0) {
                    w.append(":");

                    for (String friendName : friendallgraph.getAdjacent(name)) {
                        w.append(" ");
                        w.append(friendName);
                    }
                }

                w.append("\r\n");
            }
            w.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addG (){
            friendallgraph.add(Graphics.arg1);
        save(friendallgraph, "Workfile.txt");
    }
    public static void addEdgeG (){
            friendallgraph.addEdge(Graphics.arg1,Graphics.arg2);
        save(friendallgraph, "Workfile.txt");
    }

    public static void  deletePerson(){
        for (int i = 0; i < friendallgraph.get(Graphics.arg1).size(); i++){
            String name = friendallgraph.get(Graphics.arg1).get(i);
            friendallgraph.deleteEdge(name, Graphics.arg1);
            i--;
            }
        friendallgraph.deleteVertex(Graphics.arg1);
        save(friendallgraph, "Workfile.txt");
    }

    public static void deleteG (){
        friendallgraph.deleteEdge(Graphics.arg1, Graphics.arg2);
        save(friendallgraph, "Workfile.txt");
    }
    public static void friendsUserG () {
        String nameC = "";
        ArrayList<String> list = friendallgraph.get(Graphics.arg1);
        for (int i = 0; i < list.size(); i++)
        {
            nameC = nameC + " " + list.get(i);
        }
        JOptionPane.showMessageDialog(null,"Друзья("+Graphics.arg1+"):" + nameC, "Список друзей", JOptionPane.PLAIN_MESSAGE);

    }
    public static void topG(){
        String nameC = "";
        for (String name: getTopUsers(friendallgraph)) {
            nameC = nameC + " " + name;
        }
        JOptionPane.showMessageDialog(null,"Top: "+nameC, "Топ друзей", JOptionPane.PLAIN_MESSAGE);
    }
    public static void recommendG (){

            ArrayList<String> recommendedfriendallgraph = new ArrayList<String>();
            if(friendallgraph.getAdjacent(Graphics.arg1).size() > 0) {
                for (String friendName : friendallgraph.getAdjacent(Graphics.arg1)) {
                    for (String name : friendallgraph.getAdjacent(friendName)) {
                        if (recommendedfriendallgraph.size() < maxrec) {
                            if (!recommendedfriendallgraph.contains(name) && !name.equals(Graphics.arg1) && !friendallgraph.getAdjacent(Graphics.arg1).contains(name)) {
                                recommendedfriendallgraph.add(name);
                            }
                        } else {
                            break;
                        }
                    }
                }
                //по имени друга по списку друзей создаём список рекомендаций
                //имя друга из списка рекомендованных и смотрим его друзей и записываем сюда же
                if (recommendedfriendallgraph.size() < maxrec) {
                    for (int i = 0; i < recommendedfriendallgraph.size(); i++) {
                        String friendName=recommendedfriendallgraph.get(i);
                        for (String name : friendallgraph.getAdjacent(friendName)) {
                            if (recommendedfriendallgraph.size() < maxrec) {
                                if (!recommendedfriendallgraph.contains(name) && !name.equals(Graphics.arg1) && !friendallgraph.getAdjacent(Graphics.arg1).contains(name)) {
                                    recommendedfriendallgraph.add(name);
                                }
                            }
                        }
                    }
                }
            } else {
                recommendedfriendallgraph = getTopUsers(friendallgraph);
            }
            String nameC = "";
            if (recommendedfriendallgraph.isEmpty())
            {
                for (String name: getTopUsers(friendallgraph)) {
                    nameC = nameC + " " + name;
                }
            }
            else
                for (String name: recommendedfriendallgraph) {
                    nameC = nameC + " " + name;
                }
            JOptionPane.showMessageDialog(null,"Рекомендации: "+nameC, "Рекомендованные друзья", JOptionPane.PLAIN_MESSAGE);
    }
}