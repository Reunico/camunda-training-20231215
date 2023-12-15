package com.reunico.com.delegate;

import com.reunico.com.constant.ProcessVariableConstant;
import com.reunico.com.model.Order;
import com.reunico.com.service.CrmService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("saveCrm")
@RequiredArgsConstructor
public class SaveCrmDelegate implements JavaDelegate {

    private final CrmService crmService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        crmService.saveOrder((Order) execution.getVariable(ProcessVariableConstant.ORDER));
    }
}
