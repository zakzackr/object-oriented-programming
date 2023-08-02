import java.util.ArrayList;

class Player{
    private String username;
    private int health;
    private int attack;
    private int defense;
    private double heightM = 1.8;
    private int gold;

    public Player(String username, int health, int attack, int defense, int gold){
        this.username = username;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.heightM = heightM;
        this.gold = gold;
    }

    public double getHeight(){
        return this.heightM;
    }

    // attack methodは引数にMonster classのオブジェクトを受け取るため、PlayerはMonsterに依存している
    // Monster classの設計が変更されると、このメソッドの動作が予期しない形で変わる可能性がある
    public void attack(Monster monster){
        System.out.println(username + " attacks " + monster.getName());

        // e.g. Monsterの身長がmeterからcmに変わった場合、常に下のif条件がtrueになり、Monsterに攻撃しても無効になる
        if (monster.getHeight() >= heightM * 3 || monster.getDefense() >= attack) return;
        monster.attacked(attack - monster.getDefense());
    }

    public String toString(){
        return "Player " + username + " - HP:" + health +"/Atk:" + attack + "/Def:" + defense + "/Gold:" + gold + "/height:" + heightM + " meters";
    }
}

class Monster{
    private String monster;
    private int health;
    private int attack;
    private int defense;
    private double heightM = 3;

    public Monster(String monster, int health, int attack, int defense){
        this.monster = monster;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName(){
        return this.monster;
    }

    public double getHeight(){
        return this.heightM;
    }

    public int getAttack(){
        return this.attack;
    }

    public int getDefense(){
        return this.defense;
    }

    public void attacked(int hp){
        health -= hp;
        if (health < 0) health = 0;
    }

    public String toString(){
        return monster + " - HP:" + health +"/Atk:" + attack + "/Def:" + defense + "/height:" + heightM + " meters";
    }
}

class Coordinate{
    private int x;
    private int y;
    private int z;

    public Coordinate(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    } 

    public String toString(){
        return "{x: " + x + ", y: " + y + ", z: " + z + "}";
    }
}

class Field{
    private static final int MAX_X = 10000;
    private static final int MAX_Y = 7000;
    private static final int MAX_Z = 800;
    // MonsterとMonsterに対応するCoordinateの配列
    ArrayList<Monster> creatures;
    ArrayList<Coordinate> coordinates;

    public Field(){
        this.creatures = new ArrayList<Monster>();
        this.coordinates = new ArrayList<Coordinate>();
    }

    // 関数が必要とするMonsterオブジェクトを直接生成する方法
    // 他の開発者はrandomlyAdd の内部構造を知らないが、FieldクラスはMonsterクラスに依存している
    public void randomlyAdd(String monster, int health, int attack, int defense){
        Monster newMonster = new Monster(monster, health, attack, defense);
        Coordinate c = new Coordinate(internalRanAlgorithm(1, MAX_X), internalRanAlgorithm(1, MAX_Y), internalRanAlgorithm(1, MAX_Z));

        creatures.add(newMonster);
        coordinates.add(c);
    }

    // Monsterオブジェクトを作成するために入力を取り入れるよりも(String monster, int health etc.)、オブジェクト自体を直接取り入れる方が良い
    // 関数が必要とするMonsterオブジェクトを外部から渡すことで、randomlyAdd()より依存関係が明確になり、全ての開発者が依存関係を意識できる(FieldクラスがMonsterクラスに依存している)
    public void randomlyAddWithDependency(Monster monster){
        creatures.add(monster);
        Coordinate c = new Coordinate(internalRanAlgorithm(1, MAX_X), internalRanAlgorithm(1, MAX_Y), internalRanAlgorithm(1, MAX_Z));
        coordinates.add(c);
    }

    private int internalRanAlgorithm(int min, int max){
        return (int)(Math.random() * (max - min) + min);
    }

    public String toString(){
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < creatures.size(); i++){
            s.append(creatures.get(i) + " with coordinate: " + coordinates.get(i) + "\n");
        }

        return s.toString();
    }
}

class Main{
    public static void main(String[] args){
        Monster lion = new Monster("lion",  4000, 300, 200);
        Monster elephant = new Monster("elephant", 4500, 200, 300);

        Field world = new Field();

        // 関数が必要としているオブジェクトを直接生成しているため内部で何が行われているか、どのような依存関係があるか分からない
        world.randomlyAdd("crocodile", 3500, 250, 250);

        // dependency injectionによって関数が必要とするオブジェクトを外部から渡すことで、依存関係が明確になる
        world.randomlyAddWithDependency(lion);
        world.randomlyAddWithDependency(elephant);

        System.out.println(world);
    } 
}
