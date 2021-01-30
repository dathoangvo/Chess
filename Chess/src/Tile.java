import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {
    private Color color;
    GlobalData GD = new GlobalData();

    public Tile(int index, int grid_data) {
        color = getColor(getColorData(grid_data));
        int x = getXCoordinate(index) * GD.tileWidth;
        int y = getYCoordinate(index) * GD.tileHeight;
        setBounds(x, y, GD.tileWidth, GD.tileHeight);
    }

    private int getColorData(int grid_data) {
        return (grid_data % 100) / 10;
    }

    private Color getColor(int color_data) {
        switch (color_data) {
            case 0:
                return Color.orange;
            case 1:
                return Color.darkGray;
            case 2:
                return Color.yellow;
            case 3:
                return Color.blue;
            case 4:
                return Color.pink;
            case 5:
                return Color.red;
        }
        return Color.white;
    }

    public int getYCoordinate(int index) {
        int counter = 0;
        while (index >= 8) {
            counter++;
            index -= 8;
        }
        return counter;
    }

    public int getXCoordinate(int index) {
        return index % 8;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, GD.tileWidth, GD.tileHeight);
    }
}
