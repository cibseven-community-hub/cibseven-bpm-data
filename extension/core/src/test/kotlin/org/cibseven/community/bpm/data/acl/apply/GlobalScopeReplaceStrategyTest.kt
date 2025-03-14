package org.cibseven.community.bpm.data.acl.apply

import org.cibseven.community.bpm.data.CamundaBpmData
import org.cibseven.community.bpm.data.CamundaBpmData.stringVariable
import org.assertj.core.api.Assertions.assertThat
import org.cibseven.bpm.engine.delegate.DelegateExecution
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class GlobalScopeReplaceStrategyTest {

  private val FOO = stringVariable("foo")

  @Test
  fun `should apply global`() {
    val variables = CamundaBpmData.builder().set(FOO, "bar").build()
    val executionMock = Mockito.mock(DelegateExecution::class.java)

    GlobalScopeReplaceStrategy.apply(variables, executionMock)

    verify(executionMock, Mockito.never()).variablesLocal = Mockito.any()
    verify(executionMock).variables = variables
    verifyNoMoreInteractions(executionMock)

    assertThat(GlobalScopeReplaceStrategy.toString()).isEqualTo(GlobalScopeReplaceStrategy::class.java.canonicalName)
  }
}
