package org.cibseven.community.bpm.data.example.kotlin.process

import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.booleanVariable
import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.stringVariable
import org.cibseven.community.bpm.data.CamundaBpmDataKotlin.customVariable
import org.cibseven.community.bpm.data.example.kotlin.domain.Order
import org.cibseven.community.bpm.data.example.kotlin.domain.OrderPosition
import org.cibseven.community.bpm.data.example.kotlin.domain.OrderRepository
import org.cibseven.community.bpm.data.example.kotlin.process.OrderApproval.Variables.ORDER
import org.cibseven.community.bpm.data.example.kotlin.process.OrderApproval.Variables.ORDER_APPROVED
import org.cibseven.community.bpm.data.example.kotlin.process.OrderApproval.Variables.ORDER_ID
import org.cibseven.community.bpm.data.example.kotlin.process.OrderApproval.Variables.ORDER_POSITION
import org.cibseven.community.bpm.data.example.kotlin.process.OrderApproval.Variables.ORDER_TOTAL
import org.cibseven.community.bpm.data.factory.VariableFactory
import org.cibseven.community.bpm.data.guard.VariablesGuard
import org.cibseven.community.bpm.data.guard.condition.exists
import org.cibseven.community.bpm.data.guard.condition.matches
import org.cibseven.community.bpm.data.guard.integration.DefaultGuardTaskListener
import mu.KLogging
import org.cibseven.bpm.engine.delegate.DelegateExecution
import org.cibseven.bpm.engine.delegate.DelegateTask
import org.cibseven.bpm.engine.delegate.ExecutionListener
import org.cibseven.bpm.engine.delegate.JavaDelegate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import java.math.BigDecimal
import java.util.*

/**
 * Backing bean.
 */
@Configuration
class OrderApproval {

  @Autowired
  lateinit var orderRepository: OrderRepository

  companion object : KLogging() {
    const val KEY = "order-approval"
  }

  object Variables {
    val ORDER_ID = stringVariable("orderId").nonNull
    val ORDER_APPROVED = booleanVariable("orderApproved").nonNull
    val ORDER: VariableFactory<Order> = customVariable("order")
    val ORDER_POSITION: VariableFactory<OrderPosition> = customVariable("orderPosition")
    val ORDER_TOTAL: VariableFactory<BigDecimal> = customVariable("orderTotal")
  }

  /**
   * Load a primitive variable by id (string) and store a complex variable (order).
   */
  @Bean
  fun loadOrder() = JavaDelegate { execution ->
    val orderId = ORDER_ID.from(execution).get()
    val order = orderRepository.loadOrder(orderId)
    ORDER.on(execution).set(order)
    ORDER_TOTAL.on(execution).set(BigDecimal.ZERO)
  }

  /**
   * Load a local order position, write a local variable.
   */
  @Bean
  fun calculateOrderPositions() = JavaDelegate { execution ->
    val orderPosition = ORDER_POSITION.from(execution).get()

    ORDER_TOTAL.on(execution).update { total -> total.plus(orderPosition.netCost.times(BigDecimal.valueOf(orderPosition.amount))) }
  }

  /**
   * Read a local variable and store it in global variable.
   */
  @Bean
  fun writeOrderTotal() = ExecutionListener { execution ->
    val total = ORDER_TOTAL.from(execution).get()
    ORDER_TOTAL.on(execution).set(total)
  }

  /**
   * Checks that the variable "orderApproved" exists and is true.
   * Used as task listener on complete of user task in BPMN ${taskExecutionListener}
   *
   * @return task listener.
   */
  @Bean
  fun guardTaskListener() = DefaultGuardTaskListener(
    VariablesGuard(listOf(
      ORDER_APPROVED.exists(),
      ORDER_APPROVED.matches(this::isTrueViolationMessageSupplier) { it == true }
    )), true
  )

  private fun isTrueViolationMessageSupplier(variableFactory: VariableFactory<Boolean>, localLabel: String, option: Optional<Boolean>) =
    "Expecting$localLabel variable '${variableFactory.name}' to be true, but its value '${option.get()}' has not."

  /**
   * Log the task id.
   */
  @EventListener(condition = "#task != null && #task.eventName == 'create'")
  fun taskLogger(task: DelegateTask) {
    logger.info("TASK LOGGER: Created user task ${task.id}")
  }

  @EventListener(condition = "#execution != null && #execution.eventName == 'start' && #execution.currentActivityId == 'start_order_created'")
  fun processStartLogger(execution: DelegateExecution) {
    logger.info { "INSTANCE LOGGER: Started process instance ${execution.processInstanceId}" }
  }

  @EventListener(condition = "#execution != null && #execution.eventName == 'end' && #execution.currentActivityId == 'end_order_approved'")
  fun processEndLogger(execution: DelegateExecution) {
    logger.info { "INSTANCE LOGGER: Finished process instance ${execution.processInstanceId}" }
  }

}
