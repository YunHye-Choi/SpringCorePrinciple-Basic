package hello.core.singleton;

public class SingletonService {
    // 1. static 영역에 객체를 딱 1개만 생성!
    public static final SingletonService instance = new SingletonService();

    // 2. public메서드로 객체 받아오게 하기
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. private 생성자를 통해 외부에서 임의로 객체 생성하는 것 막아야 함! (중요)
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체");
    }

    /**
     * 외부에서
     *     public static void main(String[] args) {
     *         SingletonService singletonService = SingletonService.getInstance();
     *     }
     *  이와 같이 활용 가능
     */
}
