package org.cibseven.community.bpm.data.acl.apply

import org.cibseven.bpm.engine.delegate.VariableScope
import org.cibseven.bpm.engine.variable.VariableMap


/**
 * Interface describing the strategy to assign values.
 */
@FunctionalInterface
interface ValueApplicationStrategy {
  /**
   * Strategy to assign variables stored in a map to the given variable scope.
   */
  fun apply(variableMap: VariableMap, variableScope: VariableScope): VariableScope
}
