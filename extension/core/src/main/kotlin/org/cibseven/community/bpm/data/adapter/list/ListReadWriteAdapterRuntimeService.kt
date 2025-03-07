package org.cibseven.community.bpm.data.adapter.list

import org.cibseven.bpm.engine.RuntimeService
import java.util.*

/**
 * Read write adapter for runtime service access.
 *
 * @param [T] type of value.
 * @param runtimeService runtime service to use.
 * @param executionId    id of the execution to read from and write to.
 * @param variableName   name of the variable.
 * @param memberClazz    class of the variable.
 */
class ListReadWriteAdapterRuntimeService<T>(
  private val runtimeService: RuntimeService,
  private val executionId: String,
  variableName: String,
  memberClazz: Class<T>
) : AbstractListReadWriteAdapter<T>(variableName, memberClazz) {

  override fun getOptional(): Optional<List<T>> {
    return Optional.ofNullable(
      getOrNull(
        runtimeService.getVariable(
          executionId, variableName
        )
      )
    )
  }

  override fun set(value: List<T>, isTransient: Boolean) {
    runtimeService.setVariable(executionId, variableName, getTypedValue(value, isTransient))
  }

  override fun getLocalOptional(): Optional<List<T>> {
    return Optional.ofNullable(
      getOrNull(
        runtimeService.getVariableLocal(
          executionId, variableName
        )
      )
    )
  }

  override fun setLocal(value: List<T>, isTransient: Boolean) {
    runtimeService.setVariableLocal(executionId, variableName, getTypedValue(value, isTransient))
  }

  override fun remove() {
    runtimeService.removeVariable(executionId, variableName)
  }

  override fun removeLocal() {
    runtimeService.removeVariableLocal(executionId, variableName)
  }
}
