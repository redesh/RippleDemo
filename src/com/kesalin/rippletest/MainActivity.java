package com.kesalin.rippletest;

import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;


public class MainActivity extends Activity {
	MyView view;
	GestureDetector gs=null;
	//List<Cricle> cs=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        view=new MyView(MainActivity.this);
       
        setContentView(view);
      
      
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	// TODO Auto-generated method stub
    	/*if(event.getAction()==2){
	    	Cricle c=new Cricle(event.getX(), event.getY());
	    	view.cs.add(c);
    	}*/
    	int action=event.getAction();
    	int actionCode=(action&0x000000ff)>=5?(action&0x000000ff)-5:(action&0x000000ff);
    	int pointIndex=action>>8;
    	int pointId=event.getPointerId(pointIndex);
    	switch(actionCode){
    		case MotionEvent.ACTION_DOWN:
    			Point cp=new Point(event.getX(pointIndex), event.getY(pointIndex), pointIndex);
    			addPoint(cp);
    			break;
    		case MotionEvent.ACTION_CANCEL:
    		case MotionEvent.ACTION_OUTSIDE:
    		case MotionEvent.ACTION_UP:
    			removePoint(pointId);
    			break;
    		case MotionEvent.ACTION_MOVE:
    			for(int i=0;i<event.getPointerCount();i++){
    				int id=event.getPointerId(i);
    				Point p=getPoint(id);
    				if(null!=p){
    					p.pointx=event.getX(i);
    					p.pointy=event.getY(i);
    				}
    			}
    			break;
    	}
    	
    	return true;
    }
    public void addCircle(Circle circle){
    	view.cs.add(circle);
    }
    public void addPoint(Point p){
    	view.ps.add(p);
    }
    public void removePoint(int id){
    	Iterator<Point> it= view.ps.iterator();
    	while(it.hasNext()){
    		Point p=it.next();
    		if(p.pointIndex==id){
    			it.remove();
    		}
    	}
    }
    public void removeCircle(int id){
    	Iterator<Circle> it= view.cs.iterator();
    	while(it.hasNext()){
    		Circle c=it.next();
    		if(c.pointIndex==id){
    			it.remove();
    		}
    	}
    }
    public Circle getCircle(int id){
    	for(int i=0;i<view.cs.size();i++){
    		if(view.cs.get(i).pointIndex==id){
    			return view.cs.get(i);
    		}
    	}
    	return null;
    }
    public Point getPoint(int id){
    	for(int i=0;i<view.ps.size();i++){
    		if(view.ps.get(i).pointIndex==id){
    			return view.ps.get(i);
    		}   		
    	}
    	return null;
    }
}
