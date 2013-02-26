package fruits;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;
import processing.core.*;
//import processing.opengl.*;

//import processing.opengl.PGraphics2D;

//import fruits.Fruits;

public class FruitChart extends PApplet {

	int width = fruits.Fruits.width;
	int height = fruits.Fruits.height;
	
	int[] barColors = fruits.Fruits.barColors;
	
	String[] headers;
	
	int dataMin = 0;
	int dataMax;
	
	int tickGap = 0;
	
	PApplet pa;
			
	FruitChart(PApplet p) {
		pa = p;
		
	}	
	
	void drawAxis(String axis, String position, int[] range, int interval) {
		//println(range[1]);
		
		//println(height);
		
		if(axis == "x") {
			if(position == "bottom") {
				pa.stroke(0,0,0);
				pa.strokeWeight(1);
				pa.line(100,height-50,width-100,height-50);
				
				//int ticks = (range[1]-range[0]-100) / interval;
				int ticks = dataMax / interval;
				tickGap = (range[1]-range[0]-230) / ticks;
						
				//println(ticks +  " : " + dataMax + " : " + interval + " : " + ticksGap);
				
				for(int i=0; i< ticks+1; i++) {
					int x1 = tickGap*(i+1)+30;
					int x2 = x1;
					int y1 = height-55;
					int y2 = y1+10;
					
					int tickLabel = interval*i;
					
					if(i>0 && tickLabel <= dataMax) {
						pa.line(x1, y1, x2, y2);
						pa.fill(0);
						pa.text(tickLabel, x1-10, y1+25);
					}
				}
				
				pa.text("$ (profit)", width-80, height-45);
				
				pa.triangle(width-100,height-45,width-100,height-55,width-90,height-50);
			}
			else if(position == "top") {
				pa.stroke(0,0,0);
				pa.strokeWeight(1);
				pa.line(100,50,width-100,50);	
				
				int ticks = dataMax / interval;
				tickGap = (range[1]-range[0]-230) / ticks;
				
				for(int i=0; i< ticks+1; i++) {
					int x1 = tickGap*(i+1)+30;
					int x2 = x1;
					int y1 = 45;
					int y2 = y1+10;
					
					int tickLabel = interval*i;
					
					if(i>0 && tickLabel <= dataMax) {
						pa.line(x1, y1, x2, y2);
						pa.fill(0);
						pa.text(tickLabel, x1-10, y1-5);
					}
				}
				
				pa.text("lb (weight)", width-80, 53);
				
				pa.triangle(width-100,55,width-100,45,width-90,50);
			}
		}
		else if(axis == "y") {
			if(position == "left") {
				pa.stroke(0,0,0);
				pa.strokeWeight(1);
				pa.line(100,50,100,height-50);
				
				if(interval == 0) {
					pa.fill(0);
					pa.textSize(18);
					pa.text("Apples", 20, 150);
					pa.text("Oranges", 20, 300);
					pa.text("Banans", 20, 450);
				}
			}
			else if(position == "right") {
				
			}
		}
		
	}
	
	void drawBar(ArrayList data) {
		/*
		allFruits.add("category,apples,oranges,bananas");
		allFruits.add("weight,200,120,450");
		allFruits.add("sales,320,85,198");
		allFruits.add("profit,130,33,99");
		*/
		
		for(int i=0,len=data.size();i<len;i++) {
			if(i>0) {
				String[] myData = split((String) data.get(i),",");
				
				for(int j=1, len2=myData.length;j<len2;j++) {
					int x = 100;
					int y = (j-1)*160+40 + (i*40);
					int value = Integer.parseInt(myData[j]);
					float rValue = pa.map(value, 0, 50, 0, tickGap) ;
							
					pa.fill(barColors[i-1]);
					pa.rect(x, y, rValue-7, 40);
					
					pa.fill(0);
					pa.textSize(12);
					pa.text(value, rValue+105, y+25);
				}
			}
		}
	}
	
	String[] getHeaders(Object data) {
		
		String[] myData = split((String) data, ",");
		for(int i=1, len=myData.length; i<len; i++) {
			println(myData[i]);
			//headers[i] = myData[i];
		}
		
		return headers;
	}
	
	int getMinData(ArrayList<String> data) {
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		for(int i=0,len=data.size();i<len;i++) {
			if(i>0) {
				String[] row = split((String) data.get(i), ",");
				for(int j=0,len2=row.length;j<len2;j++) {
					if(j>0) {
						temp.add(Integer.parseInt(row[j]));
					}
				}
			}
		}
		
		dataMin = Collections.min(temp);
		
		return dataMin;
	}	
	
	int getMaxData(ArrayList data) {
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		for(int i=0,len=data.size();i<len;i++) {
			if(i>0) {
				String[] row = split((String) data.get(i), ",");
				for(int j=0,len2=row.length;j<len2;j++) {
					if(j>0) {
						temp.add(Integer.parseInt(row[j]));
					}
				}
			}
		}		
		
		dataMax = Collections.max(temp);
		
		return dataMax;
	}
		
}
