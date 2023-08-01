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

    public void printPlayer(){
        System.out.println("=== Player status ===");
        System.out.println("username: " + username);
        System.out.println("health: " + health);
        System.out.println("attack: " + attack);
        System.out.println("defense: " + defense);
        System.out.println("heightM: " + heightM);
    }
}

class Monster{
    private String monster;
    private int health;
    private int attack;
    private int defense;
    // Monsterの身長をMeter->cmに変更する
    // private double heightM = 3;
    private double heightCm = 300;

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
        return this.heightCm;
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

    public void printMonster(){
        System.out.println("=== Monster status ===");
        System.out.println("monster: " + monster);
        System.out.println("health: " + health);
        System.out.println("attack: " + attack);
        System.out.println("defense: " + defense);
        System.out.println("heightCm: " + heightCm);
    }
}

class Main{
    public static void main(String[] args){
        Player p1 = new Player("Peter", 2000, 200, 60, 1000);
        Monster gorilla = new Monster("Gorilla", 4000, 40, 100);

        p1.printPlayer();
        gorilla.printMonster();
        p1.attack(gorilla);
        // Gorillaの体力(health)は本来変化するはずだが、Monster classの設計を変更したためPlayer classのattack methodが予期した動作をしなくなった。
        gorilla.printMonster(); 
    } 
}
