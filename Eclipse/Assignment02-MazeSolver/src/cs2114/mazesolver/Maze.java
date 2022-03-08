package cs2114.mazesolver;

import java.util.Stack;

/**
 * @author jake
 * @version 2017.04.09
 */
public class Maze implements IMaze {
    private MazeCell[][] board;
    private int size;
    private Location start;
    private Location goal;

    /**
     * @param inSize
     *            size of the maze
     */
    public Maze(int inSize) {
        size = inSize;
        board = new MazeCell[inSize][inSize];
        for (int i = 0; i < size; i++) {
            for (int a = 0; a < size; a++) {
                board[i][a] = MazeCell.UNEXPLORED;
            }
        }
        start = new Location(0, 0);
        goal = new Location(size - 1, size - 1);
    }

    /**
     * @return size of maze
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return start
     */
    @Override
    public ILocation getStartLocation() {
        return start;
    }

    /**
     * @param location
     *            to set as new start
     */
    @Override
    public void setStartLocation(ILocation location) {
        if (getCell(location) == MazeCell.WALL) {
            setCell(location, MazeCell.UNEXPLORED);
        }
        start = (Location) location;
    }

    /**
     * @return goal
     */
    @Override
    public ILocation getGoalLocation() {
        return goal;
    }

    /**
     * @param location
     *            to set as new goal
     */
    @Override
    public void setGoalLocation(ILocation location) {
        if (getCell(location) == MazeCell.WALL) {
            setCell(location, MazeCell.UNEXPLORED);
        }
        goal = (Location) location;
    }

    /**
     * @param location
     *            location of cell
     * @return the MazeCell at that location
     */
    @Override
    public MazeCell getCell(ILocation location) {
        try {
            return board[((Location) location).x()][((Location) location).y()];
        }
        catch (Exception e) {
            // do nothing
        }
        return MazeCell.INVALID_CELL;
    }

    /**
     * @param location
     *            location to set
     * @param cell
     *            cell to set at location
     */
    @Override
    public void setCell(ILocation location, MazeCell cell) {
        try {
            if ((!start.equals((Location) location)
                    && !goal.equals((Location) location))
                    || (cell != MazeCell.WALL)) {
                board[((Location) location).x()][((Location) location)
                        .y()] = cell;
            }
        }
        catch (IndexOutOfBoundsException e) {
            // do nothing
        }
    }

    /**
     * @return returns the solution to the maze
     */
    @Override
    public String solve() {
        Stack<Location> solution = new Stack<Location>();
        Location curr = start;
        this.setCell(curr, MazeCell.CURRENT_PATH);
        solution.push(curr);
        while (!solution.empty() && !curr.equals(goal)) {
            if (this.getCell(curr.north()) == MazeCell.UNEXPLORED) {
                curr = (Location) curr.north();
                this.setCell(curr, MazeCell.CURRENT_PATH);
                solution.push(curr);
            }
            else if (this.getCell(curr.south()) == MazeCell.UNEXPLORED) {
                curr = (Location) curr.south();
                this.setCell(curr, MazeCell.CURRENT_PATH);
                solution.push(curr);
            }
            else if (this.getCell(curr.west()) == MazeCell.UNEXPLORED) {
                curr = (Location) curr.west();
                this.setCell(curr, MazeCell.CURRENT_PATH);
                solution.push(curr);
            }
            else if (this.getCell(curr.east()) == MazeCell.UNEXPLORED) {
                curr = (Location) curr.east();
                this.setCell(curr, MazeCell.CURRENT_PATH);
                solution.push(curr);
            }
            else {
                this.setCell(solution.pop(), MazeCell.FAILED_PATH);
                if (!solution.empty()) {
                    curr = solution.peek();
                }
            }
        }
        if (solution.empty()) {
            return null;
        }
        Stack<Location> reverse = new Stack<Location>();
        while (!solution.empty()) {
            reverse.push(solution.pop());
        }
        String result = "";
        while (!reverse.empty()) {
            result += reverse.pop().toString() + " ";
        }
        return result.trim();
    }

}
