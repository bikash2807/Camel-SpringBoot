package com.camelspringpoc;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;

import com.camelspringpoc.model.Input;
import com.camelspringpoc.model.Output;

public class Processing {

	public void getData(Input in,Exchange exchange) {
		
		Map<String, Integer> map = new HashMap();
		map.put("id", in.getId());
		exchange.getIn().setBody(map);
	}
	
}
