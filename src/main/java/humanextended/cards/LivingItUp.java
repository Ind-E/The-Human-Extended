package humanextended.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;
import theHuman.patches.subscribers.OnShuffleSubscriber;

import static humanextended.HumanExtendedMod.imagePath;

public class LivingItUp extends BaseCard implements OnShuffleSubscriber {

    public static final String ID = theHuman.cards.LivingItUp.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            1);

    public LivingItUp() {
        super(ID, info, "theHumanResources/images/cards/LivingItUp.png");
        setMagic(1, 1);
        returnToHand = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        selfRetain = false;
        Wiz.atb(new DrawCardAction(magicNumber));
        Wiz.atb(new DiscardAction(p, p, magicNumber, false));
    }

    @Override
    public void onShuffle() {
        Wiz.atb(new GainEnergyAction(1));
    }

    @Override
    public void triggerWhenDrawn() {
        selfRetain = true;
    }
}
