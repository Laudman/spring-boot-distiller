package pl.jcommerce.moonshine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.jcommerce.moonshine.model.Thermometer;

@Component
public class WebSocketDataGenerator implements ApplicationListener<BrokerAvailabilityEvent> {

	private final MessageSendingOperations<String> messagingTemplate;

	@Autowired
	private ThermometerService service;

	@Autowired
	public WebSocketDataGenerator(final MessageSendingOperations<String> messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@Override
	public void onApplicationEvent(final BrokerAvailabilityEvent event) {
	}

	@Scheduled(fixedDelay = 1000)
    public void sendDataUpdates() {
    	Thermometer thermometer = service.getChoosenTermometer();
    	
		this.messagingTemplate.convertAndSend(
	            "/hello", thermometer.getTemperature());
//	        System.out.println("FIRST");   
    }
}