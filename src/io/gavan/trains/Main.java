package io.gavan.trains;

import io.gavan.trains.factory.ITownFactory;
import io.gavan.trains.factory.impl.TownFactory;
import io.gavan.trains.service.IRailroadService;
import io.gavan.trains.service.impl.RailroadService;

public class Main {

    public static void main(String[] args) {
        IRailroadService railroadService = new RailroadService();
        ITownFactory townFactory = new TownFactory();

        solveDistanceProblems(railroadService, townFactory);
    }

    private static void solveDistanceProblems(IRailroadService railroadService, ITownFactory townFactory) {
        char[] problems = {'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'};
        for (int i = 0; i < problems.length; i += 2) {
            outputRouteDistance(railroadService, townFactory, problems[i], problems[i + 1]);
        }
    }

    private static void outputRouteDistance(IRailroadService railroadService, ITownFactory townFactory, char from, char to) {
        int distance = getRouteDistance(railroadService, townFactory, from, to);
        if (distance == IRailroadService.INVALID_DISTANCE_VALUE) {
            System.out.println(IRailroadService.INVALID_DISTANCE_OUTPUT);
        } else {
            System.out.println(distance);
        }
    }

    private static int getRouteDistance(IRailroadService railroadService, ITownFactory townFactory, char from, char to) {
        return railroadService.getRouteDistance(townFactory.get(from), townFactory.get(to));
    }
}
