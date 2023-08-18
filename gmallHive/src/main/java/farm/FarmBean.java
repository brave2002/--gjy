package farm;


/**
 *
 */
public class FarmBean {
    private String province;
    private String name;
    private int sum;

    public FarmBean() {
    }

    public FarmBean(String province, int sum) {
        this.province = province;
        this.sum = sum;
    }

    public FarmBean(String province, String name, int sum) {
        this.province = province;
        this.name = name;
        this.sum = sum;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "FarmBean{" +
                "province='" + province + '\'' +
                ", sum=" + sum +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
