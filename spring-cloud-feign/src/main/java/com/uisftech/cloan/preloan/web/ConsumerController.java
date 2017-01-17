package com.uisftech.cloan.preloan.web;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uisftech.cloan.preloan.service.IComputeClient;

@RestController
public class ConsumerController {
	private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private IComputeClient computeClient;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add() {
        return computeClient.add(10, 20);
    }
}
