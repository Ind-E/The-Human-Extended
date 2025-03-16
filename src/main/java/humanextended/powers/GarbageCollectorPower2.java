package humanextended.powers;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import humanextended.util.Wiz;
import theHuman.HumanMod;
import theHuman.powers.TechniquePower;

import static humanextended.HumanExtendedMod.makeID;

public class GarbageCollectorPower2 extends BasePower {

    public static final String POWER_ID = makeID("GarbageCollectorPower2");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private int junkDoubledThisTurn = 0;

    public GarbageCollectorPower2(int amount) {
        super(POWER_ID, TYPE, TURN_BASED, Wiz.p(), Wiz.p(), amount, true, false);
        loadRegion("echo");
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0];
        } else {
            description = String.format(DESCRIPTIONS[1], amount);
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (junkDoubledThisTurn >= amount) {
            return;
        }
        if (!(card.hasTag(HumanMod.HumanCardTags.JUNK_HUMAN))) {
            return;
        }
        if (card.baseDiscard == -7) {
            return;
        }
        junkDoubledThisTurn++;
        flash();
        AbstractMonster target = null;
        if (action.target != null) {
            target = (AbstractMonster)action.target;
        }

        AbstractCard tmp = card.makeSameInstanceOf();
        Wiz.p().limbo.addToBottom(tmp);
        tmp.current_x = card.current_x;
        tmp.current_y = card.current_y;
        tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        tmp.target_y = (float)Settings.HEIGHT / 2.0F;
        if (target != null) {
            tmp.calculateCardDamage(target);
        }

        tmp.purgeOnUse = true;
        tmp.baseDiscard = -7;
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, target, card.energyOnUse, true, true), true);
    }

}
