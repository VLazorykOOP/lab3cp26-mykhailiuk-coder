/*
Задача: Ігровий персонаж із емоційним станом та логом подій

Сценарій:

У грі є один глобальний логер подій — всі персонажі пишуть у нього повідомлення. Тут використовується Singleton.

Кожен персонаж може мати різні стани емоцій: щасливий, сумний, злий — це State, і поведінка персонажа змінюється залежно від стану.

До персонажа можна додати додаткові можливості (наприклад, магічний щит або бонус до атаки) — це Decorator, який динамічно розширює його функціональність.
*/

//Singleton pattern
class Logger {
    private static Logger instance;

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println(message);
    }
}

//State pattern
interface State {
    void act();
}

class Happy implements State {
    public void act() {
        Logger.getInstance().log("Character is happy!");
    }
}

class Angry implements State {
    public void act() {
        Logger.getInstance().log("Character is angry!");
    }
}

abstract class Character {
    public abstract void performAction();
}

//Decorator
class BasicCharacter extends Character {
    private State state;

    public BasicCharacter(State state) {
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public void performAction() {
        state.act();
    }
}

class MagicShield extends Character {
    private Character character;

    public MagicShield(Character character) {
        this.character = character;
    }

    @Override
    public void performAction() {
        character.performAction();
        Logger.getInstance().log("...with a magical shield!");
    }
}

public class Main {
    public static void main(String[] args) {
        State happy = new Happy();
        State angry = new Angry();

        BasicCharacter hero = new BasicCharacter(happy);
        Character heroWithShield = new MagicShield(hero);

        heroWithShield.performAction();
        hero.setState(angry);
        heroWithShield.performAction();
    }
}
