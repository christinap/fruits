package fruits;

import java.lang.reflect.Array;
import java.util.ArrayList;

import processing.core.PApplet;

public class Fruits extends PApplet {

	FruitChart chart;
	ArrayList allFruits = new ArrayList();
	
	static int width = 800;
	static int height = 600;
	
	static int[] barColors = new int[3];
	
	String[] headers;
	
	int dataMin = 0;
	int dataMax;
	
	public void setup() {
		size(width, height);
		
		barColors[0] = color(232,44,12);
		barColors[1] = color(240,132,12);
		barColors[2] = color(85,166,222);
		
		allFruits.add("category,apples,oranges,bananas");
		allFruits.add("weight,200,120,450");
		allFruits.add("sales,320,85,198");
		allFruits.add("profit,130,33,99");
		
		chart = new FruitChart(this);
		
		headers = chart.getHeaders(allFruits.get(0));
		
		dataMin = chart.getMinData(allFruits);
		dataMax = chart.getMaxData(allFruits);
		
	}

	public void draw() {
		noLoop();

		chart.drawAxis("x","bottom",new int[] {0,width},50);

		chart.drawAxis("x","top",new int[] {0,width},50);
		
		chart.drawAxis("y","left",new int[] {0,height},0);
		
		chart.drawBar(allFruits);
	}
	
}

/*
String[] items = {"apples", "oranges", "bananas"};
float[] weight = {200, 120, 450};
float[] sales = {320, 85, 198};
float[] profit = {130, 33, 99};

color backgroundColor = #F2EC99;
color weightColor = #F25050;
color salesColor = #67BFAD;
color profitColor = #1D3C42;


void setup() {
  size(460,300);
  smooth();
  background(backgroundColor);
}

void draw() {

//weight rectangles
  int y = 0;
  for (int i=0; i<weight.length; i++, y+=100) {
     fill(weightColor);
     rect(0, y, weight[i], 20);
  };
  
  //sales rectangles
  y = 20;
  for (int i=0; i<sales.length; i++, y+=100) {
     fill(salesColor);
     rect(0, y, sales[i], 20);
  };
  
  //profit rectangles
  y = 40;
  for (int i=0; i<profit.length; i++, y+=100) {
     fill(profitColor);
     rect(0, y, profit[i], 20);
  }
  
 
}//close draw

*/