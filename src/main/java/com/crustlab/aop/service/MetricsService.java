package com.crustlab.aop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private static final Logger LOG = LoggerFactory.getLogger(MetricsService.class);

    public void logMetric(String metricName) {
        LOG.info(String.format("'logMetric' call for metric: '%s'", metricName));
    }

    public void logErrorMetric(String metricName) {
        LOG.info(String.format("'logErrorMetric' call for metric: '%s'", metricName));
    }

}
