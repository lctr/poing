import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racket {
  private Game game;
  private static final int Y = 330;
  private static final int WIDTH = 60;
  private static final int HEIGHT = 10;
  int x = 0;
  int dx = 0;

  public Racket(Game game) {
    this.game = game;
  }

  public void move() {
    if (x + dx > 0 && x + dx < game.getWidth() - WIDTH) 
      x += dx;
  }

  public void paint(Graphics2D g) {
    g.fillRect(x, Y, WIDTH, HEIGHT);
  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) 
      dx = -game.speed;
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
      dx = game.speed;
  }

  public void keyReleased(KeyEvent e) {
    dx = 0;
  }

  public Rectangle getBounds() {
    return new Rectangle(x, Y, WIDTH, HEIGHT);
  }

  public int getTopY() {
    return Y - HEIGHT;
  }
}
