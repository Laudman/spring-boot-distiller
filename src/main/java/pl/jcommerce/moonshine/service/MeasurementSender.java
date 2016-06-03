package pl.jcommerce.moonshine.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.jcommerce.moonshine.model.Measurement;
import pl.jcommerce.moonshine.repository.MeasurementRepository;

@Component
public class MeasurementSender implements ApplicationListener<BrokerAvailabilityEvent> {

	private final MessageSendingOperations<String> messagingTemplate;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private MeasurementRepository repository;

	@Autowired
	public MeasurementSender(final MessageSendingOperations<String> messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@Override
	public void onApplicationEvent(final BrokerAvailabilityEvent event) {
	}

	@Scheduled(fixedDelay = 1000)
	public void sendDataUpdates() {

//		this.messagingTemplate.convertAndSend("/hello", message);
	}
}