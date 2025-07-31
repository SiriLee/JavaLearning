package com.siri.game.mystarrail.starrail_reference;

// 敌人类
class Enemy extends BattleUnit {
    public Enemy(String name, int maxHp, int attack, int defense, int speed) {
        super(name, maxHp, attack, defense, speed);
    }

    // 普通攻击
    public void basicAttack(BattleUnit target) {
        int damage = attack;
        System.out.println(name + " 进行攻击！");
        target.takeDamage(damage);
    }

    // 技能
    public void skill(BattleUnit target) {
        int damage = (int)(attack * 1.5);
        System.out.println(name + " 使用技能！");
        target.takeDamage(damage);
    }

    @Override
    public void act(BattleSystem battle) {
        // 简单AI：随机选择目标并使用普通攻击或技能
        BattleUnit target = battle.getRandomAliveCharacter();
        if (target == null) return;

        if (Math.random() < 0.7) {
            basicAttack(target);
        } else {
            skill(target);
        }
    }
}
