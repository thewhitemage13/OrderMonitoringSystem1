package org.thewhitemage13.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.thewhitemage13.OrderCreatedEvent;
import org.thewhitemage13.entity.Order;
import org.thewhitemage13.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<Long, OrderCreatedEvent> kafkaTemplate;

    public OrderService(OrderRepository orderRepository, KafkaTemplate<Long, OrderCreatedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createOrder(Order order) {
        OrderCreatedEvent event =
                new OrderCreatedEvent(
                        order.getId(),
                        order.getUserId(),
                        order.getItems(),
                        order.getAddress(),
                        order.getStatus(),
                        order.getCreatedAt(),
                        order.getUpdatedAt()
                );

        kafkaTemplate.send("order.created", order.getId(), event);
        order.setStatus("CREATED");
        orderRepository.save(order);
    }

    public void updateOrderStatus(Long orderId, String status) {
        Order updateOrder = orderRepository.findById(orderId).get();

        updateOrder.setStatus(status);

        OrderCreatedEvent event =
                new OrderCreatedEvent(
                        updateOrder.getId(),
                        updateOrder.getUserId(),
                        updateOrder.getItems(),
                        updateOrder.getAddress(),
                        updateOrder.getStatus(),
                        updateOrder.getCreatedAt(),
                        updateOrder.getUpdatedAt()
                );

        kafkaTemplate.send("order.updated", orderId, event);
        orderRepository.save(updateOrder);
    }
}
