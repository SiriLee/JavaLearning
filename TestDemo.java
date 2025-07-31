package com.siri.game.mystarrail.starrail_reference;

// 测试类
public class TestDemo {
    public static void main(String[] args) {
        // 创建战斗系统
        BattleSystem battleSystem = new BattleSystem();

        // 添加角色和敌人
        battleSystem.addCharacter(new Trailblazer());
        battleSystem.addEnemy(new Voidranger());
        battleSystem.addEnemy(new Voidranger());

        // 开始战斗
        battleSystem.startBattle();
    }
}