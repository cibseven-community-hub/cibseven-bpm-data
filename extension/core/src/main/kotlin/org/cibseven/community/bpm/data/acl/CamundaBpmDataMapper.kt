package org.cibseven.community.bpm.data.acl

import org.cibseven.community.bpm.data.CamundaBpmData
import org.cibseven.community.bpm.data.acl.apply.GlobalScopeReplaceStrategy
import org.cibseven.community.bpm.data.acl.apply.LocalScopeReplaceStrategy
import org.cibseven.community.bpm.data.acl.transform.IdentityVariableMapTransformer
import org.cibseven.community.bpm.data.acl.transform.VariableMapTransformer
import org.cibseven.community.bpm.data.guard.VariablesGuard
import org.cibseven.bpm.engine.variable.VariableMap

/**
 * Helper methods to create unconditional transient variable mappers.
 */
object CamundaBpmDataMapper {
  /**
   * Constructs a mapper, maps variables using transformer and replaces them in a local scope.
   * @param variableName name of the transient variable to use.
   * @param variableMapTransformer transformer to map from external to internal representation.
   */
  @JvmStatic
  fun transformingLocalReplace(variableName: String, variableMapTransformer: VariableMapTransformer) = AntiCorruptionLayer(
    precondition = VariablesGuard.EMPTY,
    variableMapTransformer = variableMapTransformer,
    factory = CamundaBpmData.customVariable(variableName, VariableMap::class.java),
    valueApplicationStrategy = LocalScopeReplaceStrategy
  )

  /**
   * Constructs a mapper, maps variables using transformer and replaces them in a global scope.
   * @param variableName name of the transient variable to use.
   * @param variableMapTransformer transformer to map from external to internal representation.
   */
  @JvmStatic
  fun transformingGlobalReplace(variableName: String, variableMapTransformer: VariableMapTransformer) = AntiCorruptionLayer(
    precondition = VariablesGuard.EMPTY,
    variableMapTransformer = variableMapTransformer,
    factory = CamundaBpmData.customVariable(variableName, VariableMap::class.java),
    valueApplicationStrategy = GlobalScopeReplaceStrategy
  )

  /**
   * Constructs a mapper, maps variables using transformer and replaces them in a scope depending on flag.
   * @param variableName name of the transient variable to use.
   * @param variableMapTransformer transformer to map from external to internal representation.
   * @param local flag to control local or global scope
   */
  @JvmStatic
  fun transformingReplace(variableName: String, local: Boolean, variableMapTransformer: VariableMapTransformer) = if (local) {
    transformingLocalReplace(variableName, variableMapTransformer)
  } else {
    transformingGlobalReplace(variableName, variableMapTransformer)
  }

  /**
   * Constructs a mapper, maps variables 1:1 and replaces them in a local scope.
   * @param variableName name of the transient variable to use.
   */
  @JvmStatic
  fun identityLocalReplace(variableName: String) = AntiCorruptionLayer(
    precondition = VariablesGuard.EMPTY,
    variableMapTransformer = IdentityVariableMapTransformer,
    factory = CamundaBpmData.customVariable(variableName, VariableMap::class.java),
    valueApplicationStrategy = LocalScopeReplaceStrategy
  )

  /**
   * Constructs a mapper, maps variables 1:1 and replaces them in a global scope.
   * @param variableName name of the transient variable to use.
   */
  @JvmStatic
  fun identityGlobalReplace(variableName: String) = AntiCorruptionLayer(
    precondition = VariablesGuard.EMPTY,
    variableMapTransformer = IdentityVariableMapTransformer,
    factory = CamundaBpmData.customVariable(variableName, VariableMap::class.java),
    valueApplicationStrategy = GlobalScopeReplaceStrategy
  )

  /**
   * Constructs a mapper, maps variables 1:1 and replaces them in scope depending on flag.
   * @param variableName name of the transient variable to use.
   * @param local flag to control local or global scope
   */
  @JvmStatic
  fun identityReplace(variableName: String, local: Boolean) = if (local) {
    identityLocalReplace(variableName)
  } else {
    identityGlobalReplace(variableName)
  }

}
