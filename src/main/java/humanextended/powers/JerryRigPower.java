package humanextended.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;
import humanextended.patches.DiscardHookPatch;
import humanextended.util.Wiz;
import theHuman.HumanMod;
import theHuman.actions.FindJunkAction;
import theHuman.powers.TechniquePower;

import static humanextended.HumanExtendedMod.makeID;

public class JerryRigPower extends BasePower implements DiscardHookPatch.OnDiscardTrigger {

    public static final String POWER_ID = makeID("JerryRigPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public JerryRigPower(int amount) {
        super(POWER_ID, TYPE, TURN_BASED, Wiz.p(), Wiz.p(), amount);
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(HumanMod.HumanCardTags.SHOOTER_HUMAN)) {
            this.flash();
            Wiz.applyToSelf(new TechniquePower(Wiz.p(), Wiz.p(), 1));
        }
    }

    @Override
    public void onManualDiscardTrigger() {
        Wiz.atb(new FindJunkAction(amount));
    }

}
