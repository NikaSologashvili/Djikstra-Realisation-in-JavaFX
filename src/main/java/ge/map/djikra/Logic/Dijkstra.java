package ge.map.djikra.Logic;

import ge.map.djikra.Drawables.GridCanvas;

import java.util.*;

public class Dijkstra {
    public static List<GridCanvas.Cell> findClosestPath(GridCanvas.Cell[][] grid, GridCanvas.Cell startPoint, GridCanvas.Cell endPoint) {

        Map<GridCanvas.Cell, GridCanvas.Cell> cameFrom = new HashMap<>();
        Map<GridCanvas.Cell, Integer> shortestDistance = new HashMap<>();
        PriorityQueue<GridCanvas.Cell> nextNodesToExploreWithLowestDistance = new PriorityQueue<>(Comparator.comparingInt(shortestDistance::get));
        //a priority queue  that always gives us the cell with the lowest distance next.
        for (GridCanvas.Cell[] row : grid) {
            for (GridCanvas.Cell cell : row) {
                shortestDistance.put(cell, Integer.MAX_VALUE);
                //Initially set the distance to every cell as infinity meaning they are unreachable for now.
            }
        }

        shortestDistance.put(startPoint, 0);
        //distance to himself
        nextNodesToExploreWithLowestDistance.add(startPoint);

        while (!nextNodesToExploreWithLowestDistance.isEmpty()) {
            GridCanvas.Cell currentStandingCell = nextNodesToExploreWithLowestDistance.poll();
            if (currentStandingCell == endPoint) break;

            for (GridCanvas.Cell neighbourCell : currentStandingCell.getNeighbors(grid)) {
                int potentialDistance = shortestDistance.get(currentStandingCell) + 1;
                //calculate every way and overwrite if shorter
                if (potentialDistance < shortestDistance.get(neighbourCell)) {
                    shortestDistance.put(neighbourCell, potentialDistance);
                    cameFrom.put(neighbourCell, currentStandingCell);
                    nextNodesToExploreWithLowestDistance.add(neighbourCell);
                }
            }
        }

        if (!cameFrom.containsKey(endPoint)) return null;

        List<GridCanvas.Cell> path = new ArrayList<>();
        //reconstruct path and return
        for (GridCanvas.Cell at = endPoint; at != null; at = cameFrom.get(at)) {
            path.add(at);
        }
        //reverese imitom rom uknidan winaa back to front
        Collections.reverse(path);
        return path;
    }
}
