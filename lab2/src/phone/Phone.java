package phone;

public class Phone {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private long accountNum;
    private int timeInCity;
    private int timeOutCity;

    public Phone(int id, String firstName, String lastName, String middleName, long accountNum, int timeInCity, int timeOutCity) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.accountNum = accountNum;
        this.timeInCity = timeInCity;
        this.timeOutCity = timeOutCity;
    }

    public void setAccountNum(long accountNum) {
        this.accountNum = accountNum;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimeInCity(int timeInCity) {
        this.timeInCity = timeInCity;
    }

    public void setTimeOutCity(int timeOutCity) {
        this.timeOutCity = timeOutCity;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public long getAccountNum() {
        return accountNum;
    }

    public int getTimeInCity() {
        return timeInCity;
    }

    public int getTimeOutCity() {
        return timeOutCity;
    }

    @Override
    public String toString() {
        return "\nPhone: " +
                "\nID: " + id +
                "\nІм'я: '" + firstName +
                "\nПрізвище: " + lastName +
                "\nПо батькові: " + middleName +
                "\nНомер рахунку: " + accountNum +
                "\nЧас міських розмов: " + timeInCity +
                "\nЧас міжміських розмов: " + timeOutCity + '\n';
    }
}

