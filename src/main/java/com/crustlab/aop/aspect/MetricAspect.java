package com.crustlab.aop.aspect;

import com.crustlab.aop.AopUtils;
import com.crustlab.aop.LogMetric;
import com.crustlab.aop.service.MetricsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MetricAspect {

    private static final Logger LOG = LoggerFactory.getLogger(MetricAspect.class);

    private final MetricsService metricsService;

    @Autowired
    public MetricAspect(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Pointcut("execution(@com.crustlab.aop.LogMetric com.crustlab.aop.model.SampleData *(..)) && @annotation(logMetric)")
    private void logMetricAnnotatedPointcut(LogMetric logMetric) {

    }

    @AfterReturning(
            value = "logMetricAnnotatedPointcut(logMetric)",
            argNames = "joinPoint,logMetric"
    )
    public void afterReturningLogMetricAnnotatedMethod(JoinPoint joinPoint, LogMetric logMetric) {
        LOG.info(String.format("@AfterReturning advice called for method: '%s'", AopUtils.getMethodSignature(joinPoint)));
        metricsService.logMetric(logMetric.value());
    }

    @AfterThrowing(
            value = "logMetricAnnotatedPointcut(logMetric)",
            argNames = "joinPoint,logMetric"
    )
    public void afterThrowingLogMetricAnnotatedMethod(JoinPoint joinPoint, LogMetric logMetric) {
        LOG.info(String.format("@AfterThrowing advice called for method: '%s'", AopUtils.getMethodSignature(joinPoint)));
        metricsService.logErrorMetric(logMetric.value());
    }

}
