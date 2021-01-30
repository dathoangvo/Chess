import javax.swing.*;

public class Board {
    private int[] grid;
    private JPanel panel;

    private boolean whites_turn;
    private boolean piece_selected;

    Pawn pawn = new Pawn();

    public GlobalData gd = new GlobalData();

    public Board() {
        panel = new JPanel();
        grid = setGrid();
        whites_turn = true;
        piece_selected = false;
        drawAllComponents();
        panel.setLayout(null);
    }

    public void setClicked(int x, int y) {
        int tile_x = x / gd.tileWidth;
        int tile_y = y / gd.tileHeight;
        System.out.println(validSelection(getIndex(tile_x, tile_y)));
        if (validSelection(getIndex(tile_x, tile_y))) {
            int index = getIndex(tile_x, tile_y);
            int piece = getPiece(grid[index]);
            if (piece == 1) {
                grid = pawn.updateGridPawn(index, grid);
            }

            clearGUI();
            drawAllComponents();
        }
    }

    private boolean validSelection(int index) {
        if (whites_turn && !piece_selected) {
            return getTeam(grid[index]) == 2;
        } else if (!whites_turn && !piece_selected) {
            return getTeam(grid[index]) == 3;
        } else {
            int color = getColor(grid[index]);
            return color == 2 || color == 3 || color == 4;
        }
    }

    private int getIndex(int x, int y) {
        return x + (y * 8);
    }

    private int getColor(int grid_data) {
        return (grid_data % 100) / 10;
    }

    private int getTeam(int grid_data) {
        return (grid_data / 100);
    }

    private int getPiece(int grid_data) {
        return grid_data % 10;
    }

    /**
     * What the numbers mean
     * 1xx = empty
     * 2xx = white
     * 3xx = black
     *
     * x0x = base color 1
     * x1x = base color 2
     * x2x = selected
     * x3x = possible move location
     * x4x = possible take location
     * x5x = check
     *
     * xx0 = empty
     * xx1 = pawn
     * xx2 = rook
     * xx3 = knight
     * xx4 = bishop
     * xx5 = queen
     * xx6 = king
     *
     */
    /**
     * This method initializes the starting grid layout. Should only be used during start of program.
     * @return an array with the data used to layout the board color and pieces.
     */
    private int[] setGrid() {
        int[] return_grid = new int[]
                {302, 313, 304, 315, 306, 314, 303, 312,
                 311, 301, 311, 301, 311, 301, 311, 301,
                 100, 110, 100, 110, 100, 110, 100, 110,
                 110, 100, 110, 100, 110, 100, 110, 100,
                 100, 110, 100, 110, 100, 110, 100, 110,
                 110, 100, 110, 100, 110, 100, 110, 100,
                 201, 211, 201, 211, 201, 211, 201, 211,
                 212, 203, 214, 205, 216, 204, 213, 202};
        return return_grid;
    }

    private void clearGUI() {
        panel.removeAll();
        panel.repaint();
        panel.revalidate();
    }

    private void drawAllComponents() {
        Pieces piece;
        for (int i = 0; i < 64; i++) {
            piece = new Pieces(i, grid[i]);
            panel.add(piece);
        }
    }

    public JPanel getBoardPanel() {return panel;}
}
