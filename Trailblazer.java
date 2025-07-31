package com.siri.game.mystarrail.starrail_reference;

import java.util.Scanner;

// ʾ����ɫ��������
class Trailblazer extends Character {
    public Trailblazer() {
        super("������", 200, 50, 20, 120, 100, 0.2, 1.8);
    }

    @Override
    public void act(BattleSystem battle) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n===== �����ߵĻغ� =====");
        System.out.println("1. ��ͨ����");
        System.out.println("2. ս�� (����1ս����)");
        System.out.println("3. �սἼ (����100����)");
        System.out.print("��ѡ���ж�: ");

        int choice = scanner.nextInt();
        BattleUnit target = battle.getRandomAliveEnemy();
        if (target == null) return;

        switch (choice) {
            case 1:
                basicAttack(target);
                battle.addSkillPoint();
                break;
            case 2:
                if (battle.useSkillPoint()) {
                    skill(target);
                } else {
                    System.out.println("ս���㲻�㣡ʹ����ͨ��������");
                    basicAttack(target);
                    battle.addSkillPoint();
                }
                break;
            case 3:
                if (energy >= 100) {
                    ultimate(target);
                } else {
                    System.out.println("�������㣡ʹ����ͨ��������");
                    basicAttack(target);
                    battle.addSkillPoint();
                }
                break;
            default:
                System.out.println("��Чѡ��ʹ����ͨ����");
                basicAttack(target);
                battle.addSkillPoint();
        }
    }
}
