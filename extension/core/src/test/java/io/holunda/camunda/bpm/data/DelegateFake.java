package io.holunda.camunda.bpm.data;

import org.cibseven.bpm.engine.ProcessEngine;
import org.cibseven.bpm.engine.ProcessEngineServices;
import org.cibseven.bpm.engine.delegate.ProcessEngineServicesAware;

/**
 * Copy of the fake to avoid dependency to camunda-platform-7-mockito (to avoid cyclic dependency graph).
 */
@SuppressWarnings({"rawtypes", "unchecked"})
abstract class DelegateFake<T extends DelegateFake> extends VariableScopeFake<T> implements ProcessEngineServicesAware {


  @Override
  public ProcessEngineServices getProcessEngineServices() {
    return null;
  }

  public T withProcessEngineServices(ProcessEngineServices processEngineServices) {
    return (T) this;
  }


  @Override
  public ProcessEngine getProcessEngine() {
    return null;
  }

  public T withProcessEngine(ProcessEngine processEngine) {
    return (T) this;
  }

}
