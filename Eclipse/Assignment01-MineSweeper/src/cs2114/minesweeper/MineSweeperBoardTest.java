package cs2114.minesweeper;

import student.TestCase;

/**
 * @author jake
 * @version 2017.03.04
 */
public class MineSweeperBoardTest extends TestCase {
    private MineSweeperBoard board1;
    private MineSweeperBoard board2;

    /**
     * setUp method runs before tests
     */
    public void setUp() {
        board1 = new MineSweeperBoard(4, 5, 3);
        board2 = new MineSweeperBoard(3, 3, 0);
    }

    /**
     * test for adjacent to test
     */
    public void testAdjacentTo() {
        MineSweeperCell c = MineSweeperCell.ADJACENT_TO_0;
        assertNotNull(c);
        // testing for exception
        Exception thrown = null;
        try {
            c = MineSweeperCell.adjacentTo(10);
        }
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            MineSweeperCell.adjacentTo(-1);
        }
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        assertNotNull(MineSweeperCell.values());
        assertNotNull(MineSweeperCell
                .valueOf(MineSweeperCell.ADJACENT_TO_0.toString()));

    }

    /**
     * test loadBoardState()
     */
    public void testloadBoardState() {
        MineSweeperBoard a = new MineSweeperBoard(2, 2, 1);
        Exception thrown = null;
        // loadBoardState testing
        // wrong number of rows
        try {
            a.loadBoardState("00");
        }
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        thrown = null;
        // wrong number of columns
        try {
            a.loadBoardState("0000 ", " ");
        }
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        // Wrong symbol in cell
        try {
            a.loadBoardState("00", "$+");
        }
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    /**
     * * This method test Equals.
     */
    public void testEqual() {
        MineSweeperBoard mBoard1 = new MineSweeperBoard(4, 4, 6);
        MineSweeperBoard mBoard2 = new MineSweeperBoard(4, 4, 6);
        mBoard1.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        mBoard2.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        // test the same board same dimensions
        assertTrue(mBoard1.equals(mBoard2));
        // same board testing same board
        assertTrue(mBoard1.equals(mBoard1));
        // testing same dimensions board with different cell
        MineSweeperBoard mBoard3 = new MineSweeperBoard(4, 4, 6);
        mBoard3.loadBoardState("    ", "O+OO", "O++O", "OOOO");
        assertFalse(mBoard1.equals(mBoard3));
        MineSweeperBoard mBoard4 = new MineSweeperBoard(15, 1, 0);
        mBoard4.loadBoardState("OFM+* 123456788");
        assertFalse(mBoard1.toString().equals(mBoard3.toString()));
        // testing two string against a board
        assertFalse(mBoard4.toString().equals(mBoard2.toString()));
        // testing against a string
        assertFalse(mBoard1.equals("abc"));
        assertNotNull(mBoard1);
        // same width but different height
        MineSweeperBoard mBoard6 = new MineSweeperBoard(4, 5, 6);
        mBoard6.loadBoardState("    ", "O+OO", "O++O", "OOOO", "OOOO");
        assertFalse(mBoard6.equals(mBoard1));
        // different width same height
        MineSweeperBoard mBoard5 = new MineSweeperBoard(5, 4, 6);
        mBoard5.loadBoardState("     ", "O+OOO", "O++OO", "OOOOO");
        assertFalse(mBoard5.equals(mBoard1));
    }

    /**
     * Test the Constructor.
     */
    public void testConstructor() {
        String board = board1.toString();
        int cover = 0;
        int mine = 0;
        for (int x = 0; x < board.length(); x++) {
            if (board.charAt(x) == 'O') {
                cover++; // counts empty tiles
            }
            else if (board.charAt(x) == '+') {
                mine++; // counts mine tiles
            }
        }
        assertEquals((4 * 5) - 3, cover);
        assertEquals(3, mine);
    }

    /**
     * tests setCell(), getCell(), width(), height()
     */
    public void testGettersSetters() {
        board1.setCell(2, 2, MineSweeperCell.MINE);
        assertEquals(MineSweeperCell.MINE, board1.getCell(2, 2));
        board1.setCell(2, 2, MineSweeperCell.COVERED_CELL);
        assertEquals(MineSweeperCell.COVERED_CELL, board1.getCell(2, 2));
        assertEquals(4, board1.width());
        assertEquals(5, board1.height());
    }

    /**
     * tests uncoverCell(), revealBoard(), numberOfAdjacentMines(), flagCell()
     */
    public void testCellModifiers() {
        board2.uncoverCell(1, 1);
        assertEquals(MineSweeperCell.ADJACENT_TO_0, board2.getCell(1, 1));
        assertEquals(0, board2.numberOfAdjacentMines(1, 1));

        board2.loadBoardState("O+O", "OOO", "OOO");
        board2.uncoverCell(1, 1);
        assertEquals(MineSweeperCell.ADJACENT_TO_1, board2.getCell(1, 1));
        assertEquals(1, board2.numberOfAdjacentMines(1, 1));

        board2.loadBoardState("O+O", "OOO", "O+O");
        board2.uncoverCell(1, 1);
        assertEquals(MineSweeperCell.ADJACENT_TO_2, board2.getCell(1, 1));
        assertEquals(2, board2.numberOfAdjacentMines(1, 1));

        board2.loadBoardState("O+O", "+OO", "O+O");
        board2.uncoverCell(1, 1);
        assertEquals(MineSweeperCell.ADJACENT_TO_3, board2.getCell(1, 1));
        assertEquals(3, board2.numberOfAdjacentMines(1, 1));

        board2.loadBoardState("O+O", "+O+", "O+O");
        board2.uncoverCell(1, 1);
        assertEquals(MineSweeperCell.ADJACENT_TO_4, board2.getCell(1, 1));
        assertEquals(4, board2.numberOfAdjacentMines(1, 1));

        board2.loadBoardState("++O", "+O+", "O+O");
        board2.uncoverCell(1, 1);
        assertEquals(MineSweeperCell.ADJACENT_TO_5, board2.getCell(1, 1));
        assertEquals(5, board2.numberOfAdjacentMines(1, 1));

        board2.loadBoardState("+++", "+O+", "O+O");
        board2.uncoverCell(1, 1);
        assertEquals(MineSweeperCell.ADJACENT_TO_6, board2.getCell(1, 1));
        assertEquals(6, board2.numberOfAdjacentMines(1, 1));

        board2.loadBoardState("+++", "+O+", "++O");
        board2.uncoverCell(1, 1);
        assertEquals(MineSweeperCell.ADJACENT_TO_7, board2.getCell(1, 1));
        assertEquals(7, board2.numberOfAdjacentMines(1, 1));

        board2.loadBoardState("+++", "+O+", "+++");
        board2.uncoverCell(1, 1);
        assertEquals(MineSweeperCell.ADJACENT_TO_8, board2.getCell(1, 1));
        assertEquals(8, board2.numberOfAdjacentMines(1, 1));

        board2.loadBoardState("+++", "+++", "+++");
        board2.uncoverCell(1, 1);
        assertEquals(MineSweeperCell.UNCOVERED_MINE, board2.getCell(1, 1));

        board2.loadBoardState("***", "***", "***");
        board2.uncoverCell(1, 1);
        assertEquals(MineSweeperCell.UNCOVERED_MINE, board2.getCell(1, 1));

        board2.loadBoardState("O+O", "+O+", "O+O");
        board2.uncoverCell(0, 0);
        assertEquals(MineSweeperCell.ADJACENT_TO_2, board2.getCell(0, 0));
        assertEquals(2, board2.numberOfAdjacentMines(0, 0));

        board2.loadBoardState("O+O", "+O*", "OMO");
        board2.uncoverCell(2, 2);
        assertEquals(MineSweeperCell.ADJACENT_TO_2, board2.getCell(2, 2));
        assertEquals(2, board2.numberOfAdjacentMines(2, 2));

        board2.loadBoardState("O+O", "+O+", "O+O");
        board2.uncoverCell(0, 2);
        assertEquals(MineSweeperCell.ADJACENT_TO_2, board2.getCell(0, 2));
        assertEquals(2, board2.numberOfAdjacentMines(0, 2));

        board2.loadBoardState("O+O", "+O+", "O+O");
        board2.uncoverCell(2, 0);
        assertEquals(MineSweeperCell.ADJACENT_TO_2, board2.getCell(2, 0));
        assertEquals(2, board2.numberOfAdjacentMines(2, 0));

        board2.loadBoardState("OOO", "OOO", "OOO");
        board2.revealBoard();
        assertEquals(true, !board2.toString().contains("O"));

        board2.loadBoardState("+*O", "1MF", "OOO");
        board2.flagCell(0, 0);
        board2.flagCell(1, 0);
        board2.flagCell(2, 0);
        board2.flagCell(0, 1);
        board2.flagCell(1, 1);
        board2.flagCell(2, 1);
        assertEquals(MineSweeperCell.FLAGGED_MINE, board2.getCell(0, 0));
        assertEquals(MineSweeperCell.UNCOVERED_MINE, board2.getCell(1, 0));
        assertEquals(MineSweeperCell.FLAG, board2.getCell(2, 0));
        assertEquals(MineSweeperCell.ADJACENT_TO_1, board2.getCell(0, 1));
        assertEquals(MineSweeperCell.MINE, board2.getCell(1, 1));
        assertEquals(MineSweeperCell.COVERED_CELL, board2.getCell(2, 1));
    }

    /**
     * tests isGameWon() tests isGameLost()
     */
    public void testWinLoss() {
        board2.loadBoardState("   ", "   ", "   ");
        assertEquals(true, board2.isGameWon());
        assertEquals(false, board2.isGameLost());

        board2.loadBoardState(" O ", "   ", "   ");
        assertEquals(false, board2.isGameWon());
        assertEquals(false, board2.isGameLost());

        board2.loadBoardState(" F ", "   ", "   ");
        assertEquals(false, board2.isGameWon());
        assertEquals(false, board2.isGameLost());

        board2.loadBoardState(" M ", "   ", "   ");
        assertEquals(true, board2.isGameWon());
        assertEquals(false, board2.isGameLost());

        board2.loadBoardState(" + ", "   ", "   ");
        assertEquals(false, board2.isGameWon());
        assertEquals(false, board2.isGameLost());

        board2.loadBoardState(" * ", "   ", "   ");
        assertEquals(false, board2.isGameWon());
        assertEquals(true, board2.isGameLost());
    }
}
