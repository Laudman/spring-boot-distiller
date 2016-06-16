package pl.jcommerce.moonshine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.jcommerce.moonshine.repository.MeasurementRepository;

/**
 * Provides method for real-time chart update
 * 
 * @author wipo
 *
 */

@Component
@EnableScheduling
public class MeasurementSender implements ApplicationListener<BrokerAvailabilityEvent> {

	private final MessageSendingOperations<String> messagingTemplate;

	@Autowired
	private MeasurementRepository measurementRepository;

	@Autowired
	public MeasurementSender(final MessageSendingOperations<String> messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@Override
	public void onApplicationEvent(final BrokerAvailabilityEvent event) {
	}

	/**
	 * Each 10s sends to websocket subscribers map with thermometer key and it's
	 * last measurement. Used to update real-time chart.
	 */
	@Scheduled(initialDelay = 2000, fixedDelay = 10000)
	public void sendLastMeasurementsForAllThermometers() {
		this.messagingTemplate.convertAndSend("/hello", measurementRepository.findLatestMeasurementForAllThermometers());
	}
}