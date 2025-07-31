package com.siri.game.mystarrail.starrail_reference;

// ��ɫ����
class Character extends BattleUnit {
    protected int energy;
    protected int maxEnergy;
    protected double critRate; // ������ (0.0-1.0)
    protected double critDmg;  // �����˺� (1.0+)

    public Character(String name, int maxHp, int attack, int defense, int speed,
                     int maxEnergy, double critRate, double critDmg) {
        super(name, maxHp, attack, defense, speed);
        this.maxEnergy = maxEnergy;
        this.energy = 0;
        this.critRate = critRate;
        this.critDmg = critDmg;
    }

    // ��ͨ����
    public void basicAttack(BattleUnit target) {
        int damage = calculateDamage(attack, 1.0);
        System.out.println(name + " ������ͨ������");
        target.takeDamage(damage);
        energy = Math.min(maxEnergy, energy + 20);
    }

    // ս����Ĭ��Ϊ������ս����
    public void skill(BattleUnit target) {
        int damage = calculateDamage(attack, 1.8);
        System.out.println(name + " ʹ��ս����");
        target.takeDamage(damage);
        energy = Math.min(maxEnergy, energy + 30);
    }

    // �սἼ��Ĭ��Ϊ�������սἼ��
    public void ultimate(BattleUnit target) {
        int damage = calculateDamage(attack, 3.0);
        System.out.println(name + " �ͷ��սἼ��");
        target.takeDamage(damage);
        energy = 0;
    }

    // �����˺�������������
    protected int calculateDamage(int baseDamage, double multiplier) {
        double damage = baseDamage * multiplier;
        if (Math.random() < critRate) {
            damage *= critDmg;
            System.out.println("������");
        }
        return (int) damage;
    }

    @Override
    public void act(BattleSystem battle) {
        // ������ʵ�־����ж�
    }

    @Override
    public String getStatus() {
        return super.getStatus() + " [����: " + energy + "/" + maxEnergy + "]";
    }
}