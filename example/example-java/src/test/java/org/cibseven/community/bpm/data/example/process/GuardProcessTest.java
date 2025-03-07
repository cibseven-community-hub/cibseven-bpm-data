package org.cibseven.community.bpm.data.example.process;

import org.cibseven.bpm.engine.ProcessEngineException;
import org.cibseven.bpm.engine.delegate.JavaDelegate;
import org.cibseven.bpm.engine.runtime.Job;
import org.cibseven.bpm.engine.task.Task;
import org.cibseven.bpm.engine.test.Deployment;
import org.cibseven.bpm.engine.test.junit5.ProcessEngineExtension;
import org.cibseven.bpm.engine.test.mock.Mocks;
import org.cibseven.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration;
import org.cibseven.community.bpm.data.example.domain.Order;
import org.cibseven.community.bpm.data.example.process.OrderApproval;
import org.cibseven.community.bpm.data.example.process.OrderApprovalInstanceFactory;
import org.cibseven.community.bpm.data.guard.integration.GuardViolationException;
import org.cibseven.spin.plugin.impl.SpinProcessEnginePlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@Deployment(resources = "order_approval.bpmn")
public class GuardProcessTest {

  @RegisterExtension
  public static final ProcessEngineExtension engine = ProcessEngineExtension
    .builder()
    .useProcessEngine(new StandaloneInMemoryTestConfiguration(new SpinProcessEnginePlugin()).buildProcessEngine())
    .build();


  @BeforeEach
  public void register() {
    OrderApproval config = new OrderApproval();
    MockOrderApproval mockOrderApproval = new MockOrderApproval();
    Mocks.register("guardExecutionListener", config.guardExecutionListener());
    Mocks.register("guardTaskListener", config.guardTaskListener());
    Mocks.register("loadOrder", mockOrderApproval.loadOrder());
    Mocks.register("calculateOrderPositions", mockOrderApproval.calculateOrderPositions());
  }

  static class MockOrderApproval {
    public JavaDelegate loadOrder() {
      return execution -> {
        OrderApproval.ORDER.on(execution).set(new Order("1", Date.from(Instant.now()), new ArrayList<>()));
      };
    }

    public JavaDelegate calculateOrderPositions() {
      return mock(JavaDelegate.class);
    }
  }

  @Test
  public void shouldFireExceptionIfOrderIdIsMissing() {

    assertThrows(
      GuardViolationException.class,
      // manual start by-passing the factory
      () -> engine.getRuntimeService().startProcessInstanceByKey(OrderApproval.KEY),
      "Guard violated by execution '6' in activity 'Order created'\nExpecting variable 'orderId' to be set, but it was not found.\n");

  }

  @Test
  public void shouldFireExceptionApproveDecisionIsMissing() {

    OrderApprovalInstanceFactory factory = new OrderApprovalInstanceFactory(engine.getRuntimeService());
    factory.start("1");

    // async after start
    Job asyncStart = engine.getManagementService().createJobQuery().singleResult();
    engine.getManagementService().executeJob(asyncStart.getId());
    Task task = engine.getTaskService().createTaskQuery().singleResult();

    assertThrows(
      ProcessEngineException.class,
      () -> engine.getTaskService().complete(task.getId()),
      "Guard violated in task 'Approve order' (taskId: '21')\nExpecting variable 'orderApproved' to be set, but it was not found.\n"
    );
  }

}
