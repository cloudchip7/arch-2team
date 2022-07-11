package com.samsungsds.consumer.receiver;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface IotEventReceiver {

	String INPUT1 = "input1";

	String INPUT2 = "input2";


	@Input(IotEventReceiver.INPUT1)
	SubscribableChannel input1();

	@Input(IotEventReceiver.INPUT2)
	SubscribableChannel input2();
}
