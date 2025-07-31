package com.siri.game.mystarrail.starrail_reference;

// ������
class Enemy extends BattleUnit {
    public Enemy(String name, int maxHp, int attack, int defense, int speed) {
        super(name, maxHp, attack, defense, speed);
    }

    // ��ͨ����
    public void basicAttack(BattleUnit target) {
        int damage = attack;
        System.out.println(name + " ���й�����");
        target.takeDamage(damage);
    }

    // ����
    public void skill(BattleUnit target) {
        int damage = (int)(attack * 1.5);
        System.out.println(name + " ʹ�ü��ܣ�");
        target.takeDamage(damage);
    }

    @Override
    public void act(BattleSystem battle) {
        // ��AI�����ѡ��Ŀ�겢ʹ����ͨ��������
        BattleUnit target = battle.getRandomAliveCharacter();
        if (target == null) return;

        if (Math.random() < 0.7) {
            basicAttack(target);
        } else {
            skill(target);
        }
    }
}
