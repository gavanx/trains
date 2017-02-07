package io.gavan.trains;

import io.gavan.trains.factory.ITownFactory;
import io.gavan.trains.factory.impl.TownFactory;
import io.gavan.trains.model.Town;
import io.gavan.trains.service.IRailroadService;
import io.gavan.trains.service.impl.RailroadService;

public class Main {

    public static void main(String[] args) {
        IRailroadService railroadService = new RailroadService();
        ITownFactory townFactory = new TownFactory();

        solveDistanceProblems(railroadService, townFactory);
    }

    private static void solveDistanceProblems(IRailroadService railroadService, ITownFactory townFactory) {
        String[] problems = {"A-B-C", "A-D", "A-D-C", "A-E-B-C-D", "A-E-D"};
        for (String problem : problems) {
            String p = problem.replace("-", "");
            int len = p.length();
            Town[] route = new Town[len];
            for (int i = 0; i < len; i++) {
                route[i] = townFactory.get(p.charAt(i));
            }
            outputRouteDistance(railroadService, route);
        }
    }

    private static void outputRouteDistance(IRailroadService railroadService, Town[] route) {
        int distance = getRouteDistance(railroadService, route);
        if (distance == IRailroadService.INVALID_DISTANCE_VALUE) {
            System.out.println(IRailroadService.INVALID_DISTANCE_OUTPUT);
        } else {
            System.out.println(distance);
        }
    }

    private static int getRouteDistance(IRailroadService railroadService, Town[] route) {
        return railroadService.getRouteDistance(route);
    }
}
