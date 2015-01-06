package intervalAnalysis;

import java.util.HashMap;
import java.util.Map;

import soot.Value;

public class State {
    private Map<Value, VarState> varToState;
    
    public State() {
        varToState = new HashMap<Value, VarState>();
    }
    
    void copy(State dest){
        dest.varToState = new HashMap<Value, VarState>(this.varToState);
    }
    
    VarState getVarState(Value varName) {
        return varToState.get(varName);
    }
    
    void setVarState(Value varName, VarState varState) {
        varToState.put(varName, varState);
    }
    
    VarState updateVarState(Value varName, VarState varState) {
        VarState newState;
        VarState oldState = varToState.get(varName);        
        if (oldState == null) {
            newState = varState;
        } else {
            newState = oldState.join(varState);
        }
        setVarState(varName, newState);
        return newState;
    }
    
}
