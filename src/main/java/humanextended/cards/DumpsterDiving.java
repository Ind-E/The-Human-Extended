package humanextended.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.actions.FindJunkAction;
import theHuman.characters.TheHuman;

import static humanextended.HumanExtendedMod.imagePath;


public class DumpsterDiving extends BaseCard {

    public static final String ID = theHuman.cards.DumpsterDiving.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            1);


    public DumpsterDiving() {
        super(ID, info, "theHumanResources/images/cards/DumpsterDiving.png");
        setMagic(2, 1);
        setCustomVar("DISCARD", 1, 1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new FindJunkAction(magicNumber));
    }

    @Override
    public void triggerOnManualDiscard() {
        Wiz.atb(new FindJunkAction(customVar("DISCARD")));
    }
}

