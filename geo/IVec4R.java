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

package igeo.geo;

import igeo.core.IParameterObject;
import igeo.core.IServerI;

/**
   Reference class of 4 dimensional vector to be used as IParameterObject.
   
   @author Satoru Sugihara
   @version 0.7.0.0;
*/
public class IVec4R extends IParameterObject implements IVec4I, IVecI, IReferenceParameter{
    protected IVec4Op op;
    
    public IVec4R(IVec4Op v){ op=v; }
    //public IVec4R(IVecOp v){ op=new FromVec(v); }
    public IVec4R(IVecI v){ op=new FromVec(v); }
    
    public IVec4R(IVecI v, double w){ op=new FromVecAndW(op,new IDouble(w)); }
    public IVec4R(IVecI v, IDoubleI w){ op=new FromVecAndW(op,w); }
    
    public IVec4R(double x, double y, double z, double w){ op = new IVec4(x,y,z,w); }
    public IVec4R(double x, double y, double z){ op = new IVec4(x,y,z,1.0); }    
    
    public IVec4R(IDoubleI x, IDoubleI y, IDoubleI z, IDoubleI w){
	op=new FromXYZW(x,y,z,w);
    }
    public IVec4R(IDoubleI x, IDoubleI y, IDoubleI z){ op = new FromXYZ(x,y,z); }
    
    
    public IVec4R(IServerI s, IVec4Op v){ super(s); op=v; }
    //public IVec4R(IServerI s, IVecOp v, ){ super(s); op=new FromVec(v); }
    public IVec4R(IServerI s, IVecI v){ super(s); op=new FromVec(v); }
    
    public IVec4R(IServerI s, IVecI v, double w){ super(s); op=new FromVecAndW(op,new IDouble(w)); }
    public IVec4R(IServerI s, IVecI v, IDoubleI w){ super(s); op=new FromVecAndW(op,w); }
    
    public IVec4R(IServerI s, double x, double y, double z, double w){ super(s); op = new IVec4(x,y,z,w); }
    public IVec4R(IServerI s, double x, double y, double z){ super(s); op = new IVec4(x,y,z,1.0); }    
    
    public IVec4R(IServerI s, IDoubleI x, IDoubleI y, IDoubleI z, IDoubleI w){
	super(s); op=new FromXYZW(x,y,z,w);
    }
    public IVec4R(IServerI s, IDoubleI x, IDoubleI y, IDoubleI z){
	super(s); op = new FromXYZ(x,y,z);
    }
    
    
    public double x(){ return op.get().x; }
    public double y(){ return op.get().y; }
    public double z(){ return op.get().z; }
    public double w(){ return op.get().w; }
    public IVec4 get(){ return op.get(); }

    public IVec4R dup(){ return new IVec4R(op); }
    
    
    public IVec2R to2d(){ return new IVec2R(new ToVec2(op)); }
    public IVecR to3d(){ return new IVecR(new ToVec(op)); }
    public IVec4R to4d(){ return dup(); }
    public IVec4R to4d(double w){ return new IVec4R(this,w); }
    public IVec4R to4d(IDoubleI w){ return new IVec4R(this,w); }
    
    
    public IDoubleR getX(){ return new IDoubleR(new X(op)); }
    public IDoubleR getY(){ return new IDoubleR(new Y(op)); }
    public IDoubleR getZ(){ return new IDoubleR(new Z(op)); }
    public IDoubleR getW(){ return new IDoubleR(new W(op)); }
    
    public IVec4Op operator(){ return op; } // for viewer
    
    
    public IVec4R set(double x, double y, double z, double w){ op=new IVec4(x,y,z,w); return this; }
    public IVec4R set(double x, double y, double z){ op=new IVec4(x,y,z,1.0); return this; }
    
    public IVec4R set(IVec4I u){ op=u; return this; }
    public IVec4R set(IVecI u){ op=new FromVec(u); return this; }
    
    
    public IVec4R set(IDoubleI x, IDoubleI y, IDoubleI z, IDoubleI w){
	op=new FromXYZW(x,y,z,w); return this;
    }
    public IVec4R set(IDoubleI x, IDoubleI y, IDoubleI z){
	op = new FromXYZ(x,y,z); return this;
    }
    public IVec4R set(IVecI v, double w){
	op = new FromVecAndW(v,new IDouble(w)); return this;
    }
    public IVec4R set(IVecI v, IDoubleI w){ op = new FromVecAndW(v,w); return this; }
    
    
    
    public IVec4R add(double x, double y, double z){
	op=new Add(op,new IVec(x,y,z)); return this;
    }
    public IVec4R add(IDoubleI x, IDoubleI y, IDoubleI z){
	op=new Add(op,new IVecR(x,y,z)); return this;
    }
    public IVec4R add(IVecI u){ op=new Add(op,u); return this; }
    
    public IVec4R sub(double x, double y, double z){
	op=new Sub(op,new IVec(x,y,z)); return this;
    }
    public IVec4R sub(IDoubleI x, IDoubleI y, IDoubleI z){
	op=new Sub(op,new IVecR(x,y,z)); return this;
    }
    public IVec4R sub(IVecI u){ op=new Sub(op,u); return this; }
    
    public IVec4R mul(IDoubleI u){ op=new Mul(op,u); return this; }
    public IVec4R mul(double u){ op=new Mul(op,new IDouble(u)); return this; }
    public IVec4R div(IDoubleI u){ op=new Div(op,u); return this; }
    public IVec4R div(double u){ op=new Div(op,new IDouble(u)); return this; }
    public IVec4R neg(){ op=new Neg(op); return this; }
    public IVec4R rev(){ op=new Neg(op); return this; }
    
    public IVec4R add(IVecI v, double f){ return add(v.dup().mul(f)); }
    public IVec4R add(IVecI v, IDoubleI f){ return add(v.dup().mul(f)); }
    
    public double dot(IVecI u){ return get().dot(u); }
    //public IDoubleR dotR(IVecI u){ return new IDoubleR(new IVecR.Dot(op,u)); }
    public double dot(ISwitchE e, IVecI u){ return dot(u); }
    public IDoubleR dot(ISwitchR r, IVecI u){ return new IDoubleR(new IVecR.Dot(op,u)); }
    
    /** cross now returns a new instance 2011/08/03
     */
    //public IVec4R cross(IVecI u){ op=new Cross(op,u); return this; }
    public IVec4R cross(IVecI u){ return new IVec4R(new Cross(op,u)); }
    
    public double len(){ return get().len(); }
    //public IDoubleR lenR(){ return new IDoubleR(new IVecR.Len(op)); }
    public double len(ISwitchE e){ return len(); }
    public IDoubleR len(ISwitchR r){ return new IDoubleR(new IVecR.Len(op)); }
    
    public double len2(){ return get().len2(); }
    //public IDoubleR len2R(){ return new IDoubleR(new IVecR.Len2(op)); }
    public double len2(ISwitchE e){ return len2(); }
    public IDoubleR len2(ISwitchR r){ return new IDoubleR(new IVecR.Len2(op)); }
    
    public IVec4R len(IDoubleI l){ op=new SetLen(op,l); return this; }
    public IVec4R len(double l){ op=new SetLen(op,new IDouble(l)); return this; }
    
    public IVec4R unit(){ op=new Unit(op); return this; }
    

    public double dist(IVecI v){ return get().dist(v); }
    //public IDoubleR distR(IVecI v){ return new IDoubleR(new Dist(op, v)); }
    public double dist(ISwitchE e, IVecI v){ return dist(v); }
    public IDoubleR dist(ISwitchR r, IVecI v){ return new IDoubleR(new Dist(op, v)); }
    
    public double dist2(IVecI v){ return get().dist2(v); }
    //public IDoubleR dist2R(IVecI v){ return new IDoubleR(new Dist2(op, v)); }
    public double dist2(ISwitchE e, IVecI v){ return dist2(v); }
    public IDoubleR dist2(ISwitchR r, IVecI v){ return new IDoubleR(new Dist2(op, v)); }
    
    public boolean eq(IVecI v){ return get().eq(v); }
    //public IBoolR eqR(IVecI v){ return new IBoolR(new Eq(op, v)); }
    public boolean eq(ISwitchE e, IVecI v){ return eq(v); }
    public IBoolR eq(ISwitchR r, IVecI v){ return new IBoolR(new Eq(op, v)); }
    
    public boolean eqX(IVecI v){ return get().eqX(v); }
    public boolean eqY(IVecI v){ return get().eqY(v); }
    public boolean eqZ(IVecI v){ return get().eqZ(v); }
    public boolean eqW(IVec4I v){ return get().eqW(v); }
    //public IBoolR eqXR(IVecI v){ return new IBoolR(new EqX(op, v)); }
    //public IBoolR eqYR(IVecI v){ return new IBoolR(new EqY(op, v)); }
    //public IBoolR eqZR(IVecI v){ return new IBoolR(new EqZ(op, v)); }
    //public IBoolR eqWR(IVec4I v){ return new IBoolR(new EqW(op, v)); }
    public boolean eqX(ISwitchE e, IVecI v){ return eqX(v); }
    public boolean eqY(ISwitchE e, IVecI v){ return eqY(v); }
    public boolean eqZ(ISwitchE e, IVecI v){ return eqZ(v); }
    public boolean eqW(ISwitchE e, IVec4I v){ return eqW(v); }
    public IBoolR eqX(ISwitchR r, IVecI v){ return new IBoolR(new EqX(op, v)); }
    public IBoolR eqY(ISwitchR r, IVecI v){ return new IBoolR(new EqY(op, v)); }
    public IBoolR eqZ(ISwitchR r, IVecI v){ return new IBoolR(new EqZ(op, v)); }
    public IBoolR eqW(ISwitchR r, IVec4I v){ return new IBoolR(new EqW(op, v)); }
    
    public boolean eq(IVecI v, double resolution){ return get().eq(v,resolution); }
    //public IBoolR eqR(IVecI v, IDoubleI resolution){ return new IBoolR(new Eq(op, v, resolution)); }
    public boolean eq(ISwitchE e, IVecI v, double resolution){ return eq(v,resolution); }
    public IBoolR eq(ISwitchR r, IVecI v, IDoubleI resolution){ return new IBoolR(new Eq(op, v, resolution)); }
    
    public boolean eqX(IVecI v, double resolution){ return get().eqX(v,resolution); }
    public boolean eqY(IVecI v, double resolution){ return get().eqY(v,resolution); }
    public boolean eqZ(IVecI v, double resolution){ return get().eqZ(v,resolution); }
    public boolean eqW(IVec4I v, double resolution){ return get().eqW(v,resolution); }
    //public IBoolR eqXR(IVecI v, IDoubleI resolution){ return new IBoolR(new EqX(op,v,resolution)); }
    //public IBoolR eqYR(IVecI v, IDoubleI resolution){ return new IBoolR(new EqY(op,v,resolution)); }
    //public IBoolR eqZR(IVecI v, IDoubleI resolution){ return new IBoolR(new EqZ(op,v,resolution)); }
    //public IBoolR eqWR(IVec4I v, IDoubleI resolution){ return new IBoolR(new EqW(op,v,resolution)); }
    public boolean eqX(ISwitchE e, IVecI v, double resolution){ return eqX(v,resolution); }
    public boolean eqY(ISwitchE e, IVecI v, double resolution){ return eqY(v,resolution); }
    public boolean eqZ(ISwitchE e, IVecI v, double resolution){ return eqZ(v,resolution); }
    public boolean eqW(ISwitchE e, IVec4I v, double resolution){ return eqW(v,resolution); }
    public IBoolR eqX(ISwitchR r, IVecI v, IDoubleI resolution){ return new IBoolR(new EqX(op,v,resolution)); }
    public IBoolR eqY(ISwitchR r, IVecI v, IDoubleI resolution){ return new IBoolR(new EqY(op,v,resolution)); }
    public IBoolR eqZ(ISwitchR r, IVecI v, IDoubleI resolution){ return new IBoolR(new EqZ(op,v,resolution)); }
    public IBoolR eqW(ISwitchR r, IVec4I v, IDoubleI resolution){ return new IBoolR(new EqW(op,v,resolution)); }
    
    public double angle(IVecI u){ return get().angle(u); }
    //public IDoubleI angleR(IVecI u){ return new IDoubleR(new IVecR.Angle(op,u)); }
    public double angle(ISwitchE e, IVecI u){ return angle(u); }
    public IDoubleR angle(ISwitchR r, IVecI u){ return new IDoubleR(new IVecR.Angle(op,u)); }
    
    public double angle(IVecI u, IVecI axis){ return get().angle(u,axis); }
    //public IDoubleR angleR(IVecI u, IVecI axis){ return new IDoubleR(new IVecR.Angle(op,u,axis)); }
    public double angle(ISwitchE e, IVecI u, IVecI axis){ return angle(u,axis); }
    public IDoubleR angle(ISwitchR r, IVecI u, IVecI axis){ return new IDoubleR(new IVecR.Angle(op,u,axis)); }
    
    
    public IVec4R rot(IVecI axis, IDoubleI angle){
	op=new Rot(op,axis,angle); return this; 
    }
    public IVec4R rot(IVecI axis, double angle){
	op=new Rot(op,axis,new IDouble(angle)); return this;
    }
    
    public IVec4R rot(IVecI center, IVecI axis, IDoubleI angle){
	if(center==this) return this;
	return sub(center).rot(axis,angle).add(center);
    }
    public IVec4R rot(IVecI center, IVecI axis, double angle){
	if(center==this) return this;
	return sub(center).rot(axis,angle).add(center);
    }
    
    public IVec4R rot(IVecI axis, IVecI destDir){
        return rot(axis, destDir.cross(axis).angle(cross(axis)));
    }
    
    public IVec4R rot(IVecI center, IVecI axis, IVecI destPt){
	if(center==this) return this;
	return sub(center).rot(axis,destPt.diff(center)).add(center);
    }
    
    public IVec4R scale(IDoubleI f){ return mul(f); }
    public IVec4R scale(double f){ return mul(f); }
    
    public IVec4R scale(IVecI center, IDoubleI f){
	if(center==this) return this;
	return sub(center).mul(f).add(center);
    }
    public IVec4R scale(IVecI center, double f){
	if(center==this) return this;
	return sub(center).mul(f).add(center);
    }
    
    public IVec4R mirror(IVecI planeDir){
        op=new Mirror(op,planeDir); return this; 
    }
    public IVec4R mirror(IVecI center, IVecI planeDir){
        if(center==this) return this;
        return sub(center).mirror(planeDir).add(center);
    }
    
    public IVec4R transform(IMatrix3I mat){ op = new Transform3(op,mat); return this; }
    public IVec4R transform(IMatrix4I mat){ op = new Transform4(op,mat); return this; }
    public IVec4R transform(IVecI xvec, IVecI yvec, IVecI zvec){
        op = new TransformVec3(op,xvec,yvec,zvec); return this;
    }
    public IVec4R transform(IVecI xvec, IVecI yvec, IVecI zvec, IVecI translate){
        op = new TransformVec4(op,xvec,yvec,zvec,translate); return this;
    }    
    
    
    // methods creating new instance
    public IVec4R diff(IVecI v){ return dup().sub(v); }
    public IVec4R mid(IVecI v){ return dup().add(v).div(2); }
    public IVec4R sum(IVecI v){ return dup().add(v); }
    public IVec4R sum(IVecI... v){
	IVec4R ret = this.dup();
	for(IVecI vi: v) ret.add(vi);
	return ret;
    }
    
    public IVec4R bisect(IVecI v){ return dup().unit().add(v.dup().unit()); }
    
    /**
       weighted sum, creating a new instance
    */
    public IVec4R sum(IVecI v2, IDoubleI w1, IDoubleI w2){
        return dup().mul(w1).add(v2,w2);
    }
    public IVec4R sum(IVecI v2, IDoubleI w2){
        return dup().mul(new IDouble(1.0).sub(w2)).add(v2,w2);
    }
    
    public IVec4R sum(IVecI v2, double w1, double w2){
        return sum(v2,new IDouble(w1),new IDouble(w2));
    }
    public IVec4R sum(IVecI v2, double w2){ return sum(v2,new IDouble(w2)); }    
    
    
    
    static public class FromVec extends IParameterObject implements IVec4Op{
	public IVecOp v;
	public FromVec(IVecOp v){ this.v=v; }
	public IVec4 get(){ return new IVec4(v.get()); }
    }
    static public class FromVecAndW extends IParameterObject implements IVec4Op{
	public IVecOp v;
	public IDoubleOp w;
	public FromVecAndW(IVecOp v, IDoubleOp w){ this.v=v; this.w=w; }
	public IVec4 get(){ return new IVec4(v.get(),w.x()); }
    }
    static public class FromXYZ extends IParameterObject implements IVec4Op{
	public IDoubleOp x,y,z;
	public FromXYZ(IDoubleOp x, IDoubleOp y, IDoubleOp z){ this.x=x; this.y=y; this.z=z; }
	public IVec4 get(){ return new IVec4(x.x(),y.x(),z.x(),1.0); }
    }
    static public class FromXYZW extends IParameterObject implements IVec4Op{
	public IDoubleOp x,y,z,w;
	public FromXYZW(IDoubleOp x, IDoubleOp y, IDoubleOp z, IDoubleOp w){
	    this.x=x; this.y=y; this.z=z; this.w=w;
	}
	public IVec4 get(){ return new IVec4(x.x(),y.x(),z.x(),w.x()); }
    }
    static public class ToVec extends IParameterObject implements IVecOp{
	public IVec4Op v;
	public ToVec(IVec4Op v){ this.v=v; }
	public IVec get(){ return new IVec(v.get()); }
    }
    static public class ToVec2 extends IParameterObject implements IVec2Op{
	public IVec4Op v;
	public ToVec2(IVec4Op v){ this.v=v; }
	public IVec2 get(){ return new IVec2(v.get()); }
    }
    
    static public class Add extends IParameterObject implements IVec4Op{
	public IVec4Op v1;
	public IVecOp v2;
	public Add(IVec4Op v1, IVecOp v2){ this.v1=v1; this.v2=v2; }
	public IVec4 get(){ return (IVec4)v1.get().add(v2.get()); }
    }
    
    static public class Sub extends IParameterObject implements IVec4Op{
	public IVec4Op v1;
	public IVecOp v2;
	public Sub(IVec4Op v1, IVecOp v2){ this.v1=v1; this.v2=v2; }
	public IVec4 get(){ return (IVec4)v1.get().sub(v2.get()); }
    }
    
    static public class Mul extends IParameterObject implements IVec4Op{
	public IVec4Op v;
	public IDoubleOp d;
	public Mul(IVec4Op v, IDoubleOp d){ this.v=v; this.d=d; }
	public IVec4 get(){ return (IVec4)v.get().mul(d.x()); }
    }
    
    static public class Div extends IParameterObject implements IVec4Op{
	public IVec4Op v;
	public IDoubleOp d;
	public Div(IVec4Op v, IDoubleOp d){ this.v=v; this.d=d; }
	public IVec4 get(){ return (IVec4)v.get().div(d.x()); }
    }
    
    static public class Neg extends IParameterObject implements IVec4Op{
	public IVec4Op v;
	public Neg(IVec4Op v){ this.v=v; }
	public IVec4 get(){ return (IVec4)v.get().neg(); }
    }
    
    
    /*
    static public class Dot extends IParameterObject implements IDoubleOp{
	IVecOp v1, v2;
	Dot(IVecOp v1, IVecOp v2){ this.v1=v1; this.v2=v2; }
	public double x(){ return v1.get().dotD(v2.get()); }
	public IDouble get(){ return v1.get().dot(v2.get()); }
    }
    */
    
    static public class Cross extends IParameterObject implements IVec4Op{
	public IVec4Op v1;
	public IVecOp v2;
	public Cross(IVec4Op v1, IVecOp v2){ this.v1=v1; this.v2=v2; }
	public IVec4 get(){ return (IVec4)v1.get().cross(v2.get()); }
    }
    
    /*
    static public class Angle implements IDoubleOp{
	IVecOp v1,v2;
	Angle(IVecOp v1, IVecOp v2){ this.v1=v1; this.v2=v2; }
	public double x(){ return v1.get().angleD(v2.get()); }
	public IDouble get(){ return v1.get().angle(v2.get()); }
    }
    
    static public class AngleWithAxis implements IDoubleOp{
	IVecOp v1,v2,axis;
	AngleWithAxis(IVecOp v1, IVecOp v2,IVecOp ax){ this.v1=v1; this.v2=v2; axis=ax; }
	public double x(){ return v1.get().angleD(v2.get(),axis.get()); }
	public IDouble get(){ return v1.get().angle(v2.get(),axis.get()); }
    }
    */
    
    static public class Rot extends IParameterObject implements IVec4Op{
	public IVec4Op v;
	public IVecOp axis;
	public IDoubleOp angle;
	public Rot(IVec4Op u, IVecOp ax, IDoubleOp a){ v=u; axis=ax; angle=a; }
	public IVec4 get(){ return (IVec4)v.get().rot(axis.get(),angle.x()); }
    }
    
    /*
    static public class Len implements IDoubleOp{
	IVecOp v;
	Len(IVecOp v){ this.v=v; }
	public double x(){ return v.get().lenD(); }
	public IDouble get(){ return v.get().len(); }
    }
    
    static public class Len2 implements IDoubleOp{
	IVecOp v;
	Len2(IVecOp v){ this.v=v; }
	public double x(){ return v.get().len2D(); }
	public IDouble get(){ return v.get().len2(); }
    }
    */
    
    static public class X extends IParameterObject implements IDoubleOp{
	public IVec4Op v;
	public X(IVec4Op v){ this.v=v; }
	public double x(){ return v.get().x; }
	public IDouble get(){ return v.get().getX(); }
    }
    static public class Y extends IParameterObject implements IDoubleOp{
	public IVec4Op v;
	public Y(IVec4Op v){ this.v=v; }
	public double x(){ return v.get().y; }
	public IDouble get(){ return v.get().getY(); }
    }
    static public class Z extends IParameterObject implements IDoubleOp{
	public IVec4Op v;
	public Z(IVec4Op v){ this.v=v; }
	public double x(){ return v.get().z; }
	public IDouble get(){ return v.get().getZ(); }
    }
    static public class W extends IParameterObject implements IDoubleOp{
	public IVec4Op v;
	public W(IVec4Op v){ this.v=v; }
	public double x(){ return v.get().w; }
	public IDouble get(){ return v.get().getW(); }
    }
    
    static public class Unit extends IParameterObject implements IVec4Op{
	public IVec4Op v;
	public Unit(IVec4Op v){ this.v=v; }
	public IVec4 get(){ return (IVec4)v.get().unit(); }
    }
    
    static public class SetLen extends IParameterObject implements IVec4Op{
	public IVec4Op v;
	public IDoubleOp l;
	public SetLen(IVec4Op v, IDoubleOp l){ this.v=v; this.l=l; }
	public IVec4 get(){ return (IVec4)v.get().len(l.x()); }
    }
    
    static public class Dist extends IParameterObject implements IDoubleOp{
	public IVec4Op v1;
        public IVecOp v2;
        public Dist(IVec4Op v1, IVecOp v2){ this.v1=v1; this.v2=v2; }
        public double x(){ return v1.get().dist(v2.get()); }
        public IDouble get(){ return v1.get().dist((Ir)null,v2.get()); }
    }
    
    static public class Dist2 extends IParameterObject implements IDoubleOp{
	public IVec4Op v1;
        public IVecOp v2;
        public Dist2(IVec4Op v1, IVecOp v2){ this.v1=v1; this.v2=v2; }
        public double x(){ return v1.get().dist2(v2.get()); }
        public IDouble get(){ return v1.get().dist2((Ir)null,v2.get()); }
    }
    
    static public class Eq extends IParameterObject implements IBoolOp{
	public IVec4Op v1;
        public IVecOp v2;
	public IDoubleOp resolution=null;
        public Eq(IVec4Op v1, IVecOp v2){ this.v1=v1; this.v2=v2; }
	public Eq(IVec4Op v1, IVecOp v2, IDoubleOp reso){ this.v1=v1; this.v2=v2; resolution=reso; }
        public boolean x(){
	    if(resolution==null) return v1.get().eq(v2.get());
	    return v1.get().eq(v2.get(),resolution.x());
	}
        public IBool get(){
	    if(resolution==null) return v1.get().eq((Ir)null,v2.get());
	    return v1.get().eq((Ir)null,v2.get(),resolution.get());
	}
    }
    
    static public class EqX extends IParameterObject implements IBoolOp{
	public IVec4Op v1;
        public IVecOp v2;
	public IDoubleOp resolution=null;
        public EqX(IVec4Op v1, IVecOp v2){ this.v1=v1; this.v2=v2; }
	public EqX(IVec4Op v1, IVecOp v2, IDoubleOp reso){ this.v1=v1; this.v2=v2; resolution=reso; }
        public boolean x(){
	    if(resolution==null) return v1.get().eqX(v2.get());
	    return v1.get().eqX(v2.get(),resolution.x());
	}
        public IBool get(){
	    if(resolution==null) return v1.get().eqX((Ir)null,v2.get());
	    return v1.get().eqX((Ir)null,v2.get(),resolution.get());
	}
    }
    
    
    static public class EqY extends IParameterObject implements IBoolOp{
	public IVec4Op v1;
        public IVecOp v2;
	public IDoubleOp resolution=null;
        public EqY(IVec4Op v1, IVecOp v2){ this.v1=v1; this.v2=v2; }
	public EqY(IVec4Op v1, IVecOp v2, IDoubleOp reso){ this.v1=v1; this.v2=v2; resolution=reso; }
        public boolean x(){
	    if(resolution==null) return v1.get().eqY(v2.get());
	    return v1.get().eqY(v2.get(),resolution.x());
	}
        public IBool get(){
	    if(resolution==null) return v1.get().eqY((Ir)null,v2.get());
	    return v1.get().eqY((Ir)null,v2.get(),resolution.get());
	}
    }
    
    static public class EqZ extends IParameterObject implements IBoolOp{
	public IVec4Op v1;
        public IVecOp v2;
	public IDoubleOp resolution=null;
        public EqZ(IVec4Op v1, IVecOp v2){ this.v1=v1; this.v2=v2; }
	public EqZ(IVec4Op v1, IVecOp v2, IDoubleOp reso){ this.v1=v1; this.v2=v2; resolution=reso; }
        public boolean x(){
	    if(resolution==null) return v1.get().eqZ(v2.get());
	    return v1.get().eqZ(v2.get(),resolution.x());
	}
        public IBool get(){
	    if(resolution==null) return v1.get().eqZ((Ir)null,v2.get());
	    return v1.get().eqZ((Ir)null,v2.get(),resolution.get());
	}
    }
    
    static public class EqW extends IParameterObject implements IBoolOp{
	public IVec4Op v1;
        public IVec4Op v2;
	public IDoubleOp resolution=null;
        public EqW(IVec4Op v1, IVec4Op v2){ this.v1=v1; this.v2=v2; }
	public EqW(IVec4Op v1, IVec4Op v2, IDoubleOp reso){ this.v1=v1; this.v2=v2; resolution=reso; }
        public boolean x(){
	    if(resolution==null) return v1.get().eqW(v2.get());
	    return v1.get().eqW(v2.get(),resolution.x());
	}
        public IBool get(){
	    if(resolution==null) return v1.get().eqW((Ir)null,v2.get());
	    return v1.get().eqW((Ir)null,v2.get(),resolution.get());
	}
    }
    
    
    static public class Mirror extends IParameterObject implements IVec4Op{
	public IVec4Op v;
	public IVecOp plane;
	public Mirror(IVec4Op v, IVecOp plane){ this.v=v; this.plane=plane; };
	public IVec4 get(){ return (IVec4)v.get().mirror(plane.get()); }
    }
    
    static public class Transform3 extends IParameterObject implements IVec4Op{
	public IVec4Op v;
	public IMatrix3Op mat;
	public Transform3(IVec4Op v, IMatrix3Op mat){ this.v=v; this.mat=mat; };
	public IVec4 get(){ return (IVec4)v.get().transform(mat.get()); }
    }
    
    static public class Transform4 extends IParameterObject implements IVec4Op{
	public IVec4Op v;
	public IMatrix4Op mat;
	public Transform4(IVec4Op v, IMatrix4Op mat){ this.v=v; this.mat=mat; };
	public IVec4 get(){ return (IVec4)v.get().transform(mat.get()); }
    }
    
    static public class TransformVec3 extends IParameterObject implements IVec4Op{
	public IVec4Op v;
	public IVecOp x, y, z;
	public TransformVec3(IVec4Op v, IVecOp x, IVecOp y, IVecOp z){
	    this.v=v; this.x=x; this.y=y; this.z=z;
	}
	public IVec4 get(){ return (IVec4)v.get().transform(x.get(),y.get(),z.get()); }
    }
    
    static public class TransformVec4 extends IParameterObject implements IVec4Op{
	public IVec4Op v;
	public IVecOp x, y, z, trans;
	public TransformVec4(IVec4Op v, IVecOp x, IVecOp y, IVecOp z, IVecOp trans){
	    this.v=v; this.x=x; this.y=y; this.z=z; this.trans=trans;
	}
	public IVec4 get(){ return (IVec4)v.get().transform(x.get(),y.get(),z.get(),trans.get()); }
    }
    
    
}
