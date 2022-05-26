package com.samsungsds.consumer.receiver;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface IotEventReceiver {

	String INPUT = "iot.event";

	@Input(IotEventReceiver.INPUT)
	SubscribableChannel input();
}
