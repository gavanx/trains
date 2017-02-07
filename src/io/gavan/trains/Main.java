package io.gavan.trains;

import io.gavan.trains.factory.IRailroadFactory;
import io.gavan.trains.factory.ITownRegistry;
import io.gavan.trains.factory.impl.RailroadFactory;
import io.gavan.trains.factory.impl.TownRegistry;
import io.gavan.trains.model.Railroad;
import io.gavan.trains.model.Town;
import io.gavan.trains.service.IRailroadService;
import io.gavan.trains.trip.filter.ITripFilter;
import io.gavan.trains.trip.filter.LimitStopsTripFilter;
import io.gavan.trains.service.impl.RailroadService;

public class Main {

    public static void main(String[] args) {
        IRailroadService railroadService = new RailroadService();
        ITownRegistry townRegistry = new TownRegistry();
        IRailroadFactory railroadFactory = new RailroadFactory();
        Railroad railroad = railroadFactory.create();

        //solveDistanceProblems(railroadService, townRegistry, railroad);
        //solveTripCountProblems(railroadService, townRegistry, railroad);
        solveShortestDistanceProblems(railroadService, townRegistry, railroad);
    }

    private static void solveShortestDistanceProblems(IRailroadService railroadService, ITownRegistry townRegistry, Railroad railroad) {
        Town a = townRegistry.get('A');
        Town b = townRegistry.get('B');
        Town c = townRegistry.get('C');
        outputShortestDistance(railroadService, railroad, a, c);
        outputShortestDistance(railroadService, railroad, b, b);
    }

    private static void outputShortestDistance(IRailroadService railroadService, Railroad railroad, Town from, Town to) {
        int distance = railroadService.getShortestDistance(railroad, from, to);
        System.out.print("distance " + from.getId() + "-" + to.getId() + ":\t\t");//debug
        System.out.println(distance);
    }

    private static void solveTripCountProblems(IRailroadService railroadService, ITownRegistry townRegistry, Railroad railroad) {
        Town a = townRegistry.get('A');
        Town c = townRegistry.get('C');
        outputTripCount(railroadService, railroad, c, c, new LimitStopsTripFilter(3, 1));
        outputTripCount(railroadService, railroad, a, c, new LimitStopsTripFilter(4));
    }

    private static void outputTripCount(IRailroadService railroadService, Railroad railroad, Town from, Town to, ITripFilter tripFilter) {
        int tripCount = railroadService.getTripCount(railroad, from, to, tripFilter);
        System.out.print("trips " + from.getId() + "-" + to.getId() + ":\t\t");//debug
        System.out.println(tripCount);
    }

    private static void solveDistanceProblems(IRailroadService railroadService, ITownRegistry townRegistry, Railroad railroad) {
        String[] problems = {"A-B-C", "A-D", "A-D-C", "A-E-B-C-D", "A-E-D"};
        for (String problem : problems) {
            String p = problem.replace("-", "");
            int len = p.length();
            Town[] route = new Town[len];
            for (int i = 0; i < len; i++) {
                route[i] = townRegistry.get(p.charAt(i));
            }
            System.out.print(problem + ":\t\t");//debug
            outputRouteDistance(railroadService, railroad, route);
        }
    }

    private static void outputRouteDistance(IRailroadService railroadService, Railroad railroad, Town[] route) {
        int distance = getRouteDistance(railroadService, railroad, route);
        if (distance == IRailroadService.INVALID_DISTANCE_VALUE) {
            System.out.println(IRailroadService.INVALID_DISTANCE_OUTPUT);
        } else {
            System.out.println(distance);
        }
    }

    private static int getRouteDistance(IRailroadService railroadService, Railroad railroad, Town[] route) {
        return railroadService.getRouteDistance(railroad, route);
    }
}
