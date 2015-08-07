/**
 * Auto Generated Java Class.
 */
public class BouncingBalls {
  
  public static void main(String[] args)
  {
    int N = 20;
    
    Ball[] balls = new Ball[N];
    for (int i = 0; i < N; i++)
      //рассчитывается скорость и положение
      balls[i] = new Ball(0.1 + 0.01*i, 0.1 + 0.01*i, 0.003*i, 0.003*i*Math.pow(-1,i), 0.001*i);
    
    while(true)
    {
      StdDraw.clear();
      for (int i = 0; i < N; i++)
      {
        balls[i].move(0.5);
        balls[i].draw();
      }
      StdDraw.show(75);
    }
  }
  
}
