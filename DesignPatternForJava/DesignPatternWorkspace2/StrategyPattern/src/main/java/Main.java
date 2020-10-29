public class Main {

    public static void main(String[] args){
        Moving train = new Train();
        Moving bus = new Bus();

        /*
            기존의 기차와 버스의 이동 방식
            1) 기차 - 선로
            2) 버스 - 도로
         */
        train.setMovableStrategy(new RailLoadStrategy());
        System.out.println("선로를 달리는 기차");
        train.move();

        bus.setMovableStrategy(new LoadStrategy());
        System.out.println("도로를 달리는 버스");
        bus.move();

        /*
            선로를 따라 움직이는 버스가 개발
         */
        System.out.println("developed bus on rail load...");

        bus.setMovableStrategy(new RailLoadStrategy());
        System.out.println("선로를 달리는 버스");
        bus.move();
    }
}