package edu.project2.Interfaces;

import java.util.Deque;
import java.util.List;

public interface Explorer {
    public Deque<List<Integer>> getRoute(List<List<Integer>> routePoints);
    public List<Integer> getCurrentPosition();
}
