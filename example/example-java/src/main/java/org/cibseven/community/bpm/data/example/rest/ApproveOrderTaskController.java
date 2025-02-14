package org.cibseven.community.bpm.data.example.rest;

import org.cibseven.bpm.engine.TaskService;
import org.cibseven.bpm.engine.variable.VariableMap;
import org.cibseven.community.bpm.data.example.domain.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.cibseven.community.bpm.data.CamundaBpmData.builder;
import static org.cibseven.community.bpm.data.example.process.OrderApproval.*;

@RestController
@RequestMapping("/task/approve-order")
public class ApproveOrderTaskController {

  private final TaskService taskService;

  public ApproveOrderTaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("/{taskId}")
  public ResponseEntity<ApproveTaskDto> loadTask(@PathVariable("taskId") String taskId) {
    final Order order = ORDER.from(taskService, taskId).get();
    final BigDecimal orderTotal = ORDER_TOTAL.from(taskService, taskId).get();
    return ResponseEntity.ok(new ApproveTaskDto(order, orderTotal));
  }

  @PostMapping("/{taskId}")
  public ResponseEntity<Void> completeTask(@PathVariable("taskId") String taskId, @RequestBody ApproveTaskCompleteDto userInput) {
    VariableMap vars = builder()
      .set(ORDER_APPROVED, userInput.getApproved())
      .build();
    taskService.complete(taskId, vars);
    return ResponseEntity.noContent().build();
  }
}
