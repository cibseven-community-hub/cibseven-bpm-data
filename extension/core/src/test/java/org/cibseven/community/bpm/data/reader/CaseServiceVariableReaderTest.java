package org.cibseven.community.bpm.data.reader;

import org.cibseven.bpm.engine.CaseService;
import org.cibseven.community.bpm.data.CamundaBpmData;
import org.cibseven.community.bpm.data.factory.VariableFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cibseven.community.bpm.data.CamundaBpmData.stringVariable;
import static org.mockito.Mockito.when;

public class CaseServiceVariableReaderTest {

  private static final VariableFactory<String> STRING = stringVariable("myString");
  private final CaseService caseService = Mockito.mock(CaseService.class);
  private final String taskId = UUID.randomUUID().toString();
  private final String value = "value";
  private final String localValue = "localValue";

  @BeforeEach
  public void mockExecution() {
    when(caseService.getVariable(this.taskId, STRING.getName())).thenReturn(value);
    when(caseService.getVariableLocal(this.taskId, STRING.getName())).thenReturn(localValue);
  }

  @AfterEach
  public void after() {
    Mockito.reset(caseService);
  }

  @Test
  public void shouldDelegateGet() {
    assertThat(CamundaBpmData.reader(caseService, taskId).get(STRING)).isEqualTo(value);
  }

  @Test
  public void shouldDelegateGetOptional() {
    assertThat(CamundaBpmData.reader(caseService, taskId).getOptional(STRING)).hasValue(value);
    assertThat(CamundaBpmData.reader(caseService, taskId).getOptional(stringVariable("xxx"))).isEmpty();
  }

  @Test
  public void shouldDelegateGetLocalOptional() {
    assertThat(CamundaBpmData.reader(caseService, taskId).getLocalOptional(STRING)).hasValue(localValue);
    assertThat(CamundaBpmData.reader(caseService, taskId).getLocalOptional(stringVariable("xxx"))).isEmpty();
  }

  @Test
  public void shouldDelegateGetLocal() {
    assertThat(CamundaBpmData.reader(caseService, taskId).getLocal(STRING)).isEqualTo(localValue);
  }
}
