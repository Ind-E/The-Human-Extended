package humanextended.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;
import humanextended.util.Wiz;
import theHuman.HumanMod;
import theHuman.powers.TechniquePower;

import static humanextended.HumanExtendedMod.makeID;

public class GunkataPower extends BasePower {

    public static final String POWER_ID = makeID("GunkataPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public GunkataPower() {
        super(POWER_ID, TYPE, TURN_BASED, Wiz.p(), Wiz.p(), -1, true, false);
        loadRegion("berserk");
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(HumanMod.HumanCardTags.SHOOTER_HUMAN)) {
            this.flash();
            Wiz.applyToSelf(new TechniquePower(Wiz.p(), Wiz.p(), 1));
        }
    }

}
