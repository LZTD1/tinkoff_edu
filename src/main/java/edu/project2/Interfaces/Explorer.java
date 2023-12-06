package edu.project2.Interfaces;

import java.util.Deque;
import java.util.List;

public interface Explorer {
    Deque<List<Integer>> getRoute(List<List<Integer>> routePoints);

    List<Integer> getCurrentPosition();
}
