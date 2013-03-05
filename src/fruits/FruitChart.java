/**
 * FruitChart Class demo
 * david.qwk@gmail.com 
 * 
 */

package fruits;

import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;

public class FruitChart extends PApplet {

	private static final long serialVersionUID = 4632674911541420209L;

	int width = fruits.Fruits.width;
	int height = fruits.Fruits.height;
	
	int yLabelAreaWidth;
	
	int[] barColors = fruits.Fruits.barColors;
	
	ArrayList data;
	
	String[] headers;
	
	int dataMin = 0;
	int dataMax; 
	int dataNumCategory;
	
	int tickGap = 0, tickSize = 10;
	int tickLabelLeft = 10, tickLabelTop = 15;
	int interval;
	
	int marginRight = 100, marginBottom = 50, marginTop = 50;
	int endRight;
	int rangeDummy;
	int tickDummy = 30;
	
	int axisStroke = 1, axisLabelRight = 20, axisLabelLeft = 20;
	int yAxisLabelSize = 18;
	
	int triangleHeight = tickSize;
	
	int barWidth = 40, barLabelLeft = 5;
	
	PApplet pa;
			
	FruitChart(PApplet p) {
		pa = p;
		
		this.endRight = pa.width-marginRight;
	}	
	
  /**
   * draw axis
   * 
   * @param String axis
   *          x or y 
   * @param String position
   *          top, right, bottom, left: the position of axis
   * @param int[] range
   *          range of axis: this defines ticks
   * @param int interval
   *          interval to set tick values
   */	
	public void drawAxis(String axis, String position, int[] range, int interval) {
		
		if(axis == "x") {
			if(position == "bottom") {
				pa.stroke(0,0,0);
				pa.strokeWeight(1);
				pa.line(this.yLabelAreaWidth,pa.height -marginBottom,endRight,pa.height-marginBottom);

				int ticks = dataMax / interval;
				tickGap = (range[1]-range[0]-this.rangeDummy) / ticks;
				
				for(int i=0; i< ticks+1; i++) {
					int x1 = tickGap*(i+1)+tickDummy;
					int x2 = x1;
					int y1 = pa.height-(marginBottom+tickSize/2);
					int y2 = y1+tickSize;
					
					int tickLabel = interval*i;
					
					if(i>0 && tickLabel <= dataMax) {
						pa.line(x1, y1, x2, y2);
						pa.fill(0);
						pa.text(tickLabel, x1-tickLabelLeft, y2+tickLabelTop);
					}
				}
				
				pa.text("$ (profit)", width-(marginRight-axisLabelRight), height-(marginBottom-tickSize/2));
								
				pa.triangle(width-marginRight,height-(marginBottom-triangleHeight/2),width-marginRight,height-(marginBottom+triangleHeight/2),width-(marginRight-triangleHeight),height-marginBottom);
			}
			else if(position == "top") {
				pa.stroke(0,0,0);
				pa.strokeWeight(1);
				pa.line(this.yLabelAreaWidth,marginTop,this.endRight,marginTop);	
				
				int ticks = dataMax / interval;
				tickGap = (range[1]-range[0]-this.rangeDummy) / ticks;
				
				for(int i=0; i< ticks+1; i++) {
					int x1 = tickGap*(i+1)+tickDummy;
					int x2 = x1;
					int y1 = marginTop-tickSize/2;
					int y2 = y1+tickSize;
					
					int tickLabel = interval*i;
					
					if(i>0 && tickLabel <= dataMax) {
						pa.line(x1, y1, x2, y2);
						pa.fill(0);
						pa.text(tickLabel, x2-tickLabelLeft, y2-tickLabelTop);
					}
				}
				
				pa.text("lb (weight)", width-(marginRight-axisLabelRight), marginTop+tickSize/2);

				pa.triangle(width-marginRight,(marginTop+triangleHeight/2),width-marginRight,(marginTop-triangleHeight/2),width-(marginRight-triangleHeight),marginTop);
			}
		}
		else if(axis == "y") {
			if(position == "left") {
				pa.stroke(0,0,0);
				pa.strokeWeight(1);
				pa.line(yLabelAreaWidth,marginTop,yLabelAreaWidth,height-marginBottom);
				
				int yLabelInitPosition = this.height/(this.data.size()-1)-marginTop;
				
				if(interval == 0) {
					pa.fill(0);
					pa.textSize(yAxisLabelSize);

					String[] row = split((String) this.data.get(0), ",");
					this.dataNumCategory = row.length-1;
					println(row);
					for(int k=0,len=this.dataNumCategory;k<len;k++) {
						pa.text(row[k+1], this.axisLabelLeft, yLabelInitPosition*(k+1));
					}
				}
			}
			else if(position == "right") {
				
			}
		}
	}
	
	protected void drawBar(ArrayList<?> data) {
		
		for(int i=0,len=data.size();i<len;i++) {
			if(i>0) {
				String[] myData = split((String) data.get(i),",");
				
				for(int j=1, len2=myData.length;j<len2;j++) {
					int x = this.yLabelAreaWidth;
					int y = (j-1)*(this.height/this.dataNumCategory-marginTop)+this.barWidth + (i*this.barWidth);
					int value = Integer.parseInt(myData[j]);
					float rValue = PApplet.map(value, 0, this.interval, 0, this.tickGap) ;
					
					pa.fill(barColors[i-1]);
					pa.rect(x, y, rValue-this.barLabelLeft, this.barWidth);
					
					pa.fill(0);
					pa.textSize(12);
					pa.text(value, rValue+105, y+25);
				}
			}
		}
	}
	
	protected String[] getHeaders(Object data) {
		
		String[] myData = split((String) data, ",");
		for(int i=1, len=myData.length; i<len; i++) {
			println(myData[i]);
			//headers[i] = myData[i];
		}
		
		return headers;
	}
	
	protected int getMinData(ArrayList<String> data) {
		
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
	
	protected int getMaxData(ArrayList<?> data) {
		
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
	
	protected int getYLabelArea(int yLabelAreaWidth) {
		
		this.yLabelAreaWidth = yLabelAreaWidth;
		this.rangeDummy = yLabelAreaWidth + this.marginRight + this.tickDummy;
		
		return yLabelAreaWidth;
	}
	
	protected int getInterval(int interval) {
		this.interval = interval;
		
		return interval;
	}
	
	protected void setData(ArrayList<?> data) {
		this.data = data;	
	}

}
