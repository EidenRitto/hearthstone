package cn.eiden.hsm.cockpit.mirai;


import cn.eiden.hsm.cockpit.mirai.events.GroupEvent;
import cn.eiden.hsm.game.card.CardFactory;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.event.Events;

/**
 * @author Eiden J.P Zhou
 * @date 2020/8/4 17:02
 */
public class MiraiApplication {
    public static void main(String[] args) {
        Bot bot = BotFactoryJvm.newBot(1L, "xxx");
        bot.login();
        //输出好友
        bot.getFriends().forEach(friend -> System.out.println(friend.getId() + ":" + friend.getNick()));

        Events.registerEvents(bot, new GroupEvent());
        CardFactory.getInstance();
        System.out.println();
        System.out.println("系统启动完成！");

        //阻塞当前线程
        bot.join();
    }
}
