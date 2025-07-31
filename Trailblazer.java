package com.siri.game.mystarrail.starrail_reference;

import java.util.Scanner;

// 示例角色：开拓者
class Trailblazer extends Character {
    public Trailblazer() {
        super("开拓者", 200, 50, 20, 120, 100, 0.2, 1.8);
    }

    @Override
    public void act(BattleSystem battle) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n===== 开拓者的回合 =====");
        System.out.println("1. 普通攻击");
        System.out.println("2. 战技 (消耗1战技点)");
        System.out.println("3. 终结技 (消耗100能量)");
        System.out.print("请选择行动: ");

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
                    System.out.println("战技点不足！使用普通攻击代替");
                    basicAttack(target);
                    battle.addSkillPoint();
                }
                break;
            case 3:
                if (energy >= 100) {
                    ultimate(target);
                } else {
                    System.out.println("能量不足！使用普通攻击代替");
                    basicAttack(target);
                    battle.addSkillPoint();
                }
                break;
            default:
                System.out.println("无效选择，使用普通攻击");
                basicAttack(target);
                battle.addSkillPoint();
        }
    }
}
