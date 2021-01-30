import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class Pieces extends Tile {
    private Image transparentImg;
    private GlobalData GD = new GlobalData();

    public Pieces(int index, int grid_data) {
        super(index, grid_data);
        String path = getPath(getTeam(grid_data), getPiece(grid_data));
        transparentImg = filterImage(path);
    }

    private Image filterImage(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        ImageFilter filter = new RGBImageFilter() {
            int transparentColor = Color.white.getRGB() | 0xFF000000;
            @Override
            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == transparentColor) {
                    return 0xFF000000 & rgb;
                } else {
                    return rgb;
                }
            }
        };
        ImageProducer filteredImage = new FilteredImageSource(imageIcon.getImage().getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(filteredImage);
    }

    private int getTeam(int grid_data) {
        return grid_data / 100;
    }

    private int getPiece(int grid_data) {
        return grid_data % 10;
    }

    private String getPath(int team, int piece) {
        //System.out.println("team:" + team + " piece:" + piece);
        String builder = "";
        switch (team) {
            case 0:
            case 1:
                return null;
            case 2:
                builder += "White_Piece\\";
                break;
            case 3:
                builder += "Black_Piece\\";
                break;
        }
        switch (piece) {
            case 0:
                return null;
            case 1:
                builder += "Pawn";
                break;
            case 2:
                builder += "Rook";
                break;
            case 3:
                builder += "Knight";
                break;
            case 4:
                builder += "Bishop";
                break;
            case 5:
                builder += "Queen";
                break;
            case 6:
                builder += "King";
                break;
        }
        return builder + ".png";
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
        g.setColor(getBackground());
        g.drawImage(transparentImg, GD.tileWidth / 3, GD.tileHeight / 8, this);
    }
}
