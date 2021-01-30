import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mainFrame {
    private GlobalData GD = new GlobalData();

    public mainFrame() {
        JFrame frame = new JFrame("Chess");
        Board board = new Board();
        frame.setSize(GD.screenWidth, GD.screenHeight);
        frame.add(board.getBoardPanel());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x = e.getX() - 7;
                int y = e.getY() - 30;
                board.setClicked(x, y);
            }
        });
    }
}
