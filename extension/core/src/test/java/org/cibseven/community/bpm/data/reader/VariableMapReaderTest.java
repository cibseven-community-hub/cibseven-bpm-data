package org.cibseven.community.bpm.data.reader;

import static org.cibseven.community.bpm.data.CamundaBpmData.stringVariable;

import org.cibseven.bpm.engine.variable.VariableMap;
import org.cibseven.community.bpm.data.CamundaBpmData;
import org.cibseven.community.bpm.data.factory.VariableFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class VariableMapReaderTest {

  private static final VariableFactory<String> STRING = stringVariable("myString");

  private final String value = "value";

  private final VariableMap variableMap = CamundaBpmData.builder().set(STRING, value).build();

  @Test
  public void shouldDelegateGet() {
    assertThat(CamundaBpmData.reader(variableMap).get(STRING)).isEqualTo(value);
  }

  @Test
  public void shouldDelegateGetOptional() {
    assertThat(CamundaBpmData.reader(variableMap).getOptional(STRING)).hasValue(value);
    assertThat(CamundaBpmData.reader(variableMap).getOptional(stringVariable("xxx"))).isEmpty();
  }

  @Test
  public void shouldDelegateGetLocalOptional() {
    assertThatThrownBy(() -> CamundaBpmData.reader(variableMap).getLocalOptional(STRING)).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void shouldDelegateGetLocal() {
    assertThatThrownBy(() -> CamundaBpmData.reader(variableMap).getLocalOptional(STRING)).isInstanceOf(UnsupportedOperationException.class);
  }
}
