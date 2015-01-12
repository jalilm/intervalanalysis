package intervalAnalysis;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import abstraction.Interval;
import abstraction.LatticeElement;
import abstraction.Top;
import soot.Value;
import soot.jimple.IntConstant;

public class State {
    private Map<Value, LatticeElement> nameToState;
    
    private boolean bottom;
    
    public State() {
        nameToState = new HashMap<Value, LatticeElement>();
        bottom = false;
    }
    
    public boolean isBottom(){
        return bottom;
    }
    
    public String print(){
        StringBuilder sb = new StringBuilder();
        List<Value> valList = new LinkedList<Value>(nameToState.keySet());
        Collections.sort(valList, new Comparator<Value>() {
            @Override
            public int compare(Value o1, Value o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        for(Value v : valList) {
            sb.append(v.toString());
            sb.append(" -> ");
            sb.append(nameToState.get(v).toString());
            sb.append("\r\n");
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        if(this.bottom) {
            return "bottom";
        }
        StringBuilder sb = new StringBuilder("{");
        for(Value v : nameToState.keySet()) {
            sb.append(v.toString()+"->"+nameToState.get(v).toString()+";");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        State other = (State) obj;
        if (!this.nameToState.equals(other.nameToState)) {
            return false;
        }
        if (!this.bottom == other.bottom) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return nameToState.hashCode();
    }
    
    public void copy(State dest){
        dest.nameToState = new HashMap<Value, LatticeElement>(this.nameToState);
        dest.bottom = bottom;
    }
    
    public LatticeElement getVarState(Value varName) {
        if (varName instanceof IntConstant) {
            return new Interval((IntConstant) varName,
                    (IntConstant) varName);
        } else if (nameToState.containsKey(varName)) {
            return nameToState.get(varName);
        } else {
        	// if we have a new variable, it may be [-inf,inf]
            return new Top();
        }
    }
    
    public void setVarState(Value varName, LatticeElement varState) {
    	if (varState == null)  {
    		throw new NullPointerException("Null into setVarState");
    	}
        nameToState.put(varName, varState);
    }
    
    public LatticeElement updateVarState(Value varName, LatticeElement varState) {
        if (varState == null)  {
            throw new NullPointerException("Null into updateVarState");
        }
        LatticeElement newState;
        LatticeElement oldState = nameToState.get(varName);        
        if (oldState == null) {
            newState = varState;
        } else {
            newState = oldState.join(varState);
        }
        setVarState(varName, newState);
        return newState;
    }

    public State join(State in2) {
        State out = new State();
        if (in2 == null) { 
            this.copy(out);
        } else if (in2.isBottom()){
            this.copy(out);
        } else if (this.isBottom()) {
            in2.copy(out);
        } else {
            in2.copy(out);
            for (Value name : nameToState.keySet()) {
                out.updateVarState(name, this.getVarState(name));
            }
        }
        return out;
    }
    
    public State meet(State in2) {
        State out = new State();
        if (in2 == null) { 
            this.copy(out);
        } else if (this.isBottom() || in2.isBottom()){
            State bot = new State();
            bot.setBottom(true);
            return bot;
        } else {
            this.copy(out);
            for (Value name : nameToState.keySet()) {
            	LatticeElement i1 = in2.getVarState(name); 
            	LatticeElement i2 = out.getVarState(name);

            	if (i1 != null)
            		out.setVarState(name, i1.meet(i2));
            }
        }
        return out;
    }
    
//    public State merge(State in2) {
//        State out = new State();
//        if (in2 == null) { 
//            this.copy(out);
//        } else {
//            this.copy(out);
//            for (Value name : in2.nameToState.keySet()) {
//            		out.setVarState(name, in2.getVarState(name));
//            }
//        }
//        return out;
//    }
 
    @Override
    public State clone() {
        State cloned = new State();
        this.copy(cloned);
        return cloned;
    }

    public void setBottom(boolean b) {
        bottom = b;
    }
}
