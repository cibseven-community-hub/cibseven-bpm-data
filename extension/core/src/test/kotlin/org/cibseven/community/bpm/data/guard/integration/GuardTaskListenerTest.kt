package org.cibseven.community.bpm.data.guard.integration

import org.cibseven.community.bpm.data.CamundaBpmData.stringVariable
import org.cibseven.community.bpm.data.DelegateTaskFake
import org.cibseven.community.bpm.data.guard.VariablesGuard
import org.cibseven.community.bpm.data.guard.condition.exists
import org.cibseven.community.bpm.data.guard.condition.hasValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

val ORDER_ID = stringVariable("orderID")

/**
 * Test of listener behavior.
 */
class GuardTaskListenerTest {

  @Test
  fun `should do nothing because no guard violation`() {
    val delegateTask = DelegateTaskFake().withId("4711")
    ORDER_ID.on(delegateTask).set("1")

    val listener = createListener(true)
    listener.notify(delegateTask)

    // nothing to do here
    assertThat(true).isTrue
  }

  @Test
  fun `should not throw exception on violation if disabled `() {
    val delegateTask = DelegateTaskFake().withId("4711").withName("task name")
    ORDER_ID.on(delegateTask).set("2")

    val listener = createListener(false)
    listener.notify(delegateTask)

    // nothing to do here
    assertThat(true).isTrue
  }

  @Test
  fun `should throw exception on violation if enabled `() {
    val delegateTask = DelegateTaskFake().withId("4711").withName("task name")
    ORDER_ID.on(delegateTask).set("2")
    val listener = createListener(true)

    assertThrows<GuardViolationException>(
      "Guard violated in task '${delegateTask.name}' (taskId: '${delegateTask.id}')",
    ) {
      listener.notify(delegateTask)
    }
  }

  @Test
  fun `should print name of named guard`() {
    val delegateTask = DelegateTaskFake().withId("4711").withName("task name")

    val listener = DefaultGuardTaskListener(VariablesGuard("NamedGuard", listOf(ORDER_ID.exists())))
    val exception = assertThrows<GuardViolationException>() {
      listener.notify(delegateTask)
    }
    assertThat(exception.message).startsWith("NamedGuard")
  }

  private fun createListener(throwE: Boolean = true) = DefaultGuardTaskListener(VariablesGuard(listOf(ORDER_ID.hasValue("1"))), throwE)
}
