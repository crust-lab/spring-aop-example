package com.crustlab.aop;

import org.aspectj.lang.JoinPoint;

public final class AopUtils {

    private AopUtils() {

    }

    public static String getMethodSignature(JoinPoint joinPoint) {
        String shortSignature = joinPoint.getSignature().toShortString();
        return shortSignature.substring(0, shortSignature.indexOf('('));
    }

}
