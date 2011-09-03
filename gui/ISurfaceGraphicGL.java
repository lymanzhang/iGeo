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

import java.util.ArrayList;
import java.awt.Color;

import igeo.geo.*;
import igeo.core.*;
//import igeo.gl.*;

/**
   Graphic subobject class to draw a surface object by OpenGL.
   It contains ISurfaceGraphicFillGL and  ISurfaceGraphicWireframeGL inside.
   
   @author Satoru Sugihara
   @version 0.7.0.0;
*/
public class ISurfaceGraphicGL extends IGraphicObject{
    public static float defaultColorRed = .5f; //1f;
    public static float defaultColorGreen = .5f; //1f;
    public static float defaultColorBlue = .5f; //1f;
    public static float defaultColorAlpha = 1f;

    public static float defaultShininess=0.3f; //0.5f; //1f; //5f; //1f; //0.1f; //0.5f; //1f;
    
    
    public ISurfaceGraphicFillGL fill;
    public ISurfaceGraphicWireframeGL wireframe;
    
    public ISurfaceGraphicGL(ISurface srf){ super(srf); }
    public ISurfaceGraphicGL(ISurfaceR srf){ super(srf); }
    
    public void setColor(Color c){
	super.setColor(c);
	if(fill!=null) fill.setColor(c);
	if(wireframe!=null) wireframe.setColor(c);
    }
    
    public boolean isDrawable(IGraphicMode m){ return m.isGL(); }
    
    public void draw(IGraphics g){
	if(g.view().mode().isWireframe()){
	    if(wireframe==null){
		if(parent instanceof ISurface)
		    wireframe = new ISurfaceGraphicWireframeGL((ISurface)parent);
		else if(parent instanceof ISurfaceR)
		    wireframe = new ISurfaceGraphicWireframeGL((ISurfaceR)parent);
		wireframe.setColor(color);
	    }
	    wireframe.draw(g);
	}
	
	if(g.view().mode().isFill()){
	    if(fill==null){
		if(parent instanceof ISurface)
		    fill = new ISurfaceGraphicFillGL((ISurface)parent);
		else if(parent instanceof ISurfaceR)
		    fill = new ISurfaceGraphicFillGL((ISurfaceR)parent);
		fill.setColor(color);
	    }
	    fill.draw(g);
	}
    }
    
    
    
}