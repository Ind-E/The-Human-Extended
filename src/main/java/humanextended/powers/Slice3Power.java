package humanextended.powers;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import humanextended.util.Wiz;
import theHuman.powers.PeacefulPower;

import static humanextended.HumanExtendedMod.makeID;

public class Slice3Power extends BasePower {

    public static final String POWER_ID = makeID("Slice3Power");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public Slice3Power(int amount) {
        super(POWER_ID, TYPE, TURN_BASED, Wiz.p(), Wiz.p(), amount);
    }

    @Override
    public void atStartOfTurn() {
        Wiz.atb(new GainGoldAction(amount));
        AbstractDungeon.effectList.add(new RainingGoldEffect(amount * 2, true));
        Wiz.atb(new RemoveSpecificPowerAction(Wiz.p(), Wiz.p(), this));
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

}
