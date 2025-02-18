package org.cibseven.community.bpm.data.writer;

import org.cibseven.bpm.engine.variable.VariableMap;
import org.cibseven.bpm.engine.variable.Variables;
import org.cibseven.community.bpm.data.CamundaBpmData;
import org.cibseven.community.bpm.data.factory.VariableFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VariableMapWriterTest {

  private static final VariableFactory<String> STRING = CamundaBpmData.stringVariable("myString");

  private final VariableMap variables = Variables.createVariables();

  @Test
  public void testSet() {
    CamundaBpmData.writer(variables)
      .set(STRING, "value");
    assertThat(variables.get(STRING.getName())).isEqualTo("value");
  }

  @Test
  public void testRemove() {
    STRING.on(variables).set("value");
    CamundaBpmData.writer(variables)
      .remove(STRING);
    assertThat(variables).isEmpty();
  }

}
