package com.transportocol;

/**
 * @author wangyj
 * @Description: (传输数据返回结果处理类)
 * @date 2020/12/9 16:36
 */
public interface TransformAfter<R,T> {

    int afterDone(R r, T t);
}
