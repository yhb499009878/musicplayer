package com.airlab.musicplayer.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 对timer的管理，避免系统中出现太多的timer。根据名字管理timer，并自动设置timer thread的
 * 名字，利于debug和管理
 */
public final class TimerManager {
    
    private static final Map _timers = new HashMap();
    
    private TimerManager() {
        // utility class
    }
    
    /**
     * 得到一个timer，如果还没有对应名字的timer，则创建一个
     */
    public static Timer getTimer(String name) {
       synchronized (_timers) {
            Timer result = (Timer)_timers.get(name);
            if (result == null) {
                result = createTimer(name);
                _timers.put(name, result);
            }
            return result;
        }
    }
    
    /**
     * 创建一个timer。如果已经有同名的timer，原先已经用这个timer的地方可以接着用。
     * 后来再用同样的名字getTimer，会得到这个新创建的timer
     */
    public static Timer createTimer(final String name) {
        Timer result = new Timer(true);
        // 设置Timer的线程名字
        result.schedule(new TimerTask() {
            public void run() {
                Thread.currentThread().setName(name);
            }
        }, 0);
        return result;
    }
    
}

