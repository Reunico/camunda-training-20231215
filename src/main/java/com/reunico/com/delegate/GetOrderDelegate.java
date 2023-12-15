package com.reunico.com.delegate;

import com.reunico.com.constant.ProcessVariableConstant;
import com.reunico.com.service.WebsiteService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("getOrder")
@RequiredArgsConstructor
public class GetOrderDelegate implements JavaDelegate {

    private final WebsiteService websiteService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        execution.setVariable(ProcessVariableConstant.ORDER, websiteService.getOrder());
    }
}
