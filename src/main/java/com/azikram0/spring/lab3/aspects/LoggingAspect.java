package com.azikram0.spring.lab3.aspects;

import com.azikram0.spring.lab3.Warehouse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    @Pointcut("execution (public void sendToWarehouse(com.azikram0.spring.lab3.Warehouse))")
    public void sendToWarehousePointcut() {}

    @Pointcut("execution (public void putIntoWarehouse(com.azikram0.spring.lab3.CanBePutIntoWarehouse))")
    public void putIntoWarehousePointcut() {}

    @Pointcut("execution (public void takeFromWarehouse(com.azikram0.spring.lab3.Warehouse))")
    public void takeFromWarehousePointcut() {}

    @Before("sendToWarehousePointcut()")
    public void beforeSendToWarehouseAdvice(){
        System.out.println("beforeSendToWarehouse: попытка отправить предмет на склад");
    }

    @AfterReturning("putIntoWarehousePointcut()")
    public void afterReturningPutIntoWarehouseAdvice(){
        System.out.println("afterReturningPutIntoWarehouseAdvice: предмет успешно положен на склад");
    }

    @AfterThrowing(pointcut = "putIntoWarehousePointcut()", throwing = "exception")
    public void afterThrowingPutIntoWarehouseAdvice(Throwable exception){
        System.out.println("afterThrowingPutIntoWarehouseAdvice: попытка положить предмет на склад не удалась, "
                + exception.getMessage());
    }

    @AfterThrowing(pointcut = "takeFromWarehousePointcut()", throwing = "exception")
    public void afterThrowingTakeFromWarehouseAdvice(Throwable exception){
        System.out.println("afterThrowingTakeFromWarehouseAdvice: попытка взять предмет со склада не удалась, "
                + exception.getMessage());
    }

    @After("sendToWarehousePointcut()")
    public void afterSendIntoWarehouseAdvice(JoinPoint joinPoint) {
        System.out.print("afterSendIntoWarehouseAdvice: после отправки предмета на склад на складе: ");
        Warehouse warehouse = (Warehouse) joinPoint.getArgs()[0];
        if (warehouse.getWarehouse().isEmpty()) {
            System.out.print("пока пусто");
        } else {
            warehouse.getWarehouse().forEach(elem -> System.out.print("\n" + elem));
        }
        System.out.println();
    }


}
