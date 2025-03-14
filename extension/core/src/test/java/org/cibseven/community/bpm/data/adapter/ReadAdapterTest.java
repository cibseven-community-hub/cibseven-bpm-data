package org.cibseven.community.bpm.data.adapter;

import org.cibseven.bpm.engine.RuntimeService;
import org.cibseven.bpm.engine.variable.Variables;
import org.cibseven.community.bpm.data.CamundaBpmData;
import org.cibseven.community.bpm.data.factory.VariableFactory;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.cibseven.community.bpm.data.CamundaBpmData.stringVariable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReadAdapterTest {

  private static final VariableFactory<String> FOO = stringVariable("foo");
  private static final String EXECUTION_ID = UUID.randomUUID().toString();

  private final RuntimeService runtimeService = mock(RuntimeService.class);

  @Test
  public void getOrDefault_returns_default_value_for_missing_variable() {
    assertThat(FOO.from(Variables.createVariables()).getOrDefault("bar")).isEqualTo("bar");
  }

  @Test
  public void getOrDefault_returns_value_although_default_is_given() {
    assertThat(FOO.from(CamundaBpmData.builder().set(FOO, "hello").build()).getOrDefault("bar")).isEqualTo("hello");
  }

  @Test
  public void getOrNull_returns_null_for_missing_variable() {
    assertThat(FOO.from(Variables.createVariables()).getOrNull()).isNull();
  }

  @Test
  public void getOrNull_returns_value_if_exists() {
    assertThat(FOO.from(CamundaBpmData.builder().set(FOO, "hello").build()).getOrNull()).isEqualTo("hello");
  }

  @Test
  public void getLocalOrDefault_returns_default_value_for_missing_variable() {
    localFooVariable(null);
    assertThat(FOO.from(runtimeService, EXECUTION_ID).getLocalOrDefault("bar")).isEqualTo("bar");
  }

  @Test
  public void getLocalOrDefault_returns_value_although_default_is_given() {
    localFooVariable("hello");
    assertThat(FOO.from(runtimeService, EXECUTION_ID).getLocalOrDefault("bar")).isEqualTo("hello");
  }

  @Test
  public void getLocalOrNull_returns_null_for_missing_variable() {
    localFooVariable(null);
    assertThat(FOO.from(runtimeService, EXECUTION_ID).getLocalOrNull()).isNull();
  }

  @Test
  public void getLocalOrNull_returns_value_if_exists() {
    localFooVariable("hello");
    assertThat(FOO.from(runtimeService, EXECUTION_ID).getLocalOrNull()).isEqualTo("hello");
  }

  private void localFooVariable(String value) {
    when(runtimeService.getVariableLocal(EXECUTION_ID, FOO.getName())).thenReturn(value);
  }
}
