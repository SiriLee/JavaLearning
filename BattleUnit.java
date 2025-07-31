package com.siri.game.mystarrail.starrail_reference;

// ս����λ����
abstract class BattleUnit {
    protected String name;
    protected int maxHp;
    protected int hp;
    protected int attack;
    protected int defense;
    protected int speed;
    protected int actionValue; // �ж�ֵ��0-10000��

    public BattleUnit(String name, int maxHp, int attack, int defense, int speed) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.actionValue = 0;
    }

    // �ж�ֵ�ƽ�
    public void advanceActionValue() {
        actionValue += speed;
    }

    // �Ƿ�׼�����ж�
    public boolean isReadyToAct() {
        return actionValue >= 10000;
    }

    // �ж��������ж�ֵ
    public void resetActionValue() {
        actionValue -= 10000;
    }

    // �ܵ��˺�
    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - defense);
        hp = Math.max(0, hp - actualDamage);
        System.out.println(name + " �ܵ� " + actualDamage + " ���˺���");
    }

    // �Ƿ���
    public boolean isAlive() {
        return hp > 0;
    }

    // ��ȡ״̬��Ϣ
    public String getStatus() {
        return name + " [HP: " + hp + "/" + maxHp + "]";
    }

    // �����ж�����
    public abstract void act(BattleSystem battle);
}
