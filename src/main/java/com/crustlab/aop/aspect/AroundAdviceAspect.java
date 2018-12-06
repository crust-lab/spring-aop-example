package com.crustlab.aop.aspect;

import com.crustlab.aop.AopUtils;
import com.crustlab.aop.LogMetric;
import com.crustlab.aop.model.SampleData;
import com.crustlab.aop.service.MetricsService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class AroundAdviceAspect {

    private static final Logger LOG = LoggerFactory.getLogger(AroundAdviceAspect.class);

    private final MetricsService metricsService;

    @Autowired
    public AroundAdviceAspect(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Pointcut("execution(@com.crustlab.aop.LogMetric com.crustlab.aop.model.SampleData *(..)) && @annotation(logMetric)")
    private void logMetricAnnotatedPointcut(LogMetric logMetric) {

    }

    @Around(
            value = "logMetricAnnotatedPointcut(logMetric)",
            argNames = "joinPoint,logMetric"
    )
    public Object aroundMetricMethods(ProceedingJoinPoint joinPoint, LogMetric logMetric) throws Throwable {
        LOG.info(String.format("@Around advice called for method: '%s'", AopUtils.getMethodSignature(joinPoint)));
        try {
            SampleData returnedValue = (SampleData) joinPoint.proceed();
            if (returnedValue.getTimestamp() % 2 == 0) {
                metricsService.logMetric(logMetric.value() + "_EVEN_TIMESTAMP");
            }
            return returnedValue;
        } catch (Throwable t) {
            metricsService.logErrorMetric(logMetric.value());
            throw t;
        }
    }

}
