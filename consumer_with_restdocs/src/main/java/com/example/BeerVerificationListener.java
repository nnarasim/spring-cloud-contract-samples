package com.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Marcin Grzejszczak
 */
@Component
class BeerVerificationListener {

	private static final Log log = LogFactory.getLog(BeerVerificationListener.class);

	AtomicInteger eligibleCounter = new AtomicInteger();
	AtomicInteger notEligibleCounter = new AtomicInteger();

	@StreamListener(Sink.INPUT)
	public void listen(Verification verification) {
		log.info("Received new verification");
		if (verification.eligible) {
			eligibleCounter.incrementAndGet();
		} else {
			notEligibleCounter.incrementAndGet();
		}
	}

	public static class Verification {
		public boolean eligible;
	}

	public enum Result {
		ACCEPTED, REJECTED
	}
}
