package cn.eiden.hsm.cockpit.console;

import cn.eiden.hsm.output.HearthLinkContext;
import cn.eiden.hsm.GameDemo;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Eiden J.P Zhou
 * @date 2020/6/4 15:35
 */
public class ConsoleCockpit {

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        System.out.println("请输入卡组序列:");
        Scanner scanner = new Scanner(System.in);
        String deckStr = scanner.nextLine();

        cachedThreadPool.execute(() ->{
            GameDemo gameDemo = new GameDemo();
            try {
                gameDemo.start(deckStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        while (true){
            try {
                System.out.println();
                HearthLinkContext.inputMessage.put(scanner.nextLine());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
