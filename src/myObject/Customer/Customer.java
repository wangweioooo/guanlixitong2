package myObject;


public class Customer {
    public String name;//�ͻ�����
    public char gender;//�Ա�
    public int age;//����
    public String phone;//�绰����
    public String email;//��������

    public Customer(String name, char gender, int age, String phone, String email) {
        super();
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public Customer(String name, String gender, String age, String phone, String email) {
        super();
        this.name = name;
        this.gender = gender.charAt(0);
        this.age = Integer.parseInt(age);
        this.phone = phone;
        this.email = email;
    }
}
