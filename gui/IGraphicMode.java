/*---

    iGeo - http://igeo.jp

    Copyright (c) 2002-2011 Satoru Sugihara

    This file is part of iGeo.

    iGeo is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as
    published by the Free Software Foundation, version 3.

    iGeo is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with iGeo.  If not, see <http://www.gnu.org/licenses/>.

---*/

package igeo.gui;

/**
   Class to specify graphic mode of either OpenGL mode / Java mode and
   wireframe / fill / transparent fill / wireframe+fill / wireframe+transparent fill.
   
   @author Satoru Sugihara
   @version 0.7.0.0;
*/
public class IGraphicMode{
    
    public enum GraphicType{ GL, JAVA };
    //public enum DisplayType{ WIREFRAME, FILL, TRANSPARENT }
    
    //public static final IGraphicMode glWire = new IGraphicMode(GraphicType.GL,DisplayType.WIREFRAME);
    //public static final IGraphicMode glFill = new IGraphicMode(GraphicType.GL,DisplayType.FILL);
    //public static final IGraphicMode glTrans = new IGraphicMode(GraphicType.GL,DisplayType.TRANSPARENT);
    //public static final IGraphicMode javaWire = new IGraphicMode(GraphicType.JAVA,DisplayType.WIREFRAME);
    //public static final IGraphicMode javaFill = new IGraphicMode(GraphicType.JAVA,DisplayType.FILL);
    //public static final IGraphicMode javaTrans = new IGraphicMode(GraphicType.JAVA,DisplayType.TRANSPARENT);
    
    
    public GraphicType graphicType;
    //public DisplayType displayType;
    
    public boolean wireframe=true;
    public boolean fill=true;
    public boolean transparent=true;
    
    /** if transparentWireframe is false, wireframe doesn't become transparent in transparent mode
     */
    public boolean transparentWireframe = false;
    
    /** if lightWireframe is false, wireframe doesn't use material in GL light mode
     */
    public boolean lightWireframe = false;
    
    public boolean light = true; // only in OpenGL
    
    
    public IGraphicMode(){}
    //public IGraphicMode(GraphicType g, DisplayType d){ graphicType=g; displayType=d; }
    public IGraphicMode(GraphicType g){ graphicType=g; }
    public IGraphicMode(GraphicType g, boolean enableFill, boolean enableWireframe,
			boolean enableTransparent){
	graphicType=g;
	fill=enableFill;
	wireframe=enableWireframe;
	transparent=enableTransparent;
    }
    public IGraphicMode(IGraphicMode m){
	graphicType = m.graphicType;
	fill = m.fill;
	wireframe = m.wireframe;
	transparent = m.transparent;
	transparentWireframe = m.transparentWireframe;
	lightWireframe = m.lightWireframe;
	light = m.light;
    }
    
    public void setGraphicType(GraphicType g){ graphicType=g; }
    //public void setDisplayType(DisplayType d){ displayType=d; }
    
    public GraphicType getGraphicType(){ return graphicType; }
    //public DisplayType getDisplayType(){ return displayType; }
    
    public boolean isGL(){ return graphicType==GraphicType.GL; }
    public boolean isJava(){ return graphicType==GraphicType.JAVA; }
    
    //public boolean isWireframe(){ return displayType==DisplayType.WIREFRAME; }
    //public boolean isFill(){ return displayType==DisplayType.FILL; }
    //public boolean isTransparent(){ return displayType==DisplayType.TRANSPARENT; }
    
    public boolean isWireframe(){ return wireframe; }
    public boolean isFill(){ return fill; }
    public boolean isTransparent(){ return transparent; }
    
    public boolean isLight(){ return light; }
    
    public boolean isTransparentWireframe(){ return transparentWireframe; }
    public boolean isLightWireframe(){ return lightWireframe; }

    public void setDrawMode(boolean wireframe, boolean fill, boolean transparent){
	this.wireframe=wireframe;
	this.fill=fill;
	this.transparent=transparent;
    }
    
    public void toggleWireframe(){
	// either of wireframe or fill needs to stay true
	if(!wireframe || fill) wireframe = !wireframe;
    }
    public void toggleFill(){
	// either of wireframe or fill needs to stay true
	if(!fill || wireframe) fill = !fill;
    }
    public void toggleTransparent(){ transparent = !transparent; }
    
    public void enableLight(){ light=true; }
    public void disableLight(){ light=false; }

    
    public static IGraphicMode[] getAllModes(){
	GraphicType[] g = GraphicType.values();
	IGraphicMode[] ret = new IGraphicMode[g.length];
	for(int i=0; i<g.length; i++) ret[i] = new IGraphicMode(g[i]);
	
	/*
	DisplayType[] d = DisplayType.values();
	IGraphicMode[] ret = new IGraphicMode[g.length*d.length];
	for(int i=0; i<g.length; i++)
	    for(int j=0; j<d.length; j++)
		ret[i*d.length+j] = new IGraphicMode(g[i], d[j]);
	*/
	return ret;
    }
    
    
}