package com.crustlab.aop.service;

import com.crustlab.aop.LogMetric;
import com.crustlab.aop.model.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SomeService {

    private static final Logger LOG = LoggerFactory.getLogger(SomeService.class);

    @LogMetric("FOO_CALL")
    public SampleData foo() {
        LOG.info("'foo' method call");
        return new SampleData(
                System.currentTimeMillis(),
                "foo"
        );
    }

    @LogMetric("BAR_CALL")
    public SampleData bar() {
        LOG.info("'bar' method call");
        throw new RuntimeException("Fatal error....");
    }

}
