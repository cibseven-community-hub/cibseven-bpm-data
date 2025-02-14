package org.cibseven.community.bpm.data.example.kotlin.process

import org.cibseven.community.bpm.data.builder.VariableMapBuilder
import org.cibseven.community.bpm.data.example.kotlin.process.OrderApproval.Variables.ORDER_ID
import org.cibseven.bpm.engine.RuntimeService
import org.springframework.stereotype.Component
import java.util.*

/**
 * Instance factory.
 */
@Component
class OrderApprovalInstanceFactory(
  private val runtimeService: RuntimeService
) {

  /**
   * Starts the approval process.
   */
  fun start(id: String): OrderApprovalInstance {
    val vars = VariableMapBuilder().set(ORDER_ID, id).build()
    val instance = runtimeService.startProcessInstanceByKey(OrderApproval.KEY, "order-${UUID.randomUUID()}", vars)
    return OrderApprovalInstance(instance)
  }

}
