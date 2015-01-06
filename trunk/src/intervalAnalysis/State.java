package intervalAnalysis;

import java.util.HashMap;
import java.util.Map;

import soot.Value;
import soot.jimple.IntConstant;

public class State {
    private Map<Value, VarState> nameToState;
    
    public State() {
        nameToState = new HashMap<Value, VarState>();
    }
    
    public void copy(State dest){
        dest.nameToState = new HashMap<Value, VarState>(this.nameToState);
    }
    
    public VarState getVarState(Value varName) {
        if (varName instanceof IntConstant) {
            return new Interval((IntConstant) varName,
                    (IntConstant) varName);
        } else {
            return nameToState.get(varName);
        }
    }
    
    public void setVarState(Value varName, VarState varState) {
        nameToState.put(varName, varState);
    }
    
    public VarState updateVarState(Value varName, VarState varState) {
        VarState newState;
        VarState oldState = nameToState.get(varName);        
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
