package humanextended.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import humanextended.util.Wiz;
import theHuman.powers.HealthinessPower;
import theHuman.powers.PeacefulPower;

import static humanextended.HumanExtendedMod.makeID;

public class AtPeacePower extends BasePower {

    public static final String POWER_ID = makeID("AtPeacePower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public AtPeacePower(int amount) {
        super(POWER_ID, TYPE, TURN_BASED, Wiz.p(), Wiz.p(), amount);
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount, amount);
    }

    @Override
    public void atStartOfTurn() {
        Wiz.applyToSelf(new HealthinessPower(Wiz.p(), Wiz.p(), amount));
        Wiz.applyToSelf(new PeacefulPower(Wiz.p(), Wiz.p(), amount));
    }

}

