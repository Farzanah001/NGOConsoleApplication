package ngomanagement;
public class NGOPojo {
private String name;
private int age;
private int id;
private long mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }
    NGOPojo(String name,int age,int id,long mobile){
        this.name=name;
        this.age=age;
        this.id=id;
        this.mobile=mobile;
    }
    NGOPojo(){

    }
}
