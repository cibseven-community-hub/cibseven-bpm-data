package org.cibseven.community.bpm.data.example.process;

import org.cibseven.bpm.engine.RuntimeService;
import org.cibseven.bpm.engine.runtime.ProcessInstance;
import org.cibseven.bpm.engine.variable.VariableMap;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.cibseven.bpm.engine.variable.Variables.createVariables;
import static org.cibseven.community.bpm.data.example.process.OrderApproval.ORDER_ID;

/**
 * Factory to create instance factory.
 */
@Component
public class OrderApprovalInstanceFactory {

  /**
   * Runtime service to access CIB seven API.
   */
  private final RuntimeService runtimeService;

  /**
   * Constructs the factory.
   *
   * @param runtimeService runtime service to use.
   */
  public OrderApprovalInstanceFactory(RuntimeService runtimeService) {
    this.runtimeService = runtimeService;
  }

  /**
   * Start new approval process.
   *
   * @param orderId id of an order.
   * @return instance supplier.
   */
  public OrderApprovalInstance start(String orderId) {
    VariableMap vars = createVariables();
    ORDER_ID.on(vars).set(orderId);
    ProcessInstance instance = runtimeService.startProcessInstanceByKey(OrderApproval.KEY, "order-" + UUID.randomUUID().toString(), vars);
    return new OrderApprovalInstance(instance);
  }

}
