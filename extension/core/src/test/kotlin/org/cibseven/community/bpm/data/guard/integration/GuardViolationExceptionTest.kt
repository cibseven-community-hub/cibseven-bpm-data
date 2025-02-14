package org.cibseven.community.bpm.data.guard.integration

import org.cibseven.community.bpm.data.CamundaBpmData
import org.cibseven.community.bpm.data.guard.CamundaBpmDataGuards
import org.cibseven.community.bpm.data.guard.GuardViolation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class GuardViolationExceptionTest {

  val FOO = CamundaBpmData.stringVariable("foo")
  val c1 = CamundaBpmDataGuards.exists(FOO)
  val c2 = CamundaBpmDataGuards.hasValue(FOO, "bar")

  @Test
  fun buildMessage() {

    val expected = "reason\n\tnot exists,\n\twrong VAL"
    val ex = GuardViolationException(reason = "reason", violations = listOf(
      GuardViolation(c1, Optional.empty(), "not exists"),
      GuardViolation(c2, Optional.of("VAL"), "wrong VAL")
    ))

    assertThat(ex.message).isEqualTo(expected)
  }
}
