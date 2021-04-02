import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
  static final int DIAMETER = 30;
  int x = 0;
  int y = 0;
  int dx = 1;
  int dy = 1;
  private Game game;

  public Ball(Game game) {
    this.game = game;
  }

  void move() {
    if (x + dx < 0) {
      dx = game.speed; 
    } else if (x + dx > game.getWidth() - DIAMETER) {
      dx = -game.speed; 
    } else if (y + dy < 0) {
      dy = game.speed; 
    } else if (y + dy > game.getHeight() - DIAMETER) {
      game.gameOver(); 
    } else if (collision()) {
      game.speed++;
      dy = -game.speed;
      y = game.racket.getTopY() - DIAMETER;
    } 

    x += dx;
    y += dy;
  }

  private boolean collision() {
    return game.racket.getBounds().intersects(getBounds());
  }

  public void paint(Graphics2D g) {
    g.fillOval(x, y, DIAMETER, DIAMETER);
  }

  public Rectangle getBounds() {
    return new Rectangle(x, y, DIAMETER, DIAMETER);
  }
}
