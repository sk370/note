package teammanage.service;

import teammanage.domain.*;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/27 10:32
 */
public class NameListService {
    private Employee[] employees;

    public NameListService() {
        employees = new Employee[Data.EMPLOYEES.length];
        for (int i = 0; i < Data.EMPLOYEES.length; i++) {
            int type = Integer.parseInt(Data.EMPLOYEES[i][0]);
            int id = Integer.parseInt(Data.EMPLOYEES[i][1]);
            String name = Data.EMPLOYEES[i][2];
            int age = Integer.parseInt(Data.EMPLOYEES[i][3]);
            double salary = Double.parseDouble(Data.EMPLOYEES[i][4]);

            Equipment equipment;
            double bonus;
            int stock;

            switch (type) {
                case Data.EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case Data.PROGRAMMER:
                    equipment = this.getEquipment(i);
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;
                case Data.DESIGNER:
                    bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
                    equipment = this.getEquipment(i);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case Data.ARCHITECT:
                    bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
                    equipment = this.getEquipment(i);
                    stock = Integer.parseInt(Data.EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
                    break;
            }
        }
    }

    public Equipment getEquipment(int index) {
        int type = Integer.parseInt(Data.EQUIPMENTS[index][0]);
        String model = Data.EQUIPMENTS[index][1];
        switch (type) {
            case Data.PC:
                String display = Data.EQUIPMENTS[index][2];
                return new PC(model, display);
            case Data.NOTEBOOK:
                double price = Double.parseDouble(Data.EQUIPMENTS[index][2]);
                return new NoteBook(model, price);
            case Data.PRINTER:
                String name = Data.EQUIPMENTS[index][1];
                String desc = Data.EQUIPMENTS[index][2];
                return new Printer(name, desc);
        }
        return null;
    }

    public Employee[] getAllEmployees() {
        return employees;
    }

    public Employee getEmployee(int id) throws TeamException {
        for (int i = 0; i < employees.length; i++) {
            if(id == employees[i].getId()){
                return employees[i];
            }
        }
        throw new TeamException("找不到指定的员工");
    }

    public static void main(String[] args) {
        NameListService nls = new NameListService();
        Employee[] es = nls.getAllEmployees();
        for (int i = 0; i < es.length; i++) {
            System.out.println(es[i]);
        }
        try{
            System.out.println(nls.getEmployee(13));
        }catch (TeamException e){
            System.out.println(e.getMessage());
        }
    }
}
