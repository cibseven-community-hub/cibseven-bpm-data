package org.cibseven.community.bpm.data.factory

import org.cibseven.community.bpm.data.CamundaBpmData
import org.assertj.core.api.Assertions
import org.cibseven.bpm.engine.variable.Variables
import org.junit.jupiter.api.Test
import java.util.*

class UUIDVariableFactoryTest {

  private val variables = Variables.createVariables()

  @Test
  fun readWriteUuid() {
    val anUuid = UUID.randomUUID()
    val uuidVariable = CamundaBpmData.uuidVariable("myUuid")

    uuidVariable.on(variables).set(anUuid)
    val result: UUID = uuidVariable.from(variables).get()
    Assertions.assertThat(result).isEqualTo(anUuid)
  }
}
