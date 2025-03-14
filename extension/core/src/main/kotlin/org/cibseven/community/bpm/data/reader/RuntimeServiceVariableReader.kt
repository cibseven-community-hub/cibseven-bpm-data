package org.cibseven.community.bpm.data.reader

import org.cibseven.community.bpm.data.factory.VariableFactory
import org.cibseven.bpm.engine.RuntimeService
import java.util.*

/**
 * Allows reading multiple variable values from [RuntimeService.getVariable].
 * @param runtimeService runtime service to use.
 * @param executionId    execution id.
 */
class RuntimeServiceVariableReader(private val runtimeService: RuntimeService, private val executionId: String) : VariableReader {
  override fun <T> getOptional(variableFactory: VariableFactory<T>): Optional<T> {
    return variableFactory.from(runtimeService, executionId).getOptional()
  }

  override operator fun <T> get(variableFactory: VariableFactory<T>): T {
    return variableFactory.from(runtimeService, executionId).get()
  }

  override fun <T> getLocal(variableFactory: VariableFactory<T>): T {
    return variableFactory.from(runtimeService, executionId).getLocal()
  }

  override fun <T> getLocalOptional(variableFactory: VariableFactory<T>): Optional<T> {
    return variableFactory.from(runtimeService, executionId).getLocalOptional()
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    val that = other as RuntimeServiceVariableReader
    return if (runtimeService != that.runtimeService) false else executionId == that.executionId
  }

  override fun hashCode(): Int {
    return Objects.hash(runtimeService, executionId)
  }
}
