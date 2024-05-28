package com.cycas.design.composite;

/**
 * @author xin.na
 * @since 2024/5/15 16:32
 */
public class ComponentClient2 {

    public static void main(String[] args) {
        ConcreteCompany root = new ConcreteCompany("北京总公司");
        root.add(new HRDepartment("总公司人力资源部"));
        root.add(new FinanceDepartment("总公司财务部"));

        ConcreteCompany comp = new ConcreteCompany("上海华东分公司");
        comp.add(new HRDepartment("华东分公司人力资源部"));
        comp.add(new FinanceDepartment("华东分公司财务部"));
        root.add(comp);

        ConcreteCompany comp2 = new ConcreteCompany("南京办事处");
        comp2.add(new HRDepartment("南京办事处人力资源部"));
        comp2.add(new FinanceDepartment("南京办事处财务部"));
        root.add(comp2);

        ConcreteCompany comp3 = new ConcreteCompany("杭州办事处");
        comp3.add(new HRDepartment("杭州办事处人力资源部"));
        comp3.add(new FinanceDepartment("杭州办事处财务部"));
        root.add(comp3);

        System.out.println("结构图:");
        root.display(1);
        System.out.println("职责:");
        root.lineOfDuty();
    }
}
