package ge.map.djikra.Logic;
import ge.map.djikra.Drawables.GridCanvas;
import java.util.*;
public class Dijkstra {
    public static List<GridCanvas.Cell> findClosestPath(GridCanvas.Cell[][] grid, GridCanvas.Cell startPoint, GridCanvas.Cell endPoint)
    {
        Map<GridCanvas.Cell, GridCanvas.Cell> cameFrom = new HashMap<>();
        Map<GridCanvas.Cell, Integer> shortestDistance = new HashMap<>();
        PriorityQueue<GridCanvas.Cell> nextNodesToExplore = new PriorityQueue<>(Comparator.comparingInt(shortestDistance::get));

        for (GridCanvas.Cell[] row : grid) {
            for (GridCanvas.Cell cell : row) {
                shortestDistance.put(cell, Integer.MAX_VALUE);
            }
        }

        shortestDistance.put(startPoint, 0);
        nextNodesToExplore.add(startPoint);

        while (!nextNodesToExplore.isEmpty()) {
            GridCanvas.Cell currentStandingCell = nextNodesToExplore.poll();
            if (currentStandingCell == endPoint) break;

            for (GridCanvas.Cell neighbourCell : currentStandingCell.getNeighbors(grid)) {
                int alt = shortestDistance.get(currentStandingCell) + 1;
                if (alt < shortestDistance.get(neighbourCell)) {
                    shortestDistance.put(neighbourCell, alt);
                    cameFrom.put(neighbourCell, currentStandingCell);
                    nextNodesToExplore.add(neighbourCell);
                }
            }
        }

        if (!cameFrom.containsKey(endPoint)) return null;

        List<GridCanvas.Cell> path = new ArrayList<>();
        for (GridCanvas.Cell at = endPoint; at != null; at = cameFrom.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
