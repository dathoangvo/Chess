public class Pawn {
    public int[] updateGridPawn(int index, int[] grid) {
        int team = grid[index] / 100;
        if (team == 2) { return whitePawn(index, grid, team); }
        return new int[0];
    }

    private int[] whitePawn(int index, int[] grid, int team) {
        int forward_one = index - 8;
        int forward_two = index - 16;
        int attack_one = index - 9;
        int attack_two = index - 7;
        if (inBound(forward_one)) { grid[forward_one] = setPossibleMoveTile(grid[forward_one]); }
        if (inBound(attack_one)) { grid[attack_one] = setPossibleAttackTile(grid[attack_one], team);}
        if (inBound(attack_two)) { grid[attack_two] = setPossibleAttackTile(grid[attack_two], team);}
        if (inBound(forward_two) && isPawnOnFirstRow(2, index)) {grid[forward_two] = setPossibleMoveTile(forward_two);}
        return grid;
    }

    private int setPossibleMoveTile(int grid_data) {
        if (grid_data % 10 != 0) {
            return grid_data;
        } else {
            int team = grid_data / 100;
            int moveNum = 3;
            int piece = (grid_data % 10);
            return (team*100) + (moveNum*10) + piece;
        }
    }

    private int setPossibleAttackTile(int grid_data, int team) {
        if ((grid_data/100) == team || (grid_data%10) == 0) {
            return grid_data;
        } else {
            int temp_team = grid_data / 100;
            int attackNum = 4;
            int piece = grid_data % 10;
            return (temp_team*100) + (attackNum*10) + piece;
        }
    }

    private boolean inBound(int n) {
        return n >= 0 && n < 64;
    }

    private boolean isPawnOnFirstRow(int team, int index) {
        if (team == 3) { return index > 7 && index < 15; }
        else if (team == 2) { return index > 47 && index < 56; }
        return false;
    }

    private int[] promotePawn(int[] grid) {
        return new int[0];
    }

}
