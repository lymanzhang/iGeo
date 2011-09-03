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

import javax.media.opengl.*;

import igeo.geo.*;
import igeo.core.*;
//import igeo.gl.*;

/**
   Graphic subobject class to draw filled faces of a surface object by OpenGL.
   
   @author Satoru Sugihara
   @version 0.7.0.0;
*/
public class ISurfaceGraphicFillGL extends IGraphicObject{
    
    public static final boolean insertPointOnDegree1TwistedSurface=true;
    
    public int isoparmRatioU=IConfig.surfaceIsoparmResolution;
    public int isoparmRatioV=IConfig.surfaceIsoparmResolution;
    
    //public final static int fragmentResolution = 10;
    
    public ISurfaceI surface=null; // parent
    
    public IGLQuadMatrix quadMatrix=null;
    public IGLTriangles triangles=null;
    
    public ISurfaceGraphicFillGL(ISurface srf){
	super(srf);
	//initSurface();
    }
    
    public ISurfaceGraphicFillGL(ISurfaceR srf){
	super(srf);
	//initSurface();
    }
    
    public ISurfaceGraphicFillGL(ISurface srf, int isoparmRatioU, int isoparmRatioV ){
	super(srf);
	this.isoparmRatioU = isoparmRatioU;
	this.isoparmRatioV = isoparmRatioV;
	//initSurface();
    }
    
    public ISurfaceGraphicFillGL(ISurfaceR srf, int isoparmRatioU, int isoparmRatioV ){
	super(srf);
	this.isoparmRatioU = isoparmRatioU;
	this.isoparmRatioV = isoparmRatioV;
	//initSurface();
    }
    
    public void initSurface(){
	if(parent instanceof ISurface){ surface = ((ISurface)parent).surface; }
	else if(parent instanceof ISurfaceR){ surface = ((ISurfaceR)parent).surface; }
	if(!surface.hasTrim()||!surface.hasInnerTrim()&&surface.hasDefaultTrim())
	    initWithoutTrim(); // initialize IGLQuadMatrix
	else initWithTrim(); // initialize IGLTriangles
    }
    
    public void initWithoutTrim(){
	double[] uval;
	double[] vval;
	if(surface.udeg()==1){
	    int num = surface.unum();
	    uval = new double[num];
	    for(int i=0; i<num; i++) uval[i] = surface.u(i,0);
	}
	else{
	    int epnum = surface.uepNum();
	    int num = (epnum-1)*isoparmRatioU+1;
	    uval = new double[num];
	    for(int i=0; i<epnum; i++)
		for(int j=0; j<isoparmRatioU; j++)
		    if(i<epnum-1||j==0)
			uval[i*isoparmRatioU + j] = surface.u(i, (double)j/isoparmRatioU);
	}
	
	if(surface.vdeg()==1){
	    int num = surface.vnum();
	    vval = new double[num];
	    for(int i=0; i<num; i++) vval[i] = surface.v(i,0);
	}
	else{
	    int epnum = surface.vepNum();
	    int num = (epnum-1)*isoparmRatioV+1;
	    vval = new double[num];
	    for(int i=0; i<epnum; i++)
		for(int j=0; j<isoparmRatioV; j++)
		    if(i<epnum-1||j==0)
			vval[i*isoparmRatioV + j] = surface.v(i, (double)j/isoparmRatioV);
	}
	
	// insert points for deg 1 twisted surface
	if(insertPointOnDegree1TwistedSurface&&
	   surface.udeg()==1 && surface.vdeg()==1){
	    
	    boolean uinsert[] = new boolean[uval.length-1];
	    boolean vinsert[] = new boolean[vval.length-1];
	    boolean anyInsert=false;
	    for(int i=0; i<uval.length-1; i++) uinsert[i] = false;
	    for(int i=0; i<vval.length-1; i++) vinsert[i] = false;
	    
	    for(int i=0; i<uval.length-1; i++){
		for(int j=0; j<vval.length-1; j++){
		    if(!IVec.isFlat(surface.pt(uval[i],vval[j]),
				    surface.pt(uval[i+1],vval[j]),
				    surface.pt(uval[i+1],vval[j+1]),
				    surface.pt(uval[i],vval[j+1]))){
			uinsert[i] = true;
			vinsert[j] = true;
			anyInsert = true;
		    }
		}
	    }
	    
	    if(anyInsert){
		ArrayList<Double> uval2 = new ArrayList<Double>();
		for(int i=0; i<uval.length-1; i++){
		    uval2.add(uval[i]);
		    if(uinsert[i]){
			for(int j=1; j<isoparmRatioU; j++){
			    uval2.add(((uval[i+1]-uval[i])*j)/isoparmRatioU+uval[i]);
			}
		    }
		}
		uval2.add(uval[uval.length-1]);
		
		ArrayList<Double> vval2 = new ArrayList<Double>();
		for(int i=0; i<vval.length-1; i++){
		    vval2.add(vval[i]);
		    if(vinsert[i]){
			for(int j=1; j<isoparmRatioV; j++){
			    vval2.add(((vval[i+1]-vval[i])*j)/isoparmRatioV+vval[i]);
			}
		    }
		}
		vval2.add(vval[vval.length-1]);
		
		uval = new double[uval2.size()];
		for(int i=0; i<uval2.size(); i++) uval[i] = uval2.get(i);
		
		vval = new double[vval2.size()];
		for(int i=0; i<vval2.size(); i++) vval[i] = vval2.get(i);
	    }
	}
	
	IVec[][] pts = new IVec[uval.length][vval.length];
	IVec[][] nrm = new IVec[uval.length][vval.length];
	for(int i=0; i<uval.length; i++){
	    for(int j=0; j<vval.length; j++){
		pts[i][j] = surface.pt(uval[i], vval[j]).get();
		nrm[i][j] = surface.normal(uval[i], vval[j]).get().unit();
	    }
	}
	
	quadMatrix = new IGLQuadMatrix(pts,nrm);
    }
    
    public void initWithTrim(){
	
	ITrimLoopGraphic[] outtrims = null;
        ITrimLoopGraphic[] intrims = null;
        
        if(surface.hasOuterTrim()){
            outtrims = new ITrimLoopGraphic[surface.outerTrimLoopNum()];
            for(int i=0; i<surface.outerTrimLoopNum(); i++)
                outtrims[i] = new ITrimLoopGraphic(surface.outerTrimLoop(i),
						   true,
						   IConfig.surfaceTrimEdgeResolution);
        }
        else{
	    // default outer trim loop?
            //outtrims = new ITrimLoopGraphic[1];
            //outtrims[0] = new ITrimLoopGraphic(surface);
        }
        
        if(surface.hasInnerTrim()){
            intrims = new ITrimLoopGraphic[surface.innerTrimLoopNum()];
            for(int i=0; i<surface.innerTrimLoopNum(); i++)
                intrims[i] = new ITrimLoopGraphic(surface.innerTrimLoop(i),
						  false,
						  IConfig.surfaceTrimEdgeResolution);
        }
	
        int unum = isoparmRatioU*(surface.uepNum()-1)+1;
        int vnum = isoparmRatioV*(surface.vepNum()-1)+1;
	
	double[] uval = null;
	double[] vval = null;
	
	if(surface.udeg()==1){
	    unum = surface.unum();
	    uval = new double[unum];
	    for(int i=0; i<unum; i++) uval[i] = (double)i/(unum-1);
	}
	else{
	    uval = new double[unum];
	    for(int i=0; i<surface.uepNum(); i++)
		for(int j=0; j<isoparmRatioU&&i<surface.uepNum()-1||j==0; j++)
		    uval[i*isoparmRatioU+j] =
			surface.u(i,(double)j/isoparmRatioU);
	}
	
	if(surface.vdeg()==1){
	    vnum = surface.vnum();
	    vval = new double[vnum];
	    for(int i=0; i<vnum; i++) vval[i] = (double)i/(vnum-1);
	}
	else{
	    vval = new double[vnum];
	    for(int k=0; k<surface.vepNum(); k++)
		for(int l=0; l<isoparmRatioV&&k<surface.vepNum()-1||l==0; l++){
		    vval[k*isoparmRatioV+l] =
			surface.v(k,(double)l/isoparmRatioV);
		    
		    //IOut.debug(20, "vval["+k+"*"+isoparmRatioV+"+"+l+"] = "+vval[k*isoparmRatioV+l]); //
		}
	}
	
	// insert points for deg 1 twisted surface
	if(insertPointOnDegree1TwistedSurface&&
	   surface.udeg()==1 && surface.vdeg()==1){
	    
	    boolean uinsert[] = new boolean[uval.length-1];
	    boolean vinsert[] = new boolean[vval.length-1];
	    boolean anyInsert=false;
	    for(int i=0; i<uval.length-1; i++) uinsert[i] = false;
	    for(int i=0; i<vval.length-1; i++) vinsert[i] = false;
	    
	    for(int i=0; i<uval.length-1; i++){
		for(int j=0; j<vval.length-1; j++){
		    if(!IVec.isFlat(surface.pt(uval[i],vval[j]),
				    surface.pt(uval[i+1],vval[j]),
				    surface.pt(uval[i+1],vval[j+1]),
				    surface.pt(uval[i],vval[j+1]))){
			uinsert[i] = true;
			vinsert[j] = true;
			anyInsert = true;
		    }
		}
	    }
	    
	    if(anyInsert){
		ArrayList<Double> uval2 = new ArrayList<Double>();
		for(int i=0; i<uval.length-1; i++){
		    uval2.add(uval[i]);
		    if(uinsert[i]){
			for(int j=1; j<isoparmRatioU; j++){
			    uval2.add(((uval[i+1]-uval[i])*j)/isoparmRatioU+uval[i]);
			}
		    }
		}
		uval2.add(uval[uval.length-1]);
		
		ArrayList<Double> vval2 = new ArrayList<Double>();
		for(int i=0; i<vval.length-1; i++){
		    vval2.add(vval[i]);
		    if(vinsert[i]){
			for(int j=1; j<isoparmRatioV; j++){
			    vval2.add(((vval[i+1]-vval[i])*j)/isoparmRatioV+vval[i]);
			}
		    }
		}
		vval2.add(vval[vval.length-1]);
		
		uval = new double[uval2.size()];
		for(int i=0; i<uval2.size(); i++) uval[i] = uval2.get(i);
		unum = uval.length;
		
		vval = new double[vval2.size()];
		for(int i=0; i<vval2.size(); i++) vval[i] = vval2.get(i);
		vnum = vval.length;
	    }
	    
	}
	
	
	IVec2[][] surfPts = new IVec2[unum][vnum];
	
	for(int i=0; i<unum; i++){
	    for(int j=0; j<vnum; j++){
		surfPts[i][j] = new IVec2(uval[i],vval[j]);
	    }
	}
	
	/*
	for(int i=0; i<surface.uepNum(); i++)
	    for(int j=0; j<isoparmRatioU && i<surface.uepNum()-1 || j==0; j++)
		for(int k=0; k<surface.vepNum(); k++)
		    for(int l=0; l<isoparmRatioV && k<surface.uepNum()-1 || l==0; l++)
			surfPts[i*isoparmRatioU+j][k*isoparmRatioV+l] =
			    new IVec2(surface.u(i,(double)j/isoparmRatioU),
				       surface.v(k,(double)l/isoparmRatioV));
	*/
	
	IVec2[][] outerPts = null;
	if(outtrims!=null){
	    outerPts = new IVec2[outtrims.length][];
	    for(int i=0; i<outtrims.length; i++)
		outerPts[i] = outtrims[i].getPolyline2D().get();
	}
	
	IVec2[][] innerPts = null;
	if(intrims!=null){
	    innerPts = new IVec2[intrims.length][];
	    for(int i=0; i<intrims.length; i++)
		innerPts[i] = intrims[i].getPolyline2D().get();
	}
	
	IVec2[][] triangles2D = ISurfaceMesh.getTriangles(surfPts,outerPts,innerPts);
		
	IVec[][] triangles3D = new IVec[triangles2D.length][3];
	IVec[][] trianglesNormal = new IVec[triangles2D.length][3];
	for(int i=0; i<triangles2D.length; i++){
	    for(int j=0; j<triangles2D[i].length; j++){
		triangles3D[i][j] = surface.pt(triangles2D[i][j]).get();
		trianglesNormal[i][j] = surface.normal(triangles2D[i][j]).get().unit();
	    }
	}
	
	triangles = new IGLTriangles(triangles3D,trianglesNormal);
	
    }
    
    public boolean isDrawable(IGraphicMode m){ return m.isGL()&&m.isFill(); }
    
    public void draw(IGraphics g){
	if(surface==null) initSurface(); // not initizlized at the constructor // shouldn't it?
	
	GL gl = g.getGL();
	if(gl!=null){
	    //gl.glLineWidth(0.01f);
	    //gl.glLineWidth(1f);
	    //gl.glLineStipple(0,(short)0xFFFF);
	    
	    float red = ISurfaceGraphicGL.defaultColorRed;
	    float green = ISurfaceGraphicGL.defaultColorGreen;
	    float blue = ISurfaceGraphicGL.defaultColorBlue;
	    float alpha = ISurfaceGraphicGL.defaultColorAlpha;
	    
	    if(color!=null){
		red = (float)color.getRed()/255;
		green = (float)color.getGreen()/255;
		blue = (float)color.getBlue()/255;
		alpha = (float)color.getAlpha()/255;
	    }
	    
	    if(g.view().mode().isTransparent()){ alpha = (float)transparentModeAlpha; }
	    
	    if(g.view().mode().isLight()){
		float[] colorf = new float[]{ red, green, blue, alpha };
		gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, colorf, 0);
		gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, colorf, 0);
		//gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, colorf, 0);
		gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS,
			       ISurfaceGraphicGL.defaultShininess);
		gl.glColor4f(red, green, blue, 0f); // ? without this, the color is tinted with the previous object's color
	    }
	    else{ gl.glColor4f(red, green, blue, alpha); }
	    
	    if(quadMatrix!=null) quadMatrix.draw(gl);
	    if(triangles!=null) triangles.draw(gl);
	}
    }
    
    
    
}