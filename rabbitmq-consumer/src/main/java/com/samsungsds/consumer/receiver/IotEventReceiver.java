package com.samsungsds.consumer.receiver;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface IotEventReceiver {

	String INPUT1 = "input1";

	@Input(IotEventReceiver.INPUT1)
	SubscribableChannel input1();
}
