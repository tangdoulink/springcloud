package com.zpb.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * @dec : springmvc 的异步返回
 * @Date: 2020/4/10
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@RestController
public class AsyncController {

    private BlockingQueue<DeferredResult<String>> blockingQueue = new ArrayBlockingQueue<>(1024);

    @GetMapping("get")
    public DeferredResult<String> deferredResult(){
        DeferredResult<String> result = new DeferredResult<>(3000L, "error");
        System.out.println("/get 调用！thread id is : " + Thread.currentThread().getId());
        blockingQueue.add(result);
        return result;
    }

    @GetMapping("take")
    public void take() throws InterruptedException{
        System.out.println("/take 调用！thread id is : " + Thread.currentThread().getId());
        blockingQueue.take().setResult(""+IdUtil.simpleUUID());
    }

//    @GetMapping
//    public Callable<String> callable() {
//        return () -> IdUtil.simpleUUID();
//    }

    @GetMapping("as")
    public Object webAsyncTask() throws Exception {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {Thread.sleep(1000L); } catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("当前执行线程是："+Thread.currentThread().getName());
                return "异步线程回调结果："+IdUtil.simpleUUID();
            }
        };
        System.out.println("主线程执行完毕。。。。");
        WebAsyncTask webAsyncTask = new WebAsyncTask(callable);
        Object call = webAsyncTask.getCallable().call();
        return call;

    }

}
