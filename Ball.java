/**
 * Auto Generated Java Class.
 */
public class Ball {
  
  private double rx, ry; // position
  private double vx, vy; // velocity
  private final double radius; // radius
  
  public Ball()
  { 
    /* initialize position and velocity */ 
    this(0.5, 0.5, 0.5, 0.5);
  }
  
  public Ball(double x, double y, double velx, double vely)
  { 
    /* initialize position and velocity */ 
    this(x, y, velx, vely, 0.5);   
  }
  
  public Ball(double x, double y, double velx, double vely, double radt)
  { 
    /* initialize position and velocity */ 
    rx = x;
    ry = y;
    vx = velx;
    vy = vely;
    radius = radt;
  }
  
  
  
  public void move(double dt)
  {
    //соприкосновение со стеной
    if ((rx + vx*dt < radius) || (rx + vx*dt > 1.0 - radius)) { vx = -vx; }
    if ((ry + vy*dt < radius) || (ry + vy*dt > 1.0 - radius)) { vy = -vy; }
    //иначе движение
    rx = rx + vx*dt;
    ry = ry + vy*dt;
  }
  
  public void draw()
  { 
    StdDraw.filledCircle(rx, ry, radius); 
  }
  
}
