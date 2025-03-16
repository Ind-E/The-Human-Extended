package humanextended.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.cards.tokens.DeepThought;
import theHuman.characters.TheHuman;
import theHuman.powers.HealthinessPower;

import static humanextended.HumanExtendedMod.imagePath;

// renamed from Happiness
public class Yoga extends BaseCard {

    public static final String ID = theHuman.cards.Happiness.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            2);

    public Yoga() {
        super(ID, info, imagePath("cards/skill/Yoga.png"));
        setMagic(1);
        cardsToPreview = new DeepThought();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new HealthinessPower(p, p, magicNumber));
        if (upgraded) {
            Wiz.makeInHand(new DeepThought());
        } else {
            Wiz.atb(new MakeTempCardInDrawPileAction(new DeepThought(), 1, true, true));
        }
    }
}
