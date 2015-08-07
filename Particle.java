/**
 * Auto Generated Java Class.
 */
public class Particle 
{
  
  private double rx, ry; // position
  private double vx, vy; // velocity
  private final double radius; // radius
  private final double mass; // mass
  private int count; // number of collisions
  
   // create a random particle in the unit box (overlaps not checked)
    public Particle() 
    {
        rx     = Math.random();
        ry     = Math.random();
        vx     = 0.01 * (Math.random() - 0.5);
        vy     = 0.01 * (Math.random() - 0.5);
        radius = 0.01;
        mass   = 0.5;
    }
  
  public Particle(double x, double y, double velx, double vely)
  { 
    /* initialize position and velocity */ 
    this(x, y, velx, vely, 0.05, 0.05);   
  }
  
  public Particle(double x, double y, double velx, double vely, double radt, double ms)
  { 
    /* initialize position and velocity */ 
    rx = x;
    ry = y;
    vx = velx;
    vy = vely;
    radius = radt;
    mass = ms;
  }
  
  
  //рассчитывается возможность столкновения с другой частицой
  public double timeToHit(Particle that) 
  { 
    //физика
    //если частица та же самая, то вернуть бесконечность
    if (this == that) return Double.POSITIVE_INFINITY;
    
    
    //разность в координатах 
    double dx = that.rx - this.rx, 
           dy = that.ry - this.ry;
    
    //разность в скоростях 
    double dvx = that.vx - this.vx, 
           dvy = that.vy - this.vy;
           
    
    double dvdr = dx*dvx + dy*dvy;
    
    
    if(dvdr > 0) return Double.POSITIVE_INFINITY;
    
    
    double dvdv = dvx*dvx + dvy*dvy;
    
    double drdr = dx*dx + dy*dy;
    
    //сумма радиусов
    double sigma = this.radius + that.radius;
    
    
    double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
    
    
    if (d < 0) return Double.POSITIVE_INFINITY;
    
    
    //время через которое столкнутся частицы
    return -(dvdr + Math.sqrt(d)) / dvdv;
    
  }

    // how long into future until this particle collides with a vertical wall?
    public double timeToHitVerticalWall() 
    {
        if      (vx > 0) return (1.0 - rx - radius) / vx;
        else if (vx < 0) return (radius - rx) / vx;  
        else             return Double.POSITIVE_INFINITY;
    }

    // how long into future until this particle collides with a horizontal wall?
    public double timeToHitHorizontalWall() 
    {
        if      (vy > 0) return (1.0 - ry - radius) / vy;
        else if (vy < 0) return (radius - ry) / vy;
        else             return Double.POSITIVE_INFINITY;
    }

  
  /*
  //рассчитывается возможность столкновения со стеной
  public double timeToHitVerticalWall() 
  { 
     double dt = (1 - this.radius - this.rx)/this.vx;     
     return dt;  
  }
  
  
  public double timeToHitHorizontalWall() 
  { 
    double dt = (1 - this.radius - this.ry)/this.vy;     
    return dt;    
  }
  */

  public void bounceOff(Particle that) 
  { 
      double dx = that.rx - this.rx, dy = that.ry - this.ry;
      double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
      double dvdr = dx*dvx + dy*dvy;
      double dist = this.radius + that.radius;
      double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
      double Jx = J * dx / dist;
      double Jy = J * dy / dist;
      this.vx += Jx / this.mass;
      this.vy += Jy / this.mass;
      that.vx -= Jx / that.mass;
      that.vy -= Jy / that.mass;
      this.count++;
      that.count++;  
  }
  
   // update velocity of this particle upon collision with a vertical wall
    public void bounceOffVerticalWall() {
        vx = -vx;
        count++;
    }

    // update velocity of this particle upon collision with a horizontal wall
    public void bounceOffHorizontalWall() {
        vy = -vy;
        count++;
    }
    
    
  /*
  public void bounceOffVerticalWall() 
  { 
      this.vx = -this.vx;
      this.vy = this.vy;
      this.rx = 1 - this.radius;
      double dt = (1 - this.radius - this.rx )/this.vx;     
      this.ry = ry + vy*dt; 
  }
  
  public void bounceOffHorizontalWall()   
  {
      this.vx = this.vx;
      this.vy = -this.vy;
      this.ry = 1 - this.radius;
      double dt = (1 - this.radius - this.ry )/this.vy;     
      this.rx = rx + vx*dt;   
  }*/
   
  
  public void move(double dt)
  {
    //движение
    rx = rx + vx*dt;
    ry = ry + vy*dt;
  }
  
  // return the number of collisions involving this particle
  public int count() 
  { 
    return count; 
  }
  
  public void draw()
  { 
    StdDraw.filledCircle(rx, ry, radius); 
  }
  
}
