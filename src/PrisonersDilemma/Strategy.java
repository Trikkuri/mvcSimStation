package PrisonersDilemma;

public interface Strategy {
    boolean decide(Prisoner prisoner);
    StrategyType getType();
}

class AlwaysCooperate implements Strategy {
    @Override
    public boolean decide(Prisoner prisoner) {
        return true;
    }

    @Override
    public StrategyType getType() {
        return StrategyType.COOPERATE;
    }
}

class AlwaysCheat implements Strategy {
    @Override
    public boolean decide(Prisoner prisoner) {
        return false;
    }

    @Override
    public StrategyType getType() {
        return StrategyType.CHEAT;
    }
}

class RandomlyCooperate implements Strategy {
    @Override
    public boolean decide(Prisoner prisoner) {
        return Math.random() > 0.5;
    }

    @Override
    public StrategyType getType() {
        return StrategyType.RANDOMLY_COOPERATE;
    }
}

class Tit4Tat implements Strategy {
    @Override
    public boolean decide(Prisoner prisoner) {
        return prisoner.partnerCheated;
    }

    @Override
    public StrategyType getType() {
        return StrategyType.TIT4TAT;
    }
}