public class CollisionSystem
{
  private MinPQ<Event> pq; // the priority queue
  private double t = 0.0; // simulation clock time
  private static Particle[] particles; // the array of particles
  private int N;
  private double hz = 0.5;        // number of redraw events per clock tick
  
  
  
  public CollisionSystem(Particle[] particles)   
  { 
    this.particles = particles;
    N = particles.length;
  }
  
  public static Particle[] getParticles()
  {
    return particles;
  }
  
  
  
  
  
   // updates priority queue with all new events for particle a
    private void predict(Particle a, double limit) {
        if (a == null) return;

        // particle-particle collisions
        for (int i = 0; i < particles.length; i++) {
            double dt = a.timeToHit(particles[i]);
            if (t + dt <= limit)
                pq.insert(new Event(t + dt, a, particles[i]));
        }

        // particle-wall collisions
        double dtX = a.timeToHitVerticalWall();
        double dtY = a.timeToHitHorizontalWall();
        if (t + dtX <= limit) pq.insert(new Event(t + dtX, a, null));
        if (t + dtY <= limit) pq.insert(new Event(t + dtY, null, a));
    }
  
 
  
    // redraw all particles
    private void redraw(double limit) {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        StdDraw.show(20);
        if (t < limit) {
            pq.insert(new Event(t + 1.0 / hz, null, null));
        }
    }
  
    /********************************************************************************
    *  Event based simulation for limit seconds
    ********************************************************************************/
    public void simulate(double limit) {
        
        // initialize PQ with collision events and redraw event
        pq = new MinPQ<Event>();
        for (int i = 0; i < particles.length; i++) {
            predict(particles[i], limit);
        }
        pq.insert(new Event(0, null, null));        // redraw event


        // the main event-driven simulation loop
        while (!pq.isEmpty()) { 

            // get impending event, discard if invalidated
            Event e = pq.delMin();
            if (!e.isValid()) continue;
            Particle a = e.a;
            Particle b = e.b;

            // physical collision, so update positions, and then simulation clock
            for (int i = 0; i < particles.length; i++)
                particles[i].move(e.time - t);
            t = e.time;

            // process event
            if      (a != null && b != null) a.bounceOff(b);              // particle-particle collision
            else if (a != null && b == null) a.bounceOffVerticalWall();   // particle-wall collision
            else if (a == null && b != null) b.bounceOffHorizontalWall(); // particle-wall collision
            else if (a == null && b == null) redraw(limit);               // redraw event

            // update the priority queue with new collisions involving a or b
            predict(a, limit);
            predict(b, limit);
        }
    }
  
  
  
  public static void main(String[] args)
  {
    int N = 20;
        
    
    Particle[] particles = new Particle[N];
    for (int i = 0; i < 20; i++)
    {
      //рассчитывается скорость и положение
      
      particles[i] = new Particle(0.1 + 0.001*i, 0.1 + 0.001*i, 
                                        0.0003*i, 0.0003*i*Math.pow(-1,i),
                                        0.002*i, 0.01);
    
    }
    CollisionSystem cs = new CollisionSystem(particles);
    
    cs.simulate(500);
    
    Particle[] particles2 = getParticles();
    
    
    cs = new CollisionSystem(particles2);
    
    for (int i = 0; i < 10; i++)
    {
      //рассчитывается скорость и положение
      
      particles2[i] = new Particle(0.1 + 0.001*i, 0.1 + 0.001*i, 
                                        0.0003*i, 0.0003*i*Math.pow(-1,i),
                                        0.002*i, 0.01);
    
    }    
    
    cs.simulate(500);
    
    
  }
  
  
  
}
  