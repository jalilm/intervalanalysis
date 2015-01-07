package intervalAnalysis;

import java.util.HashMap;
import java.util.Map;

import abstraction.Interval;
import abstraction.LatticeElement;
import soot.Value;
import soot.jimple.IntConstant;

public class State {
    private Map<Value, LatticeElement> nameToState;
    
    public State() {
        nameToState = new HashMap<Value, LatticeElement>();
    }
    
    public void copy(State dest){
        dest.nameToState = new HashMap<Value, LatticeElement>(this.nameToState);
    }
    
    public LatticeElement getVarState(Value varName) {
        if (varName instanceof IntConstant) {
            return new Interval((IntConstant) varName,
                    (IntConstant) varName);
        } else {
            return nameToState.get(varName);
        }
    }
    
    public void setVarState(Value varName, LatticeElement varState) {
        nameToState.put(varName, varState);
    }
    
    public LatticeElement updateVarState(Value varName, LatticeElement varState) {
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

    public State merge(State in2) {
        State out = new State();
        if (in2 == null) { 
            this.copy(out);
        } else {
            in2.copy(out);
            for (Value name : nameToState.keySet()) {
                out.updateVarState(name, this.getVarState(name));
            }
        }
        return out;
    }
    
}
