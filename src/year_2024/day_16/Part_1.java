package year_2024.day_16;

import common.Direction;

import java.awt.*;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import static common.ImportFile.fileTo2DArray;

public class Part_1 {
    public static void main(String[] args) {
        String[][] mazeMap = fileTo2DArray("src\\year_2024\\day_16\\input.txt");

        PriorityQueue<PathState> pathFindingPriorityQueue = new PriorityQueue<>();
        pathFindingPriorityQueue.add(new PathState(findStart(mazeMap)));

        Set<String> memory = new HashSet<>();

//        Dijkstra's Algorithm (Or based on it)
        int shortestPathPoints;
        while (true) {
            PathState lowestPointPath = pathFindingPriorityQueue.poll();
            Point coords = lowestPointPath.getCoords();

//            Skip if been on the same coords same direction before
            String memoryString = coords.x + "," + coords.y + "," + lowestPointPath.getDirection();
            if (memory.contains(memoryString)) continue;
            else memory.add(memoryString);

//            End the loop if the end is reached
            if (mazeMap[coords.y][coords.x].equals("E")) {
                shortestPathPoints = lowestPointPath.getPoints();
                break;
            }

//            Add to queue for different possible directions
            for (Direction direction : Direction.values()) {
                if (!direction.isCardinal()) continue;
                if (isOppositeDirection(direction, lowestPointPath.getDirection())) continue;

                int newX = coords.x + direction.getDeltaX();
                int newY = coords.y + direction.getDeltaY();

                if (!mazeMap[newY][newX].equals("#")){
                    pathFindingPriorityQueue.add(new PathState(new Point(newX,newY),
                            lowestPointPath.getNewPoints(direction), direction));
                }
            }
        }

        System.out.println("Day 16, Part 1, Lowest score a Reindeer could possibly get: " + shortestPathPoints);
    }

    private static Point findStart(String[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                if (grid[row][col].equals("S")) return new Point(col, row);
            }
        }
        return null;
    }

//    Check if a direction is the opposite direction of another, could have been implemented in the enum
    private static boolean isOppositeDirection(Direction direction, Direction direction2) {
        return direction.getDeltaX() + direction2.getDeltaX() == 0 &&
                direction.getDeltaY() + direction2.getDeltaY() == 0;
    }
}

// State of a path in the Queue.
class PathState implements Comparable<PathState> {
    private int points;
    private Direction direction;
    private Point coords;

    PathState(Point coords, int points, Direction nextDirection) {
        this.coords = coords;
        this.points = points;
        this.direction = nextDirection;
    }

    PathState(Point coords) {
        this(coords, 0, Direction.EAST);
    }

    public Point getCoords() {
        return coords;
    }

    public void setCoords(Point coords) {
        this.coords = coords;
    }

    public void setCoords(int x, int y) {
        this.coords.x = x;
        this.coords.y = y;
    }

    public void addToCoords(int x, int y) {
        this.coords.x += x;
        this.coords.y += y;
    }

    public int getPoints() {
        return points;
    }

    public int getNewPoints(Direction direction) {
        if (direction == this.direction) return points + 1;
        else return points + 1001;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public Direction getDirection() {
        return direction;
    }


    @Override
    public int compareTo(PathState path) {
        return Integer.compare(this.points, path.points);
    }

    @Override
    public String toString() {
        return "PathState{" +
                "Points: " + points +
                ", Direction: " + direction +
                ", Coords: (" + coords.x + ", " + coords.y+
                ")}";
    }
}
