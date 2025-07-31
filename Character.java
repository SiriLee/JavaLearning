package com.siri.game.mystarrail.starrail_reference;

// 角色基类
class Character extends BattleUnit {
    protected int energy;
    protected int maxEnergy;
    protected double critRate; // 暴击率 (0.0-1.0)
    protected double critDmg;  // 暴击伤害 (1.0+)

    public Character(String name, int maxHp, int attack, int defense, int speed,
                     int maxEnergy, double critRate, double critDmg) {
        super(name, maxHp, attack, defense, speed);
        this.maxEnergy = maxEnergy;
        this.energy = 0;
        this.critRate = critRate;
        this.critDmg = critDmg;
    }

    // 普通攻击
    public void basicAttack(BattleUnit target) {
        int damage = calculateDamage(attack, 1.0);
        System.out.println(name + " 进行普通攻击！");
        target.takeDamage(damage);
        energy = Math.min(maxEnergy, energy + 20);
    }

    // 战技（默认为开拓者战技）
    public void skill(BattleUnit target) {
        int damage = calculateDamage(attack, 1.8);
        System.out.println(name + " 使用战技！");
        target.takeDamage(damage);
        energy = Math.min(maxEnergy, energy + 30);
    }

    // 终结技（默认为开拓者终结技）
    public void ultimate(BattleUnit target) {
        int damage = calculateDamage(attack, 3.0);
        System.out.println(name + " 释放终结技！");
        target.takeDamage(damage);
        energy = 0;
    }

    // 计算伤害（包含暴击）
    protected int calculateDamage(int baseDamage, double multiplier) {
        double damage = baseDamage * multiplier;
        if (Math.random() < critRate) {
            damage *= critDmg;
            System.out.println("暴击！");
        }
        return (int) damage;
    }

    @Override
    public void act(BattleSystem battle) {
        // 由子类实现具体行动
    }

    @Override
    public String getStatus() {
        return super.getStatus() + " [能量: " + energy + "/" + maxEnergy + "]";
    }
}