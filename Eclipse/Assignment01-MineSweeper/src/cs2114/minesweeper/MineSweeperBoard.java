package cs2114.minesweeper;

import java.util.Random;

/**
 * @author jake
 * @version 2017.03.04
 */
public class MineSweeperBoard extends MineSweeperBoardBase {
    private MineSweeperCell[][] board;

    /**
     * @param inWidth
     *            board width
     * @param inHeight
     *            board height
     * @param inMines
     *            number of mines to be randomly placed
     */
    public MineSweeperBoard(int inWidth, int inHeight, int inMines) {
        board = new MineSweeperCell[inHeight][inWidth];
        Random num = new Random();
        for (int y = 0; y < inHeight; y++) {
            for (int x = 0; x < inWidth; x++) {
                board[y][x] = MineSweeperCell.COVERED_CELL;
            }
        }
        for (int x = 0; x < inMines; x++) {
            int height = num.nextInt(inHeight);
            int width = num.nextInt(inWidth);
            if (board[height][width].equals(MineSweeperCell.MINE)) {
                x--;
            }
            else {
                board[height][width] = MineSweeperCell.MINE;
            }
        }
    }

    /**
     * @return width of board
     */
    @Override
    public int width() {
        return board[0].length;
    }

    /**
     * @return height of board
     */
    @Override
    public int height() {
        return board.length;
    }

    /**
     * @param x
     *            column of cell
     * @param y
     *            row of cell
     * @return the cell at the location
     */
    @Override
    public MineSweeperCell getCell(int x, int y) {
        return board[y][x];
    }

    /**
     * @param x
     *            column of cell
     * @param y
     *            row of cell
     */
    @Override
    public void uncoverCell(int x, int y) {
        if (board[y][x].equals(MineSweeperCell.COVERED_CELL)) {
            int count = numberOfAdjacentMines(x, y);
            if (count == 0) {
                this.setCell(x, y, MineSweeperCell.ADJACENT_TO_0);
            }
            else if (count == 1) {
                this.setCell(x, y, MineSweeperCell.ADJACENT_TO_1);
            }
            else if (count == 2) {
                this.setCell(x, y, MineSweeperCell.ADJACENT_TO_2);
            }
            else if (count == 3) {
                this.setCell(x, y, MineSweeperCell.ADJACENT_TO_3);
            }
            else if (count == 4) {
                this.setCell(x, y, MineSweeperCell.ADJACENT_TO_4);
            }
            else if (count == 5) {
                this.setCell(x, y, MineSweeperCell.ADJACENT_TO_5);
            }
            else if (count == 6) {
                this.setCell(x, y, MineSweeperCell.ADJACENT_TO_6);
            }
            else if (count == 7) {
                this.setCell(x, y, MineSweeperCell.ADJACENT_TO_7);
            }
            else {
                this.setCell(x, y, MineSweeperCell.ADJACENT_TO_8);
            }
        }
        else if (board[y][x].equals(MineSweeperCell.MINE)) {
            this.setCell(x, y, MineSweeperCell.UNCOVERED_MINE);
        }
    }

    /**
     * @param x
     *            column of cell
     * @param y
     *            row of cell
     */
    @Override
    public void flagCell(int x, int y) {
        if (board[y][x].equals(MineSweeperCell.MINE)) {
            this.setCell(x, y, MineSweeperCell.FLAGGED_MINE);
        }
        else if (board[y][x].equals(MineSweeperCell.COVERED_CELL)) {
            this.setCell(x, y, MineSweeperCell.FLAG);
        }
        else if (board[y][x].equals(MineSweeperCell.FLAGGED_MINE)) {
            this.setCell(x, y, MineSweeperCell.MINE);
        }
        else if (board[y][x].equals(MineSweeperCell.FLAG)) {
            this.setCell(x, y, MineSweeperCell.COVERED_CELL);
        }
    }

    /**
     * @return true if game lost or false if not
     */
    @Override
    public boolean isGameLost() {
        return (this.toString().contains("*"));
    }

    /**
     * @return true if winning conditions are met
     */
    @Override
    public boolean isGameWon() {
        return (!this.toString().contains("O") && !this.toString().contains("F")
                && !this.toString().contains("+")
                && !this.toString().contains("*"));
    }

    /**
     * @param x
     *            column of cell
     * @param y
     *            row of cell
     * @return number of adjacent mines
     */
    @Override
    public int numberOfAdjacentMines(int x, int y) {
        int count = 0;
        for (int z = -1; z <= 1; z++) {
            for (int zz = -1; zz <= 1; zz++) {
                try {
                    if (board[y + z][x + zz].equals(MineSweeperCell.MINE)
                            || board[y + z][x + zz]
                                    .equals(MineSweeperCell.FLAGGED_MINE)
                            || board[y + z][x + zz]
                                    .equals(MineSweeperCell.UNCOVERED_MINE)) {
                        count++;
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    // do nothing
                }
            }
        }
        return count;
    }

    /**
     * uncover all cells
     */
    @Override
    public void revealBoard() {
        for (int y = 0; y < this.height(); y++) {
            for (int x = 0; x < this.width(); x++) {
                this.uncoverCell(x, y);
            }
        }
    }

    /**
     * @param x
     *            column of cell
     * @param y
     *            row of cell
     * @param value
     *            value of new cell
     */
    @Override
    protected void setCell(int x, int y, MineSweeperCell value) {
        board[y][x] = value;
    }

}
