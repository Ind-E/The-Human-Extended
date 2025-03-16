package humanextended.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.cards.tokens.SliceOfCake;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;

import static humanextended.HumanExtendedMod.imagePath;


public class BirthdayCake extends BaseCard {

    public static final String ID = theHuman.cards.BirthdayCake.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            2);

    public BirthdayCake() {
        super(ID, info, "theHumanResources/images/cards/BirthdayCake.png");
        setCostUpgrade(1);
        setSelfRetain(true, false);
        setMagic(2,1);
        cardsToPreview = new SliceOfCake();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new MakeTempCardInDrawPileAction(new SliceOfCake(), magicNumber, true, false));
    }
}
