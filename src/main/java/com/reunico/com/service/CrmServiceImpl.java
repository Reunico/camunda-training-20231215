package com.reunico.com.service;

import com.reunico.com.config.ApplicationProperties;
import com.reunico.com.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CrmServiceImpl implements CrmService {

    private final RestTemplate restTemplate;
    private final ApplicationProperties applicationProperties;

    @Override
    public void saveOrder(Order order) {
        restTemplate.postForEntity(applicationProperties.getCrmUrl() + "/" + order.getId(), order, Order.class);
    }
}
