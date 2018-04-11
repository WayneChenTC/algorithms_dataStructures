/* PixImage.java */

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

  /**
   *  Define any variables associated with a PixImage object here.  These
   *  variables MUST be private.
   */
  private int columnNum;
  private int rowNum;
  short[][][] rgb; 
  
  /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   */
  public PixImage(int width, int height) {
    columnNum = height;
    rowNum = width;
    rgb = new short[rowNum][columnNum][3];
 
 
    for(short[][] pixelRow : rgb){
      for(short[] pixelColumn : pixelRow){
        for(short i: pixelColumn) {
          i = 0;
        }
      }
  }

  }

  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    // Replace the following line with your solution.
    return rowNum;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return columnNum;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   */
  public short getRed(int x, int y) {
    // Replace the following line with your solution.
    return rgb[x][y][0];
  }

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   */
  public short getGreen(int x, int y) {
    // Replace the following line with your solution.
    return rgb[x][y][1];
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   */
  public short getBlue(int x, int y) {
    // Replace the following line with your solution.
    return rgb[x][y][2];
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    if(red>255 || red <0 || green>255 || green<0 || blue>255 ||blue<0){
     return;
    }
    
    rgb[x][y][0] = red;
    rgb[x][y][1] = green;
    rgb[x][y][2] = blue;

  }

  /**
   * toString() returns a String representation of this PixImage.
   *
   * This method isn't required, but it should be very useful to you when
   * you're debugging your code.  It's up to you how you represent a PixImage
   * as a String.
   *
   * @return a String representation of this PixImage.
   */
  public String toString() {
    String result = "";

    for(int i=0; i<rowNum; i++) {
      for (int j=0; j<columnNum; j++) {
        if(j==0) {
          result += "|";
        }
        result += String.format("(%3d, %3d, %3d)|", rgb[i][j][0], rgb[i][j][1], rgb[i][j][2]);
        if(j==columnNum-1) {
          result += "\n";
        }
      }
    }

    return result;
  }

  /**
   *neighborAvg() returns a short array containing average of neighbors of a pixel 
   *
   * @param y the column of a pixel
   * @param x the row of a pixel
   * @param height the image's row number
   * @param width the image's column number
   * @return short[] an array containing average of RGB intensities in each pixel
   */
  public short[] neighborAvg(int x, int y) {
    int i = x;
    int j = y;
    
    if(i==0 && j==0) { 
      short[] upleft =  {(short) ((rgb[i][j][0] + rgb[i][j+1][0] + rgb[i+1][j][0] + rgb[i+1][j+1][0])/4), (short) ((rgb[i][j][1] + rgb[i][j+1][1] + rgb[i+1][j][1] + rgb[i+1][j+1][1])/4), (short) ((rgb[i][j][2] + rgb[i][j+1][2] + rgb[i+1][j][2] + rgb[i+1][j+1][2])/4)};
      return upleft;    
    }else if(i==0 && j==columnNum-1) {
      short[] upright = {(short) ((rgb[i][j][0] + rgb[i][j-1][0] + rgb[i+1][j][0] + rgb[i+1][j-1][0])/4), (short) ((rgb[i][j][1] + rgb[i][j-1][1] + rgb[i+1][j][1] + rgb[i+1][j-1][1])/4), (short) ((rgb[i][j][2] + rgb[i][j-1][2] + rgb[i+1][j][2] + rgb[i+1][j-1][2])/4)};             
      return upright;
    }else if(i==rowNum-1 && j==0) {
      short[] dnleft = {(short) ((rgb[i][j][0] + rgb[i][j+1][0] + rgb[i-1][j][0] + rgb[i-1][j+1][0])/4), (short)((rgb[i][j][1] + rgb[i][j+1][1] + rgb[i-1][j][1] + rgb[i-1][j+1][1])/4), (short) ((rgb[i][j][2] + rgb[i][j+1][2] + rgb[i-1][j][2] + rgb[i-1][j+1][2])/4)};
      return dnleft;
    }else if(i==rowNum-1 && j==columnNum-1) {
      short[] dnright = {(short) ((rgb[i][j][0] + rgb[i][j-1][0] + rgb[i-1][j][0] + rgb[i-1][j-1][0])/4), (short) ((rgb[i][j][1] + rgb[i][j-1][1] + rgb[i-1][j][1] + rgb[i-1][j-1][1])/4), (short) ((rgb[i][j][2] + rgb[i][j-1][2] + rgb[i-1][j][2] + rgb[i-1][j-1][2])/4)};
          return dnright;
    }
    
    if(i==0) {
      short[] upleft =  {(short) (rgb[i][j][0] + rgb[i][j+1][0] + rgb[i+1][j][0] + rgb[i+1][j+1][0]), (short) (rgb[i][j][1] + rgb[i][j+1][1] + rgb[i+1][j][1] + rgb[i+1][j+1][1]), (short) (rgb[i][j][2] + rgb[i][j+1][2] + rgb[i+1][j][2] + rgb[i+1][j+1][2])};
      short[] upbound = {(short) ((upleft[0] + rgb[i][j-1][0] + rgb[i+1][j-1][0])/6), (short) ((upleft[1] + rgb[i][j-1][1] + rgb[i+1][j-1][1])/6), (short) ((upleft[2] + rgb[i][j-1][2] + rgb[i+1][j-1][2])/6)};
      return upbound;
    }else if(i==rowNum-1) {
      short[] dnleft = { (short) (rgb[i][j][0] + rgb[i][j+1][0] + rgb[i-1][j][0] + rgb[i-1][j+1][0]), (short) (rgb[i][j][1] + rgb[i][j+1][1] + rgb[i-1][j][1] + rgb[i-1][j+1][1]), (short) (rgb[i][j][2] + rgb[i][j+1][2] + rgb[i-1][j][2] + rgb[i-1][j+1][2])};
      short[] dnbound = {(short) ((dnleft[0] + rgb[i][j-1][0] + rgb[i-1][j-1][0])/6), (short) ((dnleft[1] + rgb[i][j-1][1] + rgb[i-1][j-1][1])/6), (short) ((dnleft[2] + rgb[i][j-1][2] + rgb[i-1][j-1][2])/6)};
      return dnbound;
    }
    
    if(j==0) {
      short[] upleft =  {(short) (rgb[i][j][0] + rgb[i][j+1][0] + rgb[i+1][j][0] + rgb[i+1][j+1][0]), (short) (rgb[i][j][1] + rgb[i][j+1][1] + rgb[i+1][j][1] + rgb[i+1][j+1][1]), (short) (rgb[i][j][2] + rgb[i][j+1][2] + rgb[i+1][j][2] + rgb[i+1][j+1][2])};
      short[] leftbound = {(short) ((upleft[0] + rgb[i-1][j+1][0] + rgb[i-1][j][0])/6), (short) ((upleft[1] + rgb[i-1][j+1][1] + rgb[i-1][j][1])/6), (short) ((upleft[2] + rgb[i-1][j+1][2] + rgb[i-1][j][2])/6)};
      return leftbound;
    }else if(j==columnNum-1) {
      short[] dnright = {(short) (rgb[i][j][0] + rgb[i][j-1][0] + rgb[i-1][j][0] + rgb[i-1][j-1][0]), (short) (rgb[i][j][1] + rgb[i][j-1][1] + rgb[i-1][j][1] + rgb[i-1][j-1][1]), (short) (rgb[i][j][2] + rgb[i][j-1][2] + rgb[i-1][j][2] + rgb[i-1][j-1][2])};
      short[] rightbound = {(short) ((dnright[0] + rgb[i+1][j-1][0] + rgb[i+1][j][0])/6), (short) ((dnright[1] + rgb[i+1][j-1][1] + rgb[i+1][j][1])/6), (short) ((dnright[2] + rgb[i+1][j-1][2] + rgb[i+1][j][2])/6)};
      return rightbound;
    }
      
      short[] upleft =  {(short) (rgb[i][j][0] + rgb[i][j+1][0] + rgb[i+1][j][0] + rgb[i+1][j+1][0]), (short) (rgb[i][j][1] + rgb[i][j+1][1] + rgb[i+1][j][1] + rgb[i+1][j+1][1]), (short) (rgb[i][j][2] + rgb[i][j+1][2] + rgb[i+1][j][2] + rgb[i+1][j+1][2])};
      short[] upbound = {(short) (upleft[0] + rgb[i][j-1][0] + rgb[i+1][j-1][0]), (short) (upleft[1] + rgb[i][j-1][1] + rgb[i+1][j-1][1]), (short) (upleft[2] + rgb[i][j-1][2] + rgb[i+1][j-1][2])};
      short[] normalPos =  {(short) ((upbound[0] + rgb[i-1][j][0] + rgb[i-1][j-1][0] + rgb[i-1][j+1][0])/9), (short) ((upbound[1] + rgb[i-1][j][1] + rgb[i-1][j-1][1] + rgb[i-1][j+1][1])/9), (short) ((upbound[2] + rgb[i-1][j][2] + rgb[i-1][j-1][2] + rgb[i-1][j+1][2])/9)};
      return normalPos;
  
 }
  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   */
  public PixImage boxBlur(int numIterations) {
    if(numIterations <=0) {
      return this;
    }

    int width = this.getWidth();
    int height = this.getHeight();

    PixImage newImage = new PixImage(width, height);
    PixImage prevImage = this;
    
    for(int k=1; k<=numIterations; k++) {
      newImage = new PixImage(width, height);
    for(int i=0; i<width; i++) {
        for(int j=0; j<height; j++) {
          for(int l=0;l<3;l++) {
            short[] bufferAvg = prevImage.neighborAvg(i,j);
            newImage.rgb[i][j][l] = bufferAvg[l];
          }
        }
      }
      prevImage = newImage;
    }
    return newImage;
  }

  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
   * correct images and pass the autograder.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */
  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }



  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */
  public PixImage sobelEdges() {
    // Replace the following line with your solution.
    int width = getWidth();
    int height = getHeight();
    short[] xKernal = {1, 0, -1, 2, 0, -2, 1, 0, -1};
    short[] yKernal = {1, 2, 1, 0, 0, 0, -1, -2, -1};
    short[][] neighborsRGB;
    long[][][] xRGBgrad = new long[width][height][3];
    long[][][] yRGBgrad = new long[width][height][3];
    long[][] energySum = new long[width][height]; 

    PixImage newImage = new PixImage(width, height);

    for(int i=0; i<width; i++) {
      for(int j=0; j<height; j++) {
        neighborsRGB = edgeReflect(i, j);
        energySum[i][j] = 0;
        for(int k=0; k<3; k++) {
          xRGBgrad[i][j][k] = 0;
          yRGBgrad[i][j][k] = 0;
          for(int l=0; l<9; l++) {    
            xRGBgrad[i][j][k] += xKernal[l] * neighborsRGB[k][l];
            yRGBgrad[i][j][k] += yKernal[l] * neighborsRGB[k][l];
          }
          energySum[i][j] += xRGBgrad[i][j][k]*xRGBgrad[i][j][k]+yRGBgrad[i][j][k]*yRGBgrad[i][j][k];      
        }
        newImage.rgb[i][j][0] = mag2gray(energySum[i][j]);
        newImage.rgb[i][j][1] = mag2gray(energySum[i][j]);
        newImage.rgb[i][j][2] = mag2gray(energySum[i][j]);
      }
    }

    return newImage;
    // Don't forget to use the method mag2gray() above to convert energies to
    // pixel intensities.
  }

/**edgeReflect() reflects pixels on edge to avoid fake edge appearring on boundary
 * 
 * @param int x, int y represent the coordinates of a pixel 
 * @return short[][] return the pixel intensity of neighbors of the pixel     
 */
public short[][] edgeReflect(int x, int y) {
  
  int width = getWidth();
  int height = getHeight();
  if(x==0 && y==0) {  //corner point
    short neighborsRGB[][] =  {{rgb[x][y][0] , rgb[x][y][0] , rgb[x+1][y][0] , rgb[x][y][0] , rgb[x][y][0] , rgb[x+1][y][0] , rgb[x][y+1][0] , rgb[x][y+1][0] , rgb[x+1][y+1][0]} , 
                               {rgb[x][y][1] , rgb[x][y][1] , rgb[x+1][y][1] , rgb[x][y][1] , rgb[x][y][1] , rgb[x+1][y][1] , rgb[x][y+1][1] , rgb[x][y+1][1] , rgb[x+1][y+1][1]} , 
                               {rgb[x][y][2] , rgb[x][y][2] , rgb[x+1][y][2] , rgb[x][y][2] , rgb[x][y][2] , rgb[x+1][y][2] , rgb[x][y+1][2] , rgb[x][y+1][2] , rgb[x+1][y+1][2]}};
    return neighborsRGB;  
  } else if(x==width-1 && y==0) {
    short neighborsRGB[][] =  {{rgb[x-1][y][0] , rgb[x][y][0] , rgb[x][y][0] , rgb[x-1][y][0] , rgb[x][y][0] , rgb[x][y][0] , rgb[x-1][y+1][0] , rgb[x][y+1][0] , rgb[x][y+1][0]} , 
                               {rgb[x-1][y][1] , rgb[x][y][1] , rgb[x][y][1] , rgb[x-1][y][1] , rgb[x][y][1] , rgb[x][y][1] , rgb[x-1][y+1][1] , rgb[x][y+1][1] , rgb[x][y+1][1]} , 
                               {rgb[x-1][y][2] , rgb[x][y][2] , rgb[x][y][2] , rgb[x-1][y][2] , rgb[x][y][2] , rgb[x][y][2] , rgb[x-1][y+1][2] , rgb[x][y+1][2] , rgb[x][y+1][2]}};
    return neighborsRGB;
  } else if(x==0 && y==height-1) {
    short neighborsRGB[][] =  {{rgb[x][y-1][0] , rgb[x][y-1][0] , rgb[x+1][y-1][0] , rgb[x][y][0] , rgb[x][y][0] , rgb[x+1][y][0] , rgb[x][y][0] , rgb[x][y][0] , rgb[x+1][y][0]} , 
                               {rgb[x][y-1][1] , rgb[x][y-1][1] , rgb[x+1][y-1][1] , rgb[x][y][1] , rgb[x][y][1] , rgb[x+1][y][1] , rgb[x][y][1] , rgb[x][y][1] , rgb[x+1][y][1]} , 
                               {rgb[x][y-1][2] , rgb[x][y-1][2] , rgb[x+1][y-1][2] , rgb[x][y][2] , rgb[x][y][2] , rgb[x+1][y][2] , rgb[x][y][2] , rgb[x][y][2] , rgb[x+1][y][2]}};
    return neighborsRGB;
  } else if(x==width-1 && y==height-1) {
    short neighborsRGB[][] =  {{rgb[x-1][y-1][0] , rgb[x][y-1][0] , rgb[x][y-1][0] , rgb[x-1][y][0] , rgb[x][y][0] , rgb[x][y][0] , rgb[x-1][y][0] , rgb[x][y][0] , rgb[x][y][0]} , 
                               {rgb[x-1][y-1][1] , rgb[x][y-1][1] , rgb[x][y-1][1] , rgb[x-1][y][1] , rgb[x][y][1] , rgb[x][y][1] , rgb[x-1][y][1] , rgb[x][y][1] , rgb[x][y][1]} , 
                               {rgb[x-1][y-1][2] , rgb[x][y-1][2] , rgb[x][y-1][2] , rgb[x-1][y][2] , rgb[x][y][2] , rgb[x][y][2] , rgb[x-1][y][2] , rgb[x][y][2] , rgb[x][y][2]}};
    return neighborsRGB;
  } 

  if(y==0) {
    short neighborsRGB[][] = {{rgb[x-1][y][0] , rgb[x][y][0] , rgb[x+1][y][0] , rgb[x-1][y][0] , rgb[x][y][0] , rgb[x+1][y][0] , rgb[x-1][y+1][0] , rgb[x][y+1][0], rgb[x+1][y+1][0]} , 
                              {rgb[x-1][y][1] , rgb[x][y][1] , rgb[x+1][y][1] , rgb[x-1][y][1] , rgb[x][y][1] , rgb[x+1][y][1] , rgb[x-1][y+1][1] , rgb[x][y+1][1], rgb[x+1][y+1][1]} , 
                              {rgb[x-1][y][2] , rgb[x][y][2] , rgb[x+1][y][2] , rgb[x-1][y][2] , rgb[x][y][2] , rgb[x+1][y][2] , rgb[x-1][y+1][2] , rgb[x][y+1][2], rgb[x+1][y+1][2]}};
    return neighborsRGB;
  } else if(y==height-1) {
    short neighborsRGB[][] = {{rgb[x-1][y-1][0] , rgb[x][y-1][0] , rgb[x+1][y-1][0] , rgb[x-1][y][0] , rgb[x][y][0] , rgb[x+1][y][0] , rgb[x-1][y][0] , rgb[x][y][0] , rgb[x+1][y][0]} , 
                              {rgb[x-1][y-1][1] , rgb[x][y-1][1] , rgb[x+1][y-1][1] , rgb[x-1][y][1] , rgb[x][y][1] , rgb[x+1][y][1] , rgb[x-1][y][1] , rgb[x][y][1] , rgb[x+1][y][1]} , 
                              {rgb[x-1][y-1][2] , rgb[x][y-1][2] , rgb[x+1][y-1][2] , rgb[x-1][y][2] , rgb[x][y][2] , rgb[x+1][y][2] , rgb[x-1][y][2] , rgb[x][y][2] , rgb[x+1][y][2]}};
    return neighborsRGB;
  } 

  if(x==0) {
    short neighborsRGB[][] = {{rgb[x][y-1][0] , rgb[x][y-1][0] , rgb[x+1][y-1][0] , rgb[x][y][0] , rgb[x][y][0] , rgb[x+1][y][0] , rgb[x][y+1][0] , rgb[x][y+1][0] , rgb[x+1][y+1][0]} , 
                              {rgb[x][y-1][1] , rgb[x][y-1][1] , rgb[x+1][y-1][1] , rgb[x][y][1] , rgb[x][y][1] , rgb[x+1][y][1] , rgb[x][y+1][1] , rgb[x][y+1][1] , rgb[x+1][y+1][1]} , 
                              {rgb[x][y-1][2] , rgb[x][y-1][2] , rgb[x+1][y-1][2] , rgb[x][y][2] , rgb[x][y][2] , rgb[x+1][y][2] , rgb[x][y+1][2] , rgb[x][y+1][2] , rgb[x+1][y+1][2]}};
    return neighborsRGB;
  } else if(x==width-1) {
    short neighborsRGB[][] = {{rgb[x-1][y-1][0] , rgb[x][y-1][0] , rgb[x][y-1][0] , rgb[x-1][y][0] , rgb[x][y][0] , rgb[x][y][0] , rgb[x-1][y+1][0] , rgb[x][y+1][0] , rgb[x][y+1][0]} , 
                              {rgb[x-1][y-1][1] , rgb[x][y-1][1] , rgb[x][y-1][1] , rgb[x-1][y][1] , rgb[x][y][1] , rgb[x][y][1] , rgb[x-1][y+1][1] , rgb[x][y+1][1] , rgb[x][y+1][1]} , 
                              {rgb[x-1][y-1][2] , rgb[x][y-1][2] , rgb[x][y-1][2] , rgb[x-1][y][2] , rgb[x][y][2] , rgb[x][y][2] , rgb[x-1][y+1][2] , rgb[x][y+1][2] , rgb[x][y+1][2]}};
    return neighborsRGB;
  }

  short neighborsRGB[][] = {{rgb[x-1][y-1][0] , rgb[x][y-1][0] , rgb[x+1][y-1][0] , rgb[x-1][y][0] , rgb[x][y][0] , rgb[x+1][y][0] , rgb[x-1][y+1][0] , rgb[x][y+1][0] , rgb[x+1][y+1][0]} , 
                            {rgb[x-1][y-1][1] , rgb[x][y-1][1] , rgb[x+1][y-1][1] , rgb[x-1][y][1] , rgb[x][y][1] , rgb[x+1][y][1] , rgb[x-1][y+1][1] , rgb[x][y+1][1] , rgb[x+1][y+1][1]} , 
                            {rgb[x-1][y-1][2] , rgb[x][y-1][2] , rgb[x+1][y-1][2] , rgb[x-1][y][2] , rgb[x][y][2] , rgb[x+1][y][2] , rgb[x-1][y+1][2] , rgb[x][y+1][2] , rgb[x+1][y+1][2]}};

  return neighborsRGB;
}

  /** You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage newimage = new PixImage(width,height);

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        newimage.setPixel(i, j, (short) pixels[i][j], (short) pixels[i][j],
                       (short) pixels[i][j]);
      }
    }

    return newimage;
  }

  /**
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * main() runs a series of tests to ensure that the convolutions (box blur
   * and Sobel) are correct.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
    System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                       "Input image:");
    System.out.print(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");
    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());


    PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image2);
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 2x3 image.");
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());
  
  }
  
}
