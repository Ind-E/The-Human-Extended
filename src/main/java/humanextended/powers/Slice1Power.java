package humanextended.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import humanextended.util.Wiz;
import theHuman.powers.PeacefulPower;

import static humanextended.HumanExtendedMod.makeID;

public class Slice1Power extends BasePower {

    public static final String POWER_ID = makeID("Slice1Power");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public Slice1Power(int amount) {
        super(POWER_ID, TYPE, TURN_BASED, Wiz.p(), Wiz.p(), amount);
    }

    @Override
    public void atStartOfTurn() {
        Wiz.applyToSelf(new PeacefulPower(Wiz.p(), Wiz.p(), amount));
        Wiz.atb(new RemoveSpecificPowerAction(Wiz.p(), Wiz.p(), this));
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

}
