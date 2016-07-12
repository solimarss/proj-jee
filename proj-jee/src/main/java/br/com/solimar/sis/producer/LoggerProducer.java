package br.com.solimar.sis.producer;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {
	//@Produces
	public Logger produceLoger(InjectionPoint injectionPoint) {

		Logger logger = Logger.getLogger(injectionPoint.getMember()
				.getDeclaringClass().getName());
		/*ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.FINER);
		logger.addHandler(handler);*/

		return logger;
	}
}