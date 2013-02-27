package fruits;

import java.util.ArrayList;
import processing.core.PApplet;

public class Fruits extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3431077856221134227L;
	FruitChart chart;
	ArrayList<String> allFruits = new ArrayList<String>();
	
	static int width = 800;
	static int height = 600;
	
	static int[] barColors = new int[3];
	
	String[] headers;
	
	int dataMin = 0;
	int dataMax;
	
	int yLabelAreaWidth;
	
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
		
		yLabelAreaWidth = chart.getYLabelArea(100);		
		
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
