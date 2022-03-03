package myObject;

import java.sql.SQLException;
import java.util.Scanner;

import mysqlConnect.MysqlConnect;

public class Menu {
    public int choose;
    public boolean flag;
    Scanner input = null;

    public Menu() {
        this.flag = true;
        this.input = new Scanner(System.in);
    }

    public void closeInput() {
        this.input.close();
    }

    public void printMainMenu() {
        if(this.flag) {
            System.out.println("--�ͻ���Ϣ�������--");
            System.out.println("\t1 ��ӿͻ�");
            System.out.println("\t2 �޸Ŀͻ�");
            System.out.println("\t3 ɾ���ͻ�");
            System.out.println("\t4 �ͻ��б�");
            System.out.println("\t5 ��   ��");
            this.flag = false;
        }

        System.out.print("\t��ѡ��1-5����");
//		Scanner input = new Scanner(System.in);
        while(true) {
            String temp = input.next();
            try {
                this.choose = Integer.parseInt(temp);
                break;
            }catch (Exception e) {
                System.out.print("\n����������������룺");
            }
        }

    }

    //��
    public void addCustomMenu() {

        String regex;

        ////Scanner input = new Scanner(System.in);
        System.out.println("--��ӿͻ�--");

        System.out.print("������");
        String name;
        while(true) {
            name = input.next();
            if(name.length() == 0) {
                System.out.print("������������");
                continue;
            }
            if(name.length() > 10) {
                System.out.print("���ֹ��������������룺");
                continue;
            }
            break;
        }

        System.out.print("�Ա�");
        char gender;
        while(true) {
            String temp = input.next();
            if(temp.equals("��")) {
                gender = '��';
            }else if(temp.equals("Ů")) {
                gender = 'Ů';
            }else {
                System.out.print("����������������룺");
                continue;
            }
            break;
        }

        System.out.print("���䣺");
        int age;
        while(true) {
            String temp = input.next();
            try {
                age = Integer.parseInt(temp);
                if(age>141 || age < 0) {
                    System.out.print("���벻�Ϸ������������룺");
                    continue;
                }
                break;
            }catch (Exception e) {
                System.out.print("����������������룺");
            }
        }

        System.out.print("�绰��");
        String phone;
        while(true) {
            phone = input.next();
            regex = "[1]{1}[0-9]{10}";
            if(!phone.matches(regex)) {
                System.out.print("��������ȷ��ʽ��11λ�绰���룺");
                continue;
            }
            break;
        }

        System.out.print("���䣺");
        String email;
        while(true) {
            email = input.next();
            regex = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
            if(!email.matches(regex)) {
                if( (email.length() > 20)) {
                    System.out.print("�����ַ����(����20���ַ�)�����������룺");
                }else {
                    System.out.print("��������ȷ��ʽ(XXXXX@XXX.com)�������ַ��");
                }

                continue;
            }
            break;
        }
        Customer customer = new Customer(name, gender, age, phone, email);

        boolean flag = false;
        MysqlConnect mc = new MysqlConnect();
        try {
            flag = mc.add(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(flag) {
            System.out.println("--������--");
        }else {
            System.out.println("--����ʧ��--");
        }

    }

    //ɾ
    public void deleteCustomMenu() {

        //Scanner input = new Scanner(System.in);
        System.out.println("--ɾ���ͻ�--");
        System.out.print("��ѡ���ɾ���ͻ����(-1�˳�)��");
        int id;
        while(true) {
            try {
                id = input.nextInt();
                break;
            }catch (Exception e) {
                System.out.print("��������ȷ��ʽ�ı�ţ�");
                continue;
            }
        }
        if(id == -1) {
            System.out.println("------------");

            return;
        }

        while(true) {
            System.out.print("ȷ���Ƿ�ɾ��(Y/N)��");
            String in = input.next();
            if(in.equals("y") || in.equals("Y")) {
                boolean flag = false;
                MysqlConnect mc = new MysqlConnect();
                try {
                    flag = mc.delete(id);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if(flag) {
                    System.out.println("--ɾ�����--");
                }else {
                    System.out.println("--����ʧ��--");
                }

                break;
            }else if(in.equals("n") || in.equals("N")) {
                System.out.println("--��ȡ��--");
                return;
            }else {
                System.out.println("��Ч����");
                continue;
            }
        }
    }

    //��
    public void updateCustomer() {

        System.out.println("--�޸Ŀͻ�--");
        System.out.print("��ѡ����޸Ŀͻ����(-1�˳�)��");

        int id;
        while(true) {
            try {
                String temp = input.next();
                id = Integer.parseInt(temp);
                if (id == -1) {
                    System.out.println("--ȡ���޸�--");
                    return;
                }else if(id < -1) {
                    System.out.print("��������������ţ�");
                    continue;
                }
            }catch (Exception e) {
                System.out.print("��������������ţ�");
                continue;
            }
            break;
        }

        Customer customer = new MysqlConnect().seekToGetCustomer(id);
        if(customer == null) {
            System.out.println("�ͻ�������");
            return;
        }

        MysqlConnect mc = new MysqlConnect();

        System.out.print("����(" + customer.name + ")��");
        String name;
        input.nextLine();//�Ե��س�
        while(true) {

            name = input.nextLine();

            if(name.length() == 0) {
                break;
            }
            if(name.length() != 0 && name.length() < 10) {
                try {
                    mc.update(id, "name", name);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.print("���ֹ��������������룺");
                continue;
            }
            break;
        }


        System.out.print("�Ա�(" + customer.gender + ")��");
        while(true) {
            String gender = input.nextLine();
            if(gender.length() == 0) {
                break;
            }
            if(gender.length() != 0 ) {
                if (! (gender.equals("��") || gender.equals("Ů"))) {
                    System.out.print("����������������룺");
                    continue;
                }
                try {
                    mc.update(id, "gender", gender);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            break;
        }


        System.out.print("����(" + customer.age + ")��");
        while(true) {
            String age = input.nextLine();
            if(age.length() != 0) {
                try {
                    int temp = Integer.parseInt(age);
                    if(temp >140 || temp < 0) {
                        System.out.print("���벻�Ϸ������������룺");
                        continue;
                    }else {
                        mc.update(id, "age", age);
                        break;
                    }
                }catch (Exception e) {
                    System.out.print("���벻�Ϸ������������룺");
                    continue;
                }
            }
            break;
        }

        System.out.print("�绰(" + customer.phone + ")��");
        String phone;
        while(true) {
            phone = input.nextLine();

            if(phone.length() != 0) {
                String regex = "[1]{1}[0-9]{10}";
                if(!phone.matches(regex)) {
                    System.out.print("��������ȷ��ʽ��11λ�绰���룺");
                    continue;
                }
                try {
                    mc.update(id, "phone", phone);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            break;
        }

        System.out.print("����(" + customer.email + ")��");
        String email;
        while(true) {
            email = input.nextLine();

            if(email.length() != 0) {
                String regex = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
                if(!email.matches(regex) || (email.length() > 20)) {
                    if( (email.length() > 20)) {
                        System.out.print("�����ַ����(����20���ַ�)�����������룺");
                    }else {
                        System.out.print("��������ȷ��ʽ(XXXXX@XXX.com)�������ַ��");
                    }
                    continue;
                }
                try {
                    mc.update(id, "email", email);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            break;
        }

        System.out.println("--�޸����--");
    }

    //��
    public void customerList() {
        System.out.println("-----------�ͻ��б�-----------");
        System.out.print(String.format("%-5s\t", "���"));
        System.out.print(String.format("%-10s\t", "����"));
        System.out.print(String.format("%-5s\t", "�Ա�"));
        System.out.print(String.format("%-5s\t", "����"));
        System.out.print(String.format("%-15s\t", "�绰"));
        System.out.print(String.format("%-10s\t", "����"));
        System.out.println();
        String sql = "SELECT * FROM users;";
        try {
            new MysqlConnect().connectQuery(sql,true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("---------�ͻ��б����----------");
    }


}
