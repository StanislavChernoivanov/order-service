package com.example.order_service.controller;

import com.example.order_service.model.OrderEvent;
import com.example.order_service.service.OrderEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderEvent")
@RequiredArgsConstructor
public class OrderEventController {

    private final OrderEventService orderEventService;

    @PostMapping("/sendEvent")
    public ResponseEntity<OrderEvent> sendEvent(@RequestBody @Valid OrderEvent orderEvent) {
        orderEventService.sendOrderEvent(orderEvent);
        return ResponseEntity.ok(orderEvent);
    }
}
