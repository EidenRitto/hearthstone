package cn.eiden.hsm.cockpit.java;

/**
 * @author 周晋平
 * @date 2020/9/23 16:07
 */
public class ServerCockpit {
    public static void main(String[] args) throws InterruptedException {
        HearthServer hearthServer = new HearthServer();
        hearthServer.run(8088);
    }
}
