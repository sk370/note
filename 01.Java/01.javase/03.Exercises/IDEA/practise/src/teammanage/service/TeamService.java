package teammanage.service;

import teammanage.domain.Architect;
import teammanage.domain.Designer;
import teammanage.domain.Employee;
import teammanage.domain.Programmer;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/27 10:55
 */
public class TeamService {
    private static int counter = 1;
    private final int MAX_MERBER = 5;
    private Programmer[] team = new Programmer[MAX_MERBER];
    private int total = 0;
    public Programmer[] getTeam(){
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < total; i++) {
            team[i] = this.team[i];
        }
        return team;
    }
    public void addMember(Employee e) throws TeamException {
        if(total >= MAX_MERBER){
            throw new TeamException("成员已满，无法添加！");
        }
        if(!(e instanceof Programmer)){
            throw new TeamException("该成员不是开发人员，无法添加！");
        }
        for (int i = 0; i < total; i++) {
            if(e.getId() == team[i].getId() ){
                throw new TeamException("该员工已在本开发团队中！");
            }
        }
        Programmer p = (Programmer)e;
        if(Status.BUSY.equals(p.getStatus())){
            throw new TeamException("该员工已在本开发团队中！");
        }
        if(Status.VOCATION.equals(p.getStatus())){
            throw new TeamException("该员正在休假，无法添加！");
        }
        int countPro = 0, countDes = 0, countArch = 0;
        for (int i = 0; i < total; i++) {
            if(team[i] instanceof Architect){
                countArch++;
            }else if(team[i] instanceof Designer){
                countDes++;
            }else{
                countPro++;
            }
        }
        if(e instanceof Architect){
            if(countArch >= 1){
                throw new TeamException("团队中至多只能有一名架构师！");
            }
        } else if (e instanceof Designer){
            if(countDes >= 2){
                throw new TeamException("团队中至多只能有两名设计师！");
            }
        }else {
            if(countPro >= 3){
                throw new TeamException("团队中至多只能有三名程序员！");
            }
        }
        team[total++] = (Programmer) e;
        ((Programmer) e).setMemberedId(counter++);
        ((Programmer) e).setStatus(Status.BUSY);
    }

    public void removeMember(int memberId) throws TeamException {
        int i = 0;
//        这里i的控制条件total不能写成team.length，当teeam长度为0的时候，会有空指针异常
        for (; i < total; i++) {
            if (team[i].getMemberedId() == memberId){
                team[i].setStatus(Status.FREE);
                break;
            }
        }
//        这里i的控制条件total不能写成team.length，当teeam长度为0的时候，会有空指针异常
        if(i == total){
            throw new TeamException("人员不存在，请确认编号！");
        }
        for(int j = i + 1;j < total;j++){
            team[j - 1] = team[j];
        }
        total--;
    }
}
