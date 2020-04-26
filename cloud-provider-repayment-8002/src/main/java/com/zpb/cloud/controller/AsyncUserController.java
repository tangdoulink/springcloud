package com.zpb.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.zpb.cloud.entities.ResultCommont;
import com.zpb.cloud.service.UserService;
import jdk.nashorn.internal.ir.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @dec :
 * @Date: 2020/4/15
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@RestController
public class AsyncUserController {

    private BlockingQueue<Long> queue = new ArrayBlockingQueue<>(3000);

    @Autowired
    private UserService userService;

    @GetMapping("/panic/{id}")
    public ResultCommont<WebAsyncTask> webAsyncTask(@PathVariable("id") Long id) throws Exception {

        //1.创建任务
        Callable<Integer> callable =  createTask(id);
        //2.发送请求
        WebAsyncTask webAsyncTask = new WebAsyncTask(callable);
        Integer call = (Integer)webAsyncTask.getCallable().call();
        System.out.println("call = " + call);
        System.out.println("主线程执行完毕。。。。");
        return call != -1 ? new ResultCommont<>(200,"请求成功",webAsyncTask) : new ResultCommont<>(404,"请求失败");
    }

    private Callable<Integer> createTask(Long id) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                if(queue.size() >= 3){
                    Integer update = userService.update(1L, 3000);
                    queue.clear();
                    return update;
                }
                queue.add(id);
                return -1;
            }
        };
        return callable;
    }

    @GetMapping("test")
    public String webAsyncTask() {
        return "调用成功："+IdUtil.simpleUUID();
    }
}
