package org.cibseven.community.bpm.data

import org.cibseven.community.bpm.data.factory.VariableFactory
import org.cibseven.bpm.engine.delegate.VariableScope
import org.cibseven.bpm.engine.variable.VariableMap

/**
 * Operator getter from global scope.
 * @param factory factory defining the variable.
 */
operator fun <T> VariableMap.get(factory: VariableFactory<T>): T = factory.from(this).get()

/**
 * Operator getter from global scope.
 * @param factory factory defining the variable.
 */
operator fun <T> VariableScope.get(factory: VariableFactory<T>): T = factory.from(this).get()

