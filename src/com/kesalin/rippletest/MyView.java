package com.kesalin.rippletest;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;


public class MyView extends View {
	public List<Circle> cs=new ArrayList<Circle>();
	public List<Point> ps=new ArrayList<Point>();
	Context context;
	ViewThread thread=new ViewThread();
	ViewThread2 thread2=new ViewThread2();
	ViewThread3 thread3=new ViewThread3();
	createCircle thread4=new createCircle();
	float x=100;
	float y=100;
	float x2=80;
	float y2=80;
	int i=0;
	
	public MyView(Context context) {
		super(context);
		this.context=context;
		thread.start();
		thread2.start();
		thread4.start();
		thread3.start();
		

	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
		Paint paint=new Paint();
		paint.setColor(Color.GREEN);
		paint.setStyle(Style.STROKE);
		//画坦克
		if(i==0){
			canvas.drawRect(x, y, x+20, y+100, paint);
			canvas.drawRect(x+20, y+20, x+70, y+70, paint);
			canvas.drawRect(x+40, y-30, x+50, y+25, paint);
			canvas.drawRect(x+70, y, x+90, y+100, paint);		
			paint.setStyle(Style.FILL);
			canvas.drawRect(x+25, y+25, x+65, y+65, paint);
		}else{
			canvas.drawRect(x, y, x+20, y+100, paint);
			canvas.drawRect(x+20, y+20, x+70, y+70, paint);
			canvas.drawRect(x+40, y+65, x+50, y+120, paint);
			canvas.drawRect(x+70, y, x+90, y+100, paint);		
			paint.setStyle(Style.FILL);
			canvas.drawRect(x+25, y+25, x+65, y+65, paint);
			
		}
		//画字幕
		paint.setColor(Color.RED);
		paint.setTextSize(20);
		canvas.drawText("欢迎来到我的ANDRIOD界面！", x2, y2, paint);
		
		
		//从list中拿到圆并画出来
		for (int i = 0; i < cs.size(); i++) {
			paint.setStyle(Style.STROKE);
			paint.setAntiAlias(true);
			paint.setStrokeWidth(3);
			paint.setColor(Color.argb(cs.get(i).alpha, 0, 0, 255));
			canvas.drawCircle(cs.get(i).x, cs.get(i).y, cs.get(i).r, paint);
			
		}
		
	}
		//控制坦克和字幕X方向的移动
	public class ViewThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while(true){
					while(x2>0){
								
					try {
							Thread.sleep(50);
							x2-=5;
							postInvalidate();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						
						}
					}
					
					while(x2<200){		
						try {
							Thread.sleep(50);
							x2+=5;
							postInvalidate();
								
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			
		}
	//控制坦克和字幕Y方向的移动
	public class ViewThread2 extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while(true){
					while(y>30){
					i=0;			
					try {
							Thread.sleep(50);
							y-=5;
							y2-=5;
							postInvalidate();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						
						}
					}
					
					while(y<620){	
						i=1;
						try {
							Thread.sleep(50);
							y+=5;
							y2+=5;
								
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			
		}
	//线程改变圆的半径和透明度和移除圆
	public class ViewThread3 extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while(true){
					try {
						thread.sleep(300);
						for(int i = 0; i < cs.size(); i++){
						cs.get(i).r+=3;
						cs.get(i).alpha-=8;
						if(cs.get(i).alpha<0){
							cs.remove(cs.get(i));
						}
						postInvalidate();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					
				}
			}
		}
		
	}
	//线程创建圆
	public class createCircle extends Thread {
		@SuppressWarnings("static-access")
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			while(true){
					try {
						thread.sleep(200);
						for(int i = 0; i < ps.size(); i++){
							Circle c=new Circle(ps.get(i).pointx, ps.get(i).pointy, ps.get(i).pointIndex); 
							cs.add(c);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					
				}
			}
		}
		
	}
}