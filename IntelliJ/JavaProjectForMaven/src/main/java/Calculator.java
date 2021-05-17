public class Calculator {

    private int temp;

    public Calculator() {
        this.temp = 7;
    }

    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int getTemp() {
        int a = 13;
        int b = 123;
        System.out.println("yoyo!");
        return temp;
    }

    public int getTemp(String text) {
        return text.length();
    }

    public String getText() {
        return "Hello World!";
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
