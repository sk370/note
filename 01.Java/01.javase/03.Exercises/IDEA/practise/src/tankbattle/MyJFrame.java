package tankbattle;

import javax.lang.model.element.VariableElement;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/9 10:18
 * @desc: 设置绘图区
 */
public class MyJFrame extends JFrame {
    MyPanel myPanel = null;
    static Scanner scanner = new Scanner(System.in);
    static String key;
    public MyJFrame(){
        myPanel = new MyPanel(key);
        new Thread(myPanel).start();
//        将画板放入绘图区域
        this.add(myPanel);
//        设置绘图区域
        this.setSize(1300, 800);
//        设置关闭绘图区结束程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        设置绘图区可见性
        this.setVisible(true);
//        增加绘图区的按键事件监听
        this.addKeyListener(myPanel);
//        增加绘图区的窗口事件监听，关闭窗口触发将游戏记录保存到文件的事件
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recoder.keepRecod();
                System.exit(0);//不写这句也能正常运行
            }
        });

    }
    public static void main(String[] args) {
        System.out.println("请选择游戏类型，1为新游戏，2为上局游戏");
        key = scanner.next();
        MyJFrame myJFrame = new MyJFrame();
    }
}
