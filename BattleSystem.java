package com.siri.game.mystarrail.starrail_reference;

import java.util.ArrayList;
import java.util.List;

// ս��ϵͳ
class BattleSystem {
    private final List<Character> characters;
    private final List<Enemy> enemies;
    private int skillPoints;
    private final int maxSkillPoints;
    private int turnCount;

    public BattleSystem() {
        characters = new ArrayList<>();
        enemies = new ArrayList<>();
        maxSkillPoints = 5;
        skillPoints = 3; // ��ʼս����
        turnCount = 1;
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public boolean useSkillPoint() {
        if (skillPoints > 0) {
            skillPoints--;
            return true;
        }
        return false;
    }

    public void addSkillPoint() {
        skillPoints = Math.min(maxSkillPoints, skillPoints + 1);
    }

    public BattleUnit getRandomAliveCharacter() {
        List<Character> alive = new ArrayList<>();
        for (Character c : characters) {
            if (c.isAlive()) alive.add(c);
        }
        if (alive.isEmpty()) return null;
        return alive.get((int)(Math.random() * alive.size()));
    }

    public BattleUnit getRandomAliveEnemy() {
        List<Enemy> alive = new ArrayList<>();
        for (Enemy e : enemies) {
            if (e.isAlive()) alive.add(e);
        }
        if (alive.isEmpty()) return null;
        return alive.get((int)(Math.random() * alive.size()));
    }

    public boolean isBattleOver() {
        boolean allCharactersDead = true;
        for (Character c : characters) {
            if (c.isAlive()) {
                allCharactersDead = false;
                break;
            }
        }

        boolean allEnemiesDead = true;
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                allEnemiesDead = false;
                break;
            }
        }

        return allCharactersDead || allEnemiesDead;
    }

    public void startBattle() {
        System.out.println("===== ս����ʼ! =====");

        while (!isBattleOver()) {
            System.out.println("\n===== �غ� " + turnCount + " =====");
            System.out.println("ս����: " + skillPoints + "/" + maxSkillPoints);

            // ��ʾ״̬
            System.out.println("\n[��ɫ״̬]");
            for (Character c : characters) {
                if (c.isAlive()) {
                    System.out.println(c.getStatus());
                }
            }

            System.out.println("\n[����״̬]");
            for (Enemy e : enemies) {
                if (e.isAlive()) {
                    System.out.println(e.getStatus());
                }
            }

            // �ƽ��ж�ֵ
            for (Character c : characters) {
                if (c.isAlive()) c.advanceActionValue();
            }
            for (Enemy e : enemies) {
                if (e.isAlive()) e.advanceActionValue();
            }

            // ѡ���ж���λ
            BattleUnit nextActor = null;
            while (nextActor == null) {
                // �ҳ��ж�ֵ��ߵĵ�λ
                int maxActionValue = 0;
                for (Character c : characters) {
                    if (c.isAlive() && c.isReadyToAct() && c.actionValue > maxActionValue) {
                        maxActionValue = c.actionValue;
                        nextActor = c;
                    }
                }
                for (Enemy e : enemies) {
                    if (e.isAlive() && e.isReadyToAct() && e.actionValue > maxActionValue) {
                        maxActionValue = e.actionValue;
                        nextActor = e;
                    }
                }

                // ���û�е�λ׼�����ж��������ƽ��ж�ֵ
                if (nextActor == null) {
                    for (Character c : characters) {
                        if (c.isAlive()) c.advanceActionValue();
                    }
                    for (Enemy e : enemies) {
                        if (e.isAlive()) e.advanceActionValue();
                    }
                }
            }

            // ִ���ж�
            nextActor.resetActionValue();
            nextActor.act(this);

            // ���ս���Ƿ����
            if (isBattleOver()) {
                break;
            }

            turnCount++;
        }

        // ս������
        System.out.println("\n===== ս������! =====");
        boolean charactersWin = true;
        for (Character c : characters) {
            if (c.isAlive()) {
                System.out.println(c.name + " ���!");
            }
        }
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                System.out.println(e.name + " ���!");
                charactersWin = false;
            }
        }

        if (charactersWin) {
            System.out.println("��ɫ��ʤ��!");
        } else {
            System.out.println("���˷�ʤ��!");
        }
    }
}
