import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
  int w = 300;
  int h = 400;

  Ball ball = new Ball(this);
  Racket racket = new Racket(this);

  int speed = 1;

  private int getScore() {
    return speed - 1;
  }

  public Game() {
    addKeyListener(
      new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {
          racket.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
          racket.keyPressed(e);
        }
      }
    );
    setFocusable(true);
  }

  private void move() {
    ball.move();
    racket.move();
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(
      RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON
    );
    ball.paint(g2d);
    racket.paint(g2d);

    g2d.setColor(Color.GRAY);
    g2d.setFont(new Font("Verdana", Font.BOLD, 30));
    g2d.drawString(String.valueOf(getScore()), 10, 30);
  }

  public void gameOver() {
    JOptionPane.showMessageDialog(this, "Your score: " + getScore(),
      "Game over!", JOptionPane.YES_NO_OPTION);
    System.exit(ABORT);
  }

  public static void main(String[] args) throws InterruptedException {
    JFrame frame = new JFrame("Mini Tennis");
    Game game = new Game();
    
    frame.add(game);
    frame.setSize(game.w, game.h);
    frame.setBackground(Color.DARK_GRAY);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    while (true) {
      game.move();
      game.repaint();
      Thread.sleep(10);
    }
  }
}
