package humanextended.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import humanextended.cards.tokens.Lollipop;
import humanextended.util.Wiz;
import theHuman.powers.HealthinessPower;

import static humanextended.HumanExtendedMod.makeID;

public class AppleADayPlusPower extends BasePower {

    public static final String POWER_ID = makeID("AppleADayPlusPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public AppleADayPlusPower(int amount, int amount2) {
        super(POWER_ID, TYPE, TURN_BASED, Wiz.p(), Wiz.p(), amount, true, false);
        loadRegion("berserk");
        this.amount2 = amount2;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount, amount2, amount, amount > 1 ? "s" : "");
    }

    @Override
    public void atStartOfTurnPostDraw() {
        AbstractPower health = Wiz.p().getPower(HealthinessPower.POWER_ID);
        int healthAmount = health != null ? health.amount : 0;

        int powerToAdd = 0, cardsToAdd = 0;

        for (int i = 0; i < amount; i++) {
            if (healthAmount < amount2) {
                powerToAdd++;
                healthAmount++;
            } else {
                cardsToAdd++;
            }
        }

        Wiz.applyToSelf(new HealthinessPower(Wiz.p(), Wiz.p(), powerToAdd));
        Wiz.makeInHand(new Lollipop(), cardsToAdd);
    }


}
