package PrisonersDilemma;

public interface Strategy {
    boolean decide(Prisoner prisoner);
}

class AlwaysCooperate implements Strategy {
    @Override
    public boolean decide(Prisoner prisoner) {
        return true;
    }
}

class AlwaysCheat implements Strategy {
    @Override
    public boolean decide(Prisoner prisoner) {
        return false;
    }
}

class RandomlyCooperate implements Strategy {
    @Override
    public boolean decide(Prisoner prisoner) {
        return Math.random() > 0.5;
    }
}

class Tit4Tat implements Strategy {
    @Override
    public boolean decide(Prisoner prisoner) {
        return prisoner.partnerCheated;
    }
}
