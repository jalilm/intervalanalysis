package intervalAnalysis;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import abstraction.Bottom;
import abstraction.Interval;
import abstraction.LatticeElement;
import soot.Value;
import soot.jimple.IntConstant;

public class State {
    private Map<Value, LatticeElement> nameToState;
    
    public State() {
        nameToState = new HashMap<Value, LatticeElement>();
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
        StringBuilder sb = new StringBuilder("{");
        for(Value v : nameToState.keySet()) {
            sb.append(v.toString()+"->"+nameToState.get(v).toString());
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
        return true;
    }

    @Override
    public int hashCode() {
        return nameToState.hashCode();
    }
    
    public void copy(State dest){
        dest.nameToState = new HashMap<Value, LatticeElement>(this.nameToState);
    }
    
    public LatticeElement getLatticeElement(Value varName) {
        if (varName instanceof IntConstant) {
            return new Interval((IntConstant) varName,
                    (IntConstant) varName);
        } else if (nameToState.containsKey(varName)) {
            return nameToState.get(varName);
        } else {
            return new Bottom();
        }
    }
    
    public void setLatticeElement(Value varName, LatticeElement varState) {
        nameToState.put(varName, varState);
    }
    
    public LatticeElement updateLatticeElement(Value varName, LatticeElement varState) {
        LatticeElement newState;
        LatticeElement oldState = nameToState.get(varName);        
        if (oldState == null) {
            newState = varState;
        } else {
            newState = oldState.join(varState);
        }
        setLatticeElement(varName, newState);
        return newState;
    }

    public State merge(State in) {
        State res = new State();
        if (in == null) { 
            this.copy(res);
        } else {
            in.copy(res);
            for (Value name : nameToState.keySet()) {
                res.updateLatticeElement(name, this.getLatticeElement(name));
            }
        }
        return res;
    }
    
}
