package io.gavan.trains;

import io.gavan.trains.factory.IRailroadFactory;
import io.gavan.trains.factory.ITownRegistry;
import io.gavan.trains.factory.impl.RailroadFactory;
import io.gavan.trains.factory.impl.TownRegistry;
import io.gavan.trains.model.Railroad;
import io.gavan.trains.model.Town;
import io.gavan.trains.service.IRailroadService;
import io.gavan.trains.service.impl.RailroadService;
import io.gavan.trains.trip.filter.ITripFilter;
import io.gavan.trains.trip.filter.LimitDistanceTripFilter;
import io.gavan.trains.trip.filter.LimitStopsTripFilter;

public class Main {
    public static void main(String[] args) {
        String data = args.length > 1 ? args[0] : "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        String split = args.length > 1 ? args[1] : ", ";
        IRailroadService railroadService = new RailroadService();
        ITownRegistry townRegistry = new TownRegistry();
        IRailroadFactory railroadFactory = new RailroadFactory();
        Railroad railroad = railroadFactory.create(townRegistry, data, split);

        solveDistanceProblems(railroadService, townRegistry, railroad);
        solveTripCountProblems(railroadService, townRegistry, railroad);
        solveShortestDistanceProblems(railroadService, townRegistry, railroad);
        solveTripCountProblemsLessDistance(railroadService, townRegistry, railroad);
    }

    private static void solveTripCountProblemsLessDistance(IRailroadService railroadService, ITownRegistry townRegistry, Railroad railroad) {
        Town c = townRegistry.get('C');
        outputTripCount(10, railroadService, railroad, c, c, new LimitDistanceTripFilter(30));
    }


    private static void solveShortestDistanceProblems(IRailroadService railroadService, ITownRegistry townRegistry, Railroad railroad) {
        Town a = townRegistry.get('A');
        Town b = townRegistry.get('B');
        Town c = townRegistry.get('C');
        outputShortestDistance(8, railroadService, railroad, a, c);
        outputShortestDistance(9, railroadService, railroad, b, b);
    }

    private static void outputShortestDistance(int no, IRailroadService railroadService, Railroad railroad, Town from, Town to) {
        int distance = railroadService.getShortestDistance(railroad, from, to);
//        System.out.print("distance " + from.getId() + "-" + to.getId() + ":\t\t");//debug
        output(no, distance);
    }

    private static void solveTripCountProblems(IRailroadService railroadService, ITownRegistry townRegistry, Railroad railroad) {
        Town a = townRegistry.get('A');
        Town c = townRegistry.get('C');
        outputTripCount(6, railroadService, railroad, c, c, new LimitStopsTripFilter(3, 1));
        outputTripCount(7, railroadService, railroad, a, c, new LimitStopsTripFilter(4));
    }

    private static void outputTripCount(int no, IRailroadService railroadService, Railroad railroad, Town from, Town to, ITripFilter tripFilter) {
        int tripCount = railroadService.getTripCount(railroad, from, to, tripFilter);
//        System.out.print("trips " + from.getId() + "-" + to.getId() + ":\t\t");//debug
        output(no, tripCount);
    }

    private static void solveDistanceProblems(IRailroadService railroadService, ITownRegistry townRegistry, Railroad railroad) {
        String[] problems = {"A-B-C", "A-D", "A-D-C", "A-E-B-C-D", "A-E-D"};
        for (int i = 0; i < problems.length; i++) {
            String p = problems[i].replace("-", "");
            int len = p.length();
            Town[] route = new Town[len];
            for (int j = 0; j < len; j++) {
                route[j] = townRegistry.get(p.charAt(j));
            }
//            System.out.print(problem + ":\t\t");//debug
            outputRouteDistance(i + 1, railroadService, railroad, route);
        }
    }

    private static void outputRouteDistance(int no, IRailroadService railroadService, Railroad railroad, Town[] route) {
        int distance = getRouteDistance(railroadService, railroad, route);
        if (distance == IRailroadService.INVALID_DISTANCE_VALUE) {
            output(no, IRailroadService.INVALID_DISTANCE_OUTPUT);
        } else {
            output(no, distance);
        }
    }

    private static int getRouteDistance(IRailroadService railroadService, Railroad railroad, Town[] route) {
        return railroadService.getRouteDistance(railroad, route);
    }

    private static void output(int no, Object result) {
        System.out.println("Output #" + no + ": " + result);
    }
}
