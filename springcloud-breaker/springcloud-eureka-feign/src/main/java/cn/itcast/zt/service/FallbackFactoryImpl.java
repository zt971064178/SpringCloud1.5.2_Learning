package cn.itcast.zt.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhangtian on 2017/4/20.
 */
@Component
public class FallbackFactoryImpl implements FallbackFactory<ComputeClient> {
    @Override
    public ComputeClient create(Throwable throwable) {
        return new ComputeClient() {
            @Override
            public Integer add(Integer a, Integer b) {
                return -9999;
            }
        };
    }
}
