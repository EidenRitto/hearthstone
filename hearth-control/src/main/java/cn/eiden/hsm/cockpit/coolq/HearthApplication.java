package cn.eiden.hsm.cockpit.coolq;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import cn.eiden.hsm.cockpit.coolq.listeners.CoolHearthListener;
import cn.eiden.hsm.cockpit.coolq.listeners.HearthMultiplayerListener;
import cn.eiden.hsm.game.card.CardFactory;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 14:34
 */
public class HearthApplication {
    public static void main(String[] args) {
        // 创建机器人配置 ( 传入Picq端口 )
        PicqConfig config = new PicqConfig(31092);
        config.setNoVerify(true);

        PicqBotX bot = new PicqBotX(config);
        bot.addAccount("hearth-bot", "127.0.0.1", 31091);

        // 注册事件监听器, 可以注册多个监听器
        bot.getEventManager().registerListeners(new CoolHearthListener(),new HearthMultiplayerListener());
        bot.startBot();
        
        //初始化炉石卡牌缓存池
        CardFactory.getInstance();
        System.out.println();
        System.out.println("启动完成");
    }
}
