package com.siri.game.mystarrail.starrail_reference;

// 战斗单位基类
abstract class BattleUnit {
    protected String name;
    protected int maxHp;
    protected int hp;
    protected int attack;
    protected int defense;
    protected int speed;
    protected int actionValue; // 行动值（0-10000）

    public BattleUnit(String name, int maxHp, int attack, int defense, int speed) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.actionValue = 0;
    }

    // 行动值推进
    public void advanceActionValue() {
        actionValue += speed;
    }

    // 是否准备好行动
    public boolean isReadyToAct() {
        return actionValue >= 10000;
    }

    // 行动后重置行动值
    public void resetActionValue() {
        actionValue -= 10000;
    }

    // 受到伤害
    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - defense);
        hp = Math.max(0, hp - actualDamage);
        System.out.println(name + " 受到 " + actualDamage + " 点伤害！");
    }

    // 是否存活
    public boolean isAlive() {
        return hp > 0;
    }

    // 获取状态信息
    public String getStatus() {
        return name + " [HP: " + hp + "/" + maxHp + "]";
    }

    // 抽象行动方法
    public abstract void act(BattleSystem battle);
}
