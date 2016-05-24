//import static org.mockito.Mockito.when;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import pl.jcommerce.moonshine.model.thermometer.Thermometer;
//import pl.jcommerce.moonshine.model.thermometer.ThermometerMeasurement;
//import pl.jcommerce.moonshine.repository.ThermometerRepository;
//import pl.jcommerce.moonshine.service.ThermometerService;
//
//@RunWith(MockitoJUnitRunner.class)  
//public class ThermometerServiceTest {
//	
//	
//	@Autowired
//	ThermometerRepository repository;
//	
//	@Autowired
//	ThermometerService service;
//	
//	@Mock
//	WebApplicationContext wac;
//	
//	@Mock
//	Thermometer thermometer;
//	
//	@Mock
//	List<ThermometerMeasurement> thermometerMeasurement;
//	
//	MockMvc mockMvc;
//	
//	@Before
//	public void setUp() {
//		
//		thermometer=Mockito.mock(Thermometer.class);
//	}
//	
//
//}
