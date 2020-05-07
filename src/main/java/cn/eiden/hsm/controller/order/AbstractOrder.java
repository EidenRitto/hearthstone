package cn.eiden.hsm.controller.order;

import cn.eiden.hsm.output.HearthLinkContext;
import cn.eiden.hsm.output.OutputInfo;

/**
 * @author Eiden J.P Zhou
 * @date 2020/5/7 16:20
 */
public abstract class AbstractOrder {
    protected String getOrder() {
        String order = "";
        try {
            order = HearthLinkContext.inputMessage.take();
        } catch (InterruptedException e) {
            OutputInfo.info(e.getMessage());
        }
        return order;
    }
}
