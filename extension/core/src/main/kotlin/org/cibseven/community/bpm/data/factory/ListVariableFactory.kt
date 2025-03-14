package org.cibseven.community.bpm.data.factory

import org.cibseven.community.bpm.data.adapter.ReadAdapter
import org.cibseven.community.bpm.data.adapter.WriteAdapter
import org.cibseven.community.bpm.data.adapter.list.*
import org.cibseven.bpm.engine.CaseService
import org.cibseven.bpm.engine.RuntimeService
import org.cibseven.bpm.engine.TaskService
import org.cibseven.bpm.engine.delegate.VariableScope
import org.cibseven.bpm.engine.externaltask.LockedExternalTask
import org.cibseven.bpm.engine.variable.VariableMap
import java.util.*

/**
 * Variable factory of a base parametrized list type.
 *
 * @param [T] member type of the factory.
 */
class ListVariableFactory<T>(override val name: String, val memberClass: Class<T>) : VariableFactory<List<T>> {

  companion object {
    inline fun <reified T> forType(name: String) = ListVariableFactory(name, T::class.java)
  }

  override fun on(variableScope: VariableScope): WriteAdapter<List<T>> {
    return ListReadWriteAdapterVariableScope(
      variableScope,
      name,
      memberClass
    )
  }

  override fun from(variableScope: VariableScope): ReadAdapter<List<T>> {
    return ListReadWriteAdapterVariableScope(
      variableScope,
      name,
      memberClass
    )
  }

  override fun on(variableMap: VariableMap): WriteAdapter<List<T>> {
    return ListReadWriteAdapterVariableMap(variableMap, name, memberClass)
  }

  override fun from(variableMap: VariableMap): ReadAdapter<List<T>> {
    return ListReadWriteAdapterVariableMap(variableMap, name, memberClass)
  }

  override fun on(runtimeService: RuntimeService, executionId: String): WriteAdapter<List<T>> {
    return ListReadWriteAdapterRuntimeService(
      runtimeService,
      executionId,
      name,
      memberClass
    )
  }

  override fun from(runtimeService: RuntimeService, executionId: String): ReadAdapter<List<T>> {
    return ListReadWriteAdapterRuntimeService(
      runtimeService,
      executionId,
      name,
      memberClass
    )
  }

  override fun on(taskService: TaskService, taskId: String): WriteAdapter<List<T>> {
    return ListReadWriteAdapterTaskService(
      taskService,
      taskId,
      name,
      memberClass
    )
  }

  override fun from(taskService: TaskService, taskId: String): ReadAdapter<List<T>> {
    return ListReadWriteAdapterTaskService(
      taskService,
      taskId,
      name,
      memberClass
    )
  }

  override fun on(caseService: CaseService, caseExecutionId: String): WriteAdapter<List<T>> {
    return ListReadWriteAdapterCaseService(
      caseService,
      caseExecutionId,
      name,
      memberClass
    )
  }

  override fun from(caseService: CaseService, caseExecutionId: String): ReadAdapter<List<T>> {
    return ListReadWriteAdapterCaseService(
      caseService,
      caseExecutionId,
      name,
      memberClass
    )
  }

  override fun from(lockedExternalTask: LockedExternalTask): ReadAdapter<List<T>> {
    return ListReadAdapterLockedExternalTask(
      lockedExternalTask,
      name,
      memberClass
    )
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    val that = other as ListVariableFactory<*>
    return name == that.name && memberClass == that.memberClass
  }

  override fun hashCode(): Int {
    return Objects.hash(name, memberClass)
  }

  override fun toString(): String {
    return "ListVariableFactory{" +
      "name='" + name + '\'' +
      ", memberClazz=" + memberClass +
      '}'
  }
}
