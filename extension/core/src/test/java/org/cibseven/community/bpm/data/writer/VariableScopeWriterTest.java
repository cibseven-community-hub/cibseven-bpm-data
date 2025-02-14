package org.cibseven.community.bpm.data.writer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cibseven.community.bpm.data.CamundaBpmData.stringVariable;

import org.cibseven.community.bpm.data.CamundaBpmData;
import org.cibseven.community.bpm.data.DelegateExecutionFake;
import org.cibseven.community.bpm.data.factory.VariableFactory;

public class VariableScopeWriterTest {

  private static final VariableFactory<String> STRING = stringVariable("myString");

  @Test
  public void testSet() {
    DelegateExecutionFake execution = DelegateExecutionFake.of().withId("4711");

    CamundaBpmData
      .writer(execution)
      .set(STRING, "value")
      .variables();
    assertThat(execution.getVariable(STRING.getName())).isEqualTo("value");
  }

  @Test
  public void testSetLocal() {
    DelegateExecutionFake execution = DelegateExecutionFake.of().withId("4711");
    CamundaBpmData
      .writer(execution)
      .setLocal(STRING, "value");
    assertThat(execution.getVariableLocal(STRING.getName())).isEqualTo("value");
  }

  @Test
  public void testRemove() {
    DelegateExecutionFake execution = DelegateExecutionFake.of().withId("4711");
    STRING.on(execution).set("value");
    CamundaBpmData.writer(execution)
                  .remove(STRING);
    assertThat(execution.getVariableNames()).isEmpty();
  }

  @Test
  public void testRemoveLocal() {
    DelegateExecutionFake execution = DelegateExecutionFake.of().withId("4711");
    STRING.on(execution).setLocal("value");
    CamundaBpmData
      .writer(execution)
      .removeLocal(STRING);
    assertThat(execution.getVariableNames()).isEmpty();
  }

}
