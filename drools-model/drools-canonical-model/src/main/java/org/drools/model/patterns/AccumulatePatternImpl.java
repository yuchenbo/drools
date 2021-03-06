package org.drools.model.patterns;

import java.util.Collection;
import java.util.Optional;

import org.drools.model.AccumulatePattern;
import org.drools.model.Binding;
import org.drools.model.Constraint;
import org.drools.model.DataSourceDefinition;
import org.drools.model.Pattern;
import org.drools.model.Variable;
import org.drools.model.functions.accumulate.AccumulateFunction;
import org.drools.model.impl.ModelComponent;

public class AccumulatePatternImpl<T> extends AbstractSinglePattern implements AccumulatePattern<T>, ModelComponent {

    private Pattern<T> pattern;
    private final Optional<CompositePatterns> compositePatterns;
    private final AccumulateFunction[] accumulateFunctions;
    private final Variable[] boundVariables;

    public AccumulatePatternImpl(Pattern<T> pattern, Optional<CompositePatterns> compositePatterns, AccumulateFunction... accumulateFunctions) {
        this.pattern = pattern;
        this.compositePatterns = compositePatterns;
        this.accumulateFunctions = accumulateFunctions;
        boundVariables = new Variable[accumulateFunctions.length];
        for (int i = 0; i < accumulateFunctions.length; i++) {
            boundVariables[i] = accumulateFunctions[i].getVariable();
        }
    }

    public void setPattern(Pattern<T> pattern) {
        this.pattern = pattern;
    }

    @Override
    public AccumulateFunction[] getAccumulateFunctions() {
        return accumulateFunctions;
    }

    @Override
    public Optional<CompositePatterns> getCompositePatterns() {
        return compositePatterns;
    }

    @Override
    public Pattern getPattern() {
        return pattern;
    }

    @Override
    public Variable[] getInputVariables() {
        return pattern.getInputVariables();
    }

    @Override
    public DataSourceDefinition getDataSourceDefinition() {
        return pattern.getDataSourceDefinition();
    }

    @Override
    public Variable<T> getPatternVariable() {
        if(pattern == null) {
            return null;
        }
        return pattern.getPatternVariable();
    }

    @Override
    public Collection<Binding> getBindings() {
        return pattern.getBindings();
    }

    @Override
    public String[] getWatchedProps() {
        return pattern.getWatchedProps();
    }

    @Override
    public Variable<?>[] getBoundVariables() {
        return boundVariables;
    }

    @Override
    public Constraint getConstraint() {
        return pattern.getConstraint();
    }

    @Override
    public Type getType() {
        return Type.ACCUMULATE;
    }

    @Override
    public boolean isEqualTo( ModelComponent o ) {
        if ( this == o ) return true;
        if ( !(o instanceof AccumulatePatternImpl) ) return false;

        AccumulatePatternImpl<?> that = ( AccumulatePatternImpl<?> ) o;

        if ( !ModelComponent.areEqualInModel( pattern, that.pattern ) ) return false;
        if ( !ModelComponent.areEqualInModel(accumulateFunctions, that.accumulateFunctions) ) return false;
        return ModelComponent.areEqualInModel( boundVariables, that.boundVariables );
    }
}
