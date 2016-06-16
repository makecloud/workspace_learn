package com.lyh.service.client;

import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.MessageContext;

public class AsynServiceCallback implements AxisCallback{

	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Exception arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFault(MessageContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(MessageContext arg0) {
		System.out.println(arg0.getEnvelope());
		System.out.println("message:"+
		arg0.getEnvelope().getFirstElement().getFirstElement().getFirstElement().getText());
		
		
	}

}
