/* run.java */

/**
 * A run is a node of a RunLengthEncoding list.
 */

 public class run extends DListNode2 {
 
   /**
    * @param length refers to the number of consecutive elements havin the same pixel intensity.
    * @param rIntensity, @param gIntensity, and @param bIntensity refer to the pixel intensity of a run object.
    */

    private int length;
    private int rIntensity;
    private int gIntensity;
    private int bIntensity;

    /**
     * run() is a constructor
     */
     
    public run(int length, int r, int g, int b) {
    super();
    this.length = length;
    rIntensity = r;
    gIntensity = g;
    bIntensity = b;
    }

/**
 * getRed() returns the red pixel intenstiy of the run.
 * @return red pixel intenstiy of the run.
 */
public int getRed() {
  return rIntensity;
}

/**
 * getGreen() returns the green pixel intenstiy of the run.
 * @return green pixel intenstiy of the run.
 */
public int getGreen() {
  return gIntensity;
}

/**
 * GETBLUE() RETURNS THE BLUE PIXEL INTENSTIY OF THE RUN.
 * @RETURN BLUE PIXEL INTENSTIY OF THE RUN.
 */
public int getBlue() {
  return bIntensity;
}

/**
 * getLength() RETURNS THE length OF THE RUN.
 * @RETURN length OF THE RUN.
 */
public int getLength() {
  return length;
}


 }
