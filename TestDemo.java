package com.siri.game.mystarrail.starrail_reference;

// ������
public class TestDemo {
    public static void main(String[] args) {
        // ����ս��ϵͳ
        BattleSystem battleSystem = new BattleSystem();

        // ��ӽ�ɫ�͵���
        battleSystem.addCharacter(new Trailblazer());
        battleSystem.addEnemy(new Voidranger());
        battleSystem.addEnemy(new Voidranger());

        // ��ʼս��
        battleSystem.startBattle();
    }
}