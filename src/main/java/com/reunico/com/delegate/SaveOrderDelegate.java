package com.reunico.com.delegate;

import com.reunico.com.constant.ProcessVariableConstant;
import com.reunico.com.model.Order;
import com.reunico.com.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("saveOrder")
@RequiredArgsConstructor
public class SaveOrderDelegate implements JavaDelegate {

    private final OrderService orderService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Order order = orderService.save((Order) execution.getVariable(ProcessVariableConstant.ORDER));
        execution.setVariable(ProcessVariableConstant.ORDER, order);
        execution.setProcessBusinessKey(order.getId().toString());
    }
}
