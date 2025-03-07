package org.cibseven.community.bpm.data.example.process;

import org.cibseven.bpm.engine.runtime.ProcessInstance;

import java.util.function.Supplier;

/**
 * Order Approval process instance supplier.
 */
public class OrderApprovalInstance implements Supplier<ProcessInstance> {

  /**
   * Underlying instance.
   */
  private final ProcessInstance instance;

  /**
   * Creates the supplier.
   *
   * @param instance instance.
   */
  public OrderApprovalInstance(ProcessInstance instance) {
    this.instance = instance;
  }

  /**
   * Retrieval of the instance.
   *
   * @return instance.
   */
  public ProcessInstance get() {
    return instance;
  }
}
