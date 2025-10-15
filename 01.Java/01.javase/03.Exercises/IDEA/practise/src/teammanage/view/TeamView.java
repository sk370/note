package teammanage.view;

import teammanage.domain.Employee;
import teammanage.service.NameListService;
import teammanage.service.TeamException;
import teammanage.service.TeamService;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/27 11:01
 */
public class TeamView {
    private NameListService listSvc =  new NameListService();
    private TeamService teamSvc = new TeamService();
    public void enterMainMenu(){
        Boolean flag = true;
        char choose = 0;
        while (true){
            if(choose != '1'){
                listAllEmployees();
            }
            System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
            choose = TSUtility.readMenuSelection();
            switch (choose){
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.println("确认退出吗？（Y/N");
                    char confirm = TSUtility.readConfirmSelection();
                    if(confirm == 'Y'){
                        System.out.println("退出成功！");
                        return;
                    } else if (confirm == 'N') {
                        System.out.println("取消退出！");
                    }
            }
        }
    }
    private void listAllEmployees(){
        System.out.println("-------------------------------开发团队调度软件--------------------------------");
        System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
        for (int i = 0; i < listSvc.getAllEmployees().length; i++) {
            System.out.println(listSvc.getAllEmployees()[i]);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }
    private void getTeam(){
        if(teamSvc.getTeam().length==0){
            System.out.println("当前团队没有成员！");
            return;
        }
        System.out.println("TID/ID\t姓名\t年龄\t工资\t奖金\t股票");
        for (int i = 0; i < teamSvc.getTeam().length; i++) {
            System.out.println(teamSvc.getTeam()[i].teamDesc());
        }
    }
    private void addMember(){
        System.out.print("请输入选择的序号：");
        int add = TSUtility.readInt();
        try {
            Employee nls = listSvc.getEmployee(add);
            teamSvc.addMember(nls);
            System.out.println("添加成功");
        } catch (TeamException e) {
            System.out.println("添加失败，原因是：" + e.getMessage());
        }
        TSUtility.readReturn();
    }
    private void deleteMember(){
        System.out.print("请输入要删除的员工TID：");
        int delete = TSUtility.readInt();
        System.out.print("是否确认删除？（Y/N）");
        char c = TSUtility.readConfirmSelection();
        if(c == 'Y'){
            try {
                teamSvc.removeMember(delete);
                System.out.println("删除成功");
            } catch (TeamException e) {
                System.out.println("删除失败，原因为：" + e.getMessage());
            }
            TSUtility.readReturn();
        } else if (c == 'N') {
            return;
        }
    }

    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        teamView.enterMainMenu();
    }
}
