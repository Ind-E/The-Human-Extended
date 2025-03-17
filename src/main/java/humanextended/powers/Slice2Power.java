package humanextended.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import humanextended.util.Wiz;
import theHuman.powers.HealthinessPower;
import theHuman.powers.PeacefulPower;

import static humanextended.HumanExtendedMod.makeID;

public class Slice2Power extends BasePower {

    public static final String POWER_ID = makeID("Slice2Power");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public Slice2Power(int amount) {
        super(POWER_ID, TYPE, TURN_BASED, Wiz.p(), Wiz.p(), amount);
    }

    @Override
    public void atStartOfTurn() {
        Wiz.applyToSelf(new HealthinessPower(Wiz.p(), Wiz.p(), amount));
        Wiz.atb(new RemoveSpecificPowerAction(Wiz.p(), Wiz.p(), this));
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

}
