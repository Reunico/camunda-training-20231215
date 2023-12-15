package com.reunico.com;

import com.reunico.com.constant.ProcessVariableConstant;
import com.reunico.com.model.Order;
import com.reunico.com.service.CrmService;
import com.reunico.com.service.OrderService;
import com.reunico.com.service.WebsiteService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.camunda.community.process_test_coverage.spring_test.platform7.ProcessEngineCoverageConfiguration;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import(ProcessEngineCoverageConfiguration.class)
public class WorkflowTest {

  @Autowired
  public RuntimeService runtimeService;

  @MockBean
  public CrmService crmService;

  @MockBean
  public WebsiteService websiteService;

  @MockBean
  public OrderService orderService;

  @Before
  public void setup() {
    Order order = createOrder();
    Mockito.when(websiteService.getOrder()).thenReturn(order);
    Mockito.when(orderService.save(any())).thenReturn(order);
    Mockito.doNothing().when(crmService).saveOrder(order);
  }

  Order createOrder() {
    Order order = new Order();
    order.setId(UUID.randomUUID());
    order.setAmount(BigDecimal.valueOf(300));
    order.setNumber(999L);
    order.setFullName("Test Testovich");
    order.setTitle("Developer");
    order.setCountryCode("DE");
    return order;
  }

  @Test
  public void shouldExecuteHappyPath() {
    // given
    String processDefinitionKey = "orderHandle";

    // when
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

    // then
    assertThat(processInstance).isStarted();
    assertThat(processInstance).isWaitingAt("Event_1pcws42");
    execute(job());
    assertThat(processInstance).isWaitingAt("getOrder");
    execute(job());
    assertThat(processInstance).isWaitingAt("handleOrder");
    assertThat(processInstance).hasVariables(ProcessVariableConstant.ORDER);
    complete(task(), withVariables(ProcessVariableConstant.IS_APPROVED, true));
    execute(job());
    assertThat(processInstance).isEnded();

  }

}
