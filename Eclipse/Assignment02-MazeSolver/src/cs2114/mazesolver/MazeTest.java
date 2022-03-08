package cs2114.mazesolver;

import student.TestCase;

/**
 * @author jake
 * @version 2017.04.09
 */
public class MazeTest extends TestCase {
    private Maze maze1;
    private Maze maze2;
    private Maze maze3;
    private Location[][] locations;

    /**
     * runs before each test
     */
    public void setUp() {
        locations = new Location[5][5];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                locations[x][y] = new Location(x, y);
            }
        }
        maze1 = new Maze(5);
        maze2 = new Maze(4);
        maze3 = new Maze(3);
    }

    /**
     * tests set and get methods for start, goal, and cell also tests size
     */
    public void testGetSetStartGoalCellSize() {
        assertEquals(locations[0][0], maze1.getStartLocation());
        maze1.setStartLocation(locations[1][1]);
        assertEquals(locations[1][1], maze1.getStartLocation());
        maze1.setCell(locations[2][2], MazeCell.WALL);
        assertEquals(MazeCell.WALL, maze1.getCell(locations[2][2]));
        maze1.setStartLocation(locations[2][2]);
        assertEquals(MazeCell.UNEXPLORED, maze1.getCell(locations[2][2]));
        assertEquals(locations[4][4], maze1.getGoalLocation());
        maze1.setGoalLocation(locations[1][1]);
        assertEquals(locations[1][1], maze1.getGoalLocation());
        maze1.setCell(locations[3][3], MazeCell.WALL);
        assertEquals(MazeCell.WALL, maze1.getCell(locations[3][3]));
        maze1.setGoalLocation(locations[3][3]);
        assertEquals(MazeCell.UNEXPLORED, maze1.getCell(locations[3][3]));
        maze1.setCell(locations[3][3], MazeCell.WALL);
        assertEquals(MazeCell.UNEXPLORED, maze1.getCell(locations[3][3]));
        maze1.setCell(locations[2][2], MazeCell.WALL);
        assertEquals(MazeCell.UNEXPLORED, maze1.getCell(locations[2][2]));
        maze2.setCell(locations[4][4], MazeCell.FAILED_PATH);
        assertEquals(MazeCell.INVALID_CELL, maze2.getCell(locations[4][4]));
        assertEquals(5, maze1.size());
        assertEquals(4, maze2.size());
    }

    /**
     * test solve method
     * 
     * maze1:
     * 
     * S W U W U
     * 
     * U U U W U
     * 
     * W W U W U
     * 
     * U U U U U
     * 
     * U W U W G
     * 
     * maze3:
     * 
     * U U S
     * 
     * W W W
     * 
     * W G U
     */
    public void testSolve() {
        maze1.setCell(locations[1][0], MazeCell.WALL);
        maze1.setCell(locations[3][0], MazeCell.WALL);
        maze1.setCell(locations[3][1], MazeCell.WALL);
        maze1.setCell(locations[0][2], MazeCell.WALL);
        maze1.setCell(locations[1][2], MazeCell.WALL);
        maze1.setCell(locations[3][2], MazeCell.WALL);
        maze1.setCell(locations[1][4], MazeCell.WALL);
        maze1.setCell(locations[3][4], MazeCell.WALL);

        maze3.setCell(locations[0][1], MazeCell.WALL);
        maze3.setCell(locations[1][1], MazeCell.WALL);
        maze3.setCell(locations[2][1], MazeCell.WALL);
        maze3.setCell(locations[0][2], MazeCell.WALL);
        maze3.setStartLocation(locations[2][0]);
        maze3.setGoalLocation(locations[1][2]);

        assertEquals("(0, 0) (0, 1) (1, 1) (2, 1) " + "(2, 2)" + " (2, 3)"
                + " (3, 3)" + " (4, 3)" + " (4, 4)", maze1.solve());
        assertNull(maze3.solve());
    }

    /**
     * tests MazeCell
     */
    public void testMazeCell() {
        MazeCell n;
        MazeCell test;
        test = MazeCell.CURRENT_PATH;
        assertEquals(test, MazeCell.CURRENT_PATH);
        n = MazeCell.valueOf("CURRENT_PATH");
        assertEquals(n, test);
        test = MazeCell.FAILED_PATH;
        assertEquals(test, MazeCell.FAILED_PATH);
        n = MazeCell.valueOf("FAILED_PATH");
        assertEquals(n, test);
        test = MazeCell.INVALID_CELL;
        assertEquals(test, MazeCell.INVALID_CELL);
        n = MazeCell.valueOf("INVALID_CELL");
        assertEquals(n, test);
        test = MazeCell.UNEXPLORED;
        assertEquals(test, MazeCell.UNEXPLORED);
        n = MazeCell.valueOf("UNEXPLORED");
        assertEquals(n, test);
        test = MazeCell.WALL;
        assertEquals(test, MazeCell.WALL);
        n = MazeCell.valueOf("WALL");
        assertEquals(n, test);
    }
}
