package org.cibseven.community.bpm.data.example;

import org.cibseven.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.cibseven.bpm.spring.boot.starter.event.PostDeployEvent;
import org.cibseven.community.bpm.data.example.process.OrderApprovalInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableProcessApplication
public class CamundaBpmDataProcessApplication {

  @Autowired
  private OrderApprovalInstanceFactory orderApprovalInstanceFactory;

  @EventListener
  public void onDeploy(PostDeployEvent event) {
    orderApprovalInstanceFactory.start("1");
  }

  public static void main(String[] args) {
    SpringApplication.run(CamundaBpmDataProcessApplication.class, args);
  }
}
