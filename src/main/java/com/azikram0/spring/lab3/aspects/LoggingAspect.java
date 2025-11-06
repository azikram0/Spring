package com.azikram0.spring.lab3.aspects;

import com.azikram0.spring.lab3.CanBePutIntoWarehouse;
import com.azikram0.spring.lab3.Warehouse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    @Pointcut("execution (public void sendToWarehouse(com.azikram0.spring.lab3.Warehouse))")
    public void sendToWarehousePointcut() {}

    @Pointcut("execution (public void putIntoWarehouse(com.azikram0.spring.lab3.CanBePutIntoWarehouse))")
    public void putIntoWarehousePointcut() {}

    @Pointcut("execution (public void takeFromWarehouse(com.azikram0.spring.lab3.CanBePutIntoWarehouse, int))")
    public void takeFromWarehousePointcut() {}

    @Before("sendToWarehousePointcut()")
    public void beforeSendToWarehouseAdvice(JoinPoint joinPoint){
        CanBePutIntoWarehouse thing = (CanBePutIntoWarehouse) joinPoint.getTarget();
        System.out.print("beforeSendToWarehouseAdvice: до отправки предмета " + thing.getName()
                + " на склад на складе ");
        Warehouse warehouse = (Warehouse) joinPoint.getArgs()[0];
        if (warehouse.getThings().isEmpty()) {
            System.out.print("пока пусто");
        } else {
            warehouse.getThings().forEach(elem -> System.out.print(elem.getName() + ", "));
        }
        System.out.println();
    }

    @AfterReturning("takeFromWarehousePointcut()")
    public void afterReturningTakeFromWarehouseAdvice(JoinPoint joinPoint){
        CanBePutIntoWarehouse thing = (CanBePutIntoWarehouse) joinPoint.getArgs()[0];
        int n = (int) joinPoint.getArgs()[1];
        System.out.println("afterReturningTakeFromWarehouseAdvice: предмет " + thing.getName()
                + " успешно взят со склада в количестве " + n);
    }

    @AfterReturning("putIntoWarehousePointcut()")
    public void afterReturningPutIntoWarehouseAdvice(JoinPoint joinPoint){
        CanBePutIntoWarehouse thing = (CanBePutIntoWarehouse) joinPoint.getArgs()[0];
        System.out.println("afterReturningPutIntoWarehouseAdvice: предмет " + thing.getName()
                + " успешно положен на склад");
    }

    @AfterThrowing(pointcut = "putIntoWarehousePointcut()", throwing = "exception")
    public void afterThrowingPutIntoWarehouseAdvice(JoinPoint joinPoint, Throwable exception){
        CanBePutIntoWarehouse thing = (CanBePutIntoWarehouse) joinPoint.getArgs()[0];
        System.out.println("afterThrowingPutIntoWarehouseAdvice: попытка положить предмет " + thing.getName()
                + " на склад не удалась, " + exception.getMessage());
    }

    @AfterThrowing(pointcut = "takeFromWarehousePointcut()", throwing = "exception")
    public void afterThrowingTakeFromWarehouseAdvice(JoinPoint joinPoint, Throwable exception){
        CanBePutIntoWarehouse thing = (CanBePutIntoWarehouse) joinPoint.getArgs()[0];
        int n = (int) joinPoint.getArgs()[1];
        System.out.println("afterThrowingTakeFromWarehouseAdvice: попытка взять предмет " + thing.getName()
                + " в количестве " + n + " со склада не удалась, " + exception.getMessage());
    }

    @After("sendToWarehousePointcut()")
    public void afterSendIntoWarehouseAdvice(JoinPoint joinPoint) {
        CanBePutIntoWarehouse thing = (CanBePutIntoWarehouse) joinPoint.getTarget();
        System.out.print("afterSendIntoWarehouseAdvice: сейчас на складе ");
        Warehouse warehouse = (Warehouse) joinPoint.getArgs()[0];
        if (warehouse.getThings().isEmpty()) {
            System.out.print("пока пусто");
        } else {
            warehouse.getThings().forEach(elem -> System.out.print(elem.getName() + ", "));
        }
        System.out.println();
    }

    @After("takeFromWarehousePointcut()")
    public void afterTakeFromWarehouseAdvice(JoinPoint joinPoint) {
        CanBePutIntoWarehouse thing = (CanBePutIntoWarehouse) joinPoint.getArgs()[0];
        int n = (int) joinPoint.getArgs()[1];
        System.out.print("afterTakeFromWarehouseAdvice: сейчас на складе  ");
        Warehouse warehouse = (Warehouse) joinPoint.getTarget();
        if (warehouse.getThings().isEmpty()) {
            System.out.print("пока пусто");
        } else {
            warehouse.getThings().forEach(elem -> System.out.print(elem.getName() + ", "));
        }
        System.out.println();
    }

    @Around("sendToWarehousePointcut() || takeFromWarehousePointcut()")
    public Object aroundWarehouseOps(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            System.out.println("aroundSendToWarehouseAdvice: before ---------------------------------------------------");
            Object result = joinPoint.proceed();
            System.out.println("aroundSendToWarehouseAdvice: after  ---------------------------------------------------");
            return result;
        } catch (Throwable e) {
            System.out.println("[AOP] Ошибка в методе: " + joinPoint.getSignature().getName() +
                    " — " + e.getMessage());
            System.out.println("aroundSendToWarehouseAdvice: after  ---------------------------------------------------");
            return null;
        }
    }


}
