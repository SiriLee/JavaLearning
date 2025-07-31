package com.siri.game.mystarrail.starrail_reference;

import java.util.ArrayList;
import java.util.List;

// 战斗系统
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
        skillPoints = 3; // 初始战技点
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
        System.out.println("===== 战斗开始! =====");

        while (!isBattleOver()) {
            System.out.println("\n===== 回合 " + turnCount + " =====");
            System.out.println("战技点: " + skillPoints + "/" + maxSkillPoints);

            // 显示状态
            System.out.println("\n[角色状态]");
            for (Character c : characters) {
                if (c.isAlive()) {
                    System.out.println(c.getStatus());
                }
            }

            System.out.println("\n[敌人状态]");
            for (Enemy e : enemies) {
                if (e.isAlive()) {
                    System.out.println(e.getStatus());
                }
            }

            // 推进行动值
            for (Character c : characters) {
                if (c.isAlive()) c.advanceActionValue();
            }
            for (Enemy e : enemies) {
                if (e.isAlive()) e.advanceActionValue();
            }

            // 选择行动单位
            BattleUnit nextActor = null;
            while (nextActor == null) {
                // 找出行动值最高的单位
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

                // 如果没有单位准备好行动，继续推进行动值
                if (nextActor == null) {
                    for (Character c : characters) {
                        if (c.isAlive()) c.advanceActionValue();
                    }
                    for (Enemy e : enemies) {
                        if (e.isAlive()) e.advanceActionValue();
                    }
                }
            }

            // 执行行动
            nextActor.resetActionValue();
            nextActor.act(this);

            // 检查战斗是否结束
            if (isBattleOver()) {
                break;
            }

            turnCount++;
        }

        // 战斗结束
        System.out.println("\n===== 战斗结束! =====");
        boolean charactersWin = true;
        for (Character c : characters) {
            if (c.isAlive()) {
                System.out.println(c.name + " 存活!");
            }
        }
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                System.out.println(e.name + " 存活!");
                charactersWin = false;
            }
        }

        if (charactersWin) {
            System.out.println("角色方胜利!");
        } else {
            System.out.println("敌人方胜利!");
        }
    }
}
