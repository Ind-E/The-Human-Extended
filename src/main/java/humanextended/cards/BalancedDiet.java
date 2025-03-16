package humanextended.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.cards.tokens.CheatDay;
import humanextended.cards.tokens.HealthyEating;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;

import java.util.ArrayList;

import static humanextended.HumanExtendedMod.imagePath;

// renamed from Dieting
public class BalancedDiet extends BaseCard {

    public static final String ID = theHuman.cards.Dieting.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            2);

    public BalancedDiet() {
        super(ID, info, imagePath("cards/power/BalancedDiet.png"));
        setMagic(2);
        cardsToPreviewList = new ArrayList<>();
        cardsToPreviewList.add(new HealthyEating());
        cardsToPreviewList.add(new CheatDay());
    }
    

    @Override
    public void upgrade() {
        if (!upgraded) {
            for (AbstractCard c : cardsToPreviewList) {
                c.upgrade();
            }
        }
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new MakeTempCardInDrawPileAction(cardsToPreviewList.get(0).makeStatEquivalentCopy(), magicNumber, true, true));
        Wiz.atb(new MakeTempCardInDrawPileAction(cardsToPreviewList.get(1).makeStatEquivalentCopy(), 1, true, true));
    }
}
